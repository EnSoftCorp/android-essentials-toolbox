package com.ensoftcorp.open.android.essentials.ui.views;

import java.io.IOException;

import org.eclipse.debug.internal.ui.DebugPluginImages;
import org.eclipse.debug.internal.ui.IInternalDebugUIConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.ensoftcorp.atlas.core.db.graph.Edge;
import com.ensoftcorp.atlas.core.db.graph.GraphElement;
import com.ensoftcorp.atlas.core.db.graph.Node;
import com.ensoftcorp.atlas.core.query.Q;
import com.ensoftcorp.atlas.core.xcsg.XCSG;
import com.ensoftcorp.atlas.java.core.script.Common;
import com.ensoftcorp.open.android.essentials.permissions.Permission;
import com.ensoftcorp.open.android.essentials.permissions.PermissionGroup;
import com.ensoftcorp.open.android.essentials.permissions.ProtectionLevel;
import com.ensoftcorp.open.commons.analysis.StandardQueries;
import com.ensoftcorp.open.commons.utilities.DisplayUtils;
import com.ensoftcorp.open.commons.utilities.FormattedSourceCorrespondence;

/**
 * An Eclipse view for searching and viewing apply permission mapping values in the Atlas index
 * @author Ben Holland
 */
@SuppressWarnings("restriction")
public class PermissionUsageView extends ViewPart {
	public PermissionUsageView() {}

	private final int PERMISSION_USAGE_COLOR = SWT.COLOR_RED;

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.ensoftcorp.open.android.essentials.ui.views.PermissionUsageView";
	
	private boolean usageFilterEnabled = false;
	private boolean expandTreeEnabled = true;
	
	@Override
	public void createPartControl(final Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		final Composite searchBarComposite = new Composite(parent, SWT.NONE);
		searchBarComposite.setLayout(new GridLayout(2, false));
		searchBarComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		final Button searchBarEnabledCheckbox = new Button(searchBarComposite, SWT.CHECK);
		searchBarEnabledCheckbox.setText("Filter by Permission: ");
		
		final Combo searchBar = new Combo(searchBarComposite, SWT.NONE);
		searchBar.setEnabled(false);
		searchBar.setToolTipText("Search for a permission by typing part of the name and pressing return or selecting an autocomplete suggestion.");
		searchBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		final SashForm sashForm = new SashForm(parent, SWT.NONE);
		sashForm.setToolTipText("Click and drag to resize.");
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		final Composite treeViewComposite = new Composite(sashForm, SWT.NONE);
		treeViewComposite.setLayout(new GridLayout(1, false));

		final Tree tree = new Tree(treeViewComposite, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		final Composite detailsComposite = new Composite(sashForm, SWT.NONE);
		detailsComposite.setLayout(new GridLayout(1, false));

		final StyledText detailsText = new StyledText(detailsComposite, SWT.BORDER | SWT.WRAP);
		detailsText.setEditable(false);
		detailsText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		sashForm.setWeights(new int[] { 1, 1 });
		
		searchBar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(searchBarEnabledCheckbox.getSelection()){
					repopulatePermissionTreeWithSearchResults(tree, detailsText, searchBar);
				}		
			}
		});
		
		searchBarEnabledCheckbox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(searchBarEnabledCheckbox.getSelection()){
					searchBar.setEnabled(true);
					repopulatePermissionTreeWithSearchResults(tree, detailsText, searchBar);
				} else {
					searchBar.setText("");
					searchBar.setEnabled(false);
					repopulatePermissionsTree(tree, detailsText);
				}
			}
		});
		
		searchBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent key) {
				if(key.character == '\r'){
					if(searchBarEnabledCheckbox.getSelection()){
						repopulatePermissionTreeWithSearchResults(tree, detailsText, searchBar);
					} else {
						repopulatePermissionsTree(tree, detailsText);
					}
				} else {
					if(Character.isLetter(key.character)){
						searchBar.setListVisible(false); // hide the list we are going to modify the values
						String searchText = searchBar.getText();

						// remove all items
						// note: doing this the hard way because removeAll method also clears the text
						for(String item : searchBar.getItems()){
							searchBar.remove(item);
						}

						// add the autocomplete suggestions for each matching permission
						for(Permission permission : Permission.getAllPermissions()){
							if(permission.getQualifiedName().toLowerCase().contains(searchText.toLowerCase())){
								searchBar.add(permission.getQualifiedName());
							}
						}
					}
				}
			}
		});

		// show Atlas graph on tree node double click event
		tree.addListener(SWT.MouseDoubleClick, new Listener() {
			public void handleEvent(Event event) {
				Object data = tree.getSelection()[0].getData();
				if (data instanceof GraphElement) {
					Edge callEdge = (Edge) data;
					try {
						detailsText.setText(prettyPrintGraphElement(callEdge));
					} catch (IOException e) {
						// unknown data, just clear out the text display
						detailsText.setText("");
					}
					DisplayUtils.show(callEdge, callEdge.from().getAttr(XCSG.name) + " call to " + callEdge.to().getAttr(XCSG.name));
				}
			}
		});

		// update the details text when a tree item is clicked
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				Object data = tree.getSelection()[0].getData();
				if (data instanceof ProtectionLevel) {
					ProtectionLevel protectionLevel = (ProtectionLevel) data;
					String displayText = "Protection Level Name: " + protectionLevel.getName() + "\n\nDescription: " + protectionLevel.getDescription() + "\n\nProtection Level: " + protectionLevel.getLevel();
					detailsText.setText(displayText);
				} else if (data instanceof PermissionGroup) {
					PermissionGroup permissionGroup = (PermissionGroup) data;
					String displayText = "Permission Group Name: " + permissionGroup.getSimpleName() + "\n\nQualified Permission Group Name: " + permissionGroup.getQualifiedName() + "\n\nDescription: " + permissionGroup.getDescription()
							+ "\n\nIntroduced in API version: " + permissionGroup.addedInAPILevel();
					detailsText.setText(displayText);
				} else if (data instanceof Permission) {
					Permission permission = (Permission) data;
					String displayText = "Permission Name: " + permission.getSimpleName() + "\n\nQualified Permission Name: " + permission.getQualifiedName() + "\n\nDescription: " + permission.getDescription() + "\n\nIntroduced in API version: "
							+ (permission.addedInAPILevel() == -1 ? "Unknown" : permission.addedInAPILevel()) + "\n\nIs Deprecated: " + permission.isDeprecated();
					detailsText.setText(displayText);
				} else if (data instanceof GraphElement) {
					GraphElement graphElement = (GraphElement) data;
					try {
						detailsText.setText(prettyPrintGraphElement(graphElement));
					} catch (IOException e) {
						// unknown data, just clear out the text display
						detailsText.setText("");
					}
				} else {
					// unknown data, just clear out the text display
					detailsText.setText("");
				}
			}
		});

		// add a refresh button to rebuild the tree
		final Action refreshAction = new Action() {
			public void run() {
				if(searchBarEnabledCheckbox.getSelection()){
					repopulatePermissionTreeWithSearchResults(tree, detailsText, searchBar);
				} else {
					repopulatePermissionsTree(tree, detailsText);
				}
			}
		};
		refreshAction.setText("Refresh");
		refreshAction.setToolTipText("Refresh");
		refreshAction.setImageDescriptor(DebugPluginImages.getImageDescriptor(IInternalDebugUIConstants.IMG_ELCL_TERMINATE_AND_RELAUNCH));
		refreshAction.setDisabledImageDescriptor(DebugPluginImages.getImageDescriptor(IInternalDebugUIConstants.IMG_DLCL_TERMINATE_AND_RELAUNCH));
		refreshAction.setHoverImageDescriptor(DebugPluginImages.getImageDescriptor(IInternalDebugUIConstants.IMG_ELCL_TERMINATE_AND_RELAUNCH));
		getViewSite().getActionBars().getToolBarManager().add(refreshAction);

		// add expand/collapse tree action
		final Action expandTreeToggleAction = new Action() {
			public void run() {
				if (expandTreeEnabled) {
					this.setText("Expand by usage");
					this.setToolTipText("Expand by usage");
					this.setImageDescriptor(DebugPluginImages.getImageDescriptor(IInternalDebugUIConstants.IMG_ELCL_SHOW_LOGICAL_STRUCTURE));
				} else {
					this.setText("Collapse tree");
					this.setToolTipText("Collapse tree");
					this.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));
				}				
				expandTreeEnabled = !expandTreeEnabled;
				
				if(searchBarEnabledCheckbox.getSelection()){
					repopulatePermissionTreeWithSearchResults(tree, detailsText, searchBar);
				} else {
					repopulatePermissionsTree(tree, detailsText);
				}
			}
		};
		expandTreeToggleAction.setText("Collapse tree");
		expandTreeToggleAction.setToolTipText("Collapse tree");
		expandTreeToggleAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_COLLAPSEALL));
		getViewSite().getActionBars().getToolBarManager().add(expandTreeToggleAction);
		
		// add active usage filter action
		final Action usageFilterToggleAction = new Action() {
			public void run() {
				if (usageFilterEnabled) {
					this.setText("Filter by usage");
					this.setToolTipText("Filter by usage");
					this.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED_DISABLED));
				} else {
					this.setText("Stop filtering");
					this.setToolTipText("Stop filtering");
					this.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));
				}				
				usageFilterEnabled = !usageFilterEnabled;
				
				if(searchBarEnabledCheckbox.getSelection()){
					repopulatePermissionTreeWithSearchResults(tree, detailsText, searchBar);
				} else {
					repopulatePermissionsTree(tree, detailsText);
				}
			}
		};
		usageFilterToggleAction.setText("Filter by usage");
		usageFilterToggleAction.setToolTipText("Filter by usage");
		usageFilterToggleAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED_DISABLED));
		getViewSite().getActionBars().getToolBarManager().add(usageFilterToggleAction);
		
		// populate the tree for the first time
		populatePermissionsTree(tree);
	}

	/**
	 * Helper method for repopulating the permission group and protection level subtrees without the search results
	 * @param tree
	 * @param detailsText
	 * @param searchBar
	 */
	private void repopulatePermissionTreeWithSearchResults(final Tree tree, final StyledText detailsText, final Combo searchBar){
		detailsText.setText(""); // clear out the details view
		tree.removeAll(); // clear the tree contents
		for(Permission permission : Permission.getAllPermissions()){
			// case insensitive search
			if(permission.getQualifiedName().toLowerCase().contains(searchBar.getText().toLowerCase())){
				TreeItem permissionItem = new TreeItem(tree, SWT.NONE);
				permissionItem.setText(permission.getQualifiedName());
				permissionItem.setData(permission);
				Q methods = Common.universe().nodesTaggedWithAny(permission.getQualifiedName()).retainNodes();
				for (Node method : methods.eval().nodes()) {
					String qualifiedMethodName = StandardQueries.getQualifiedFunctionName(method);
					TreeItem methodItem = new TreeItem(permissionItem, SWT.NONE);
					methodItem.setText(qualifiedMethodName);
					methodItem.setData(method);
					// add call sites of the permission method
					Q methodQ = Common.toQ(Common.toGraph(method));
					Q callEdges = Common.universe().edgesTaggedWithAny(XCSG.Call).retainEdges();
					for (Edge call : callEdges.reverseStep(methodQ).eval().edges()) {
						String qualifiedCallerName = StandardQueries.getQualifiedFunctionName(call.from());
						TreeItem item = new TreeItem(methodItem, SWT.NONE);
						item.setText(qualifiedCallerName);
						item.setData(call);
						permissionItem.setForeground(permissionItem.getDisplay().getSystemColor(PERMISSION_USAGE_COLOR));
						if(expandTreeEnabled) permissionItem.setExpanded(true);
						methodItem.setForeground(methodItem.getDisplay().getSystemColor(PERMISSION_USAGE_COLOR));
						if(expandTreeEnabled) methodItem.setExpanded(true);
						item.setForeground(item.getDisplay().getSystemColor(PERMISSION_USAGE_COLOR));
					}
				}
			}
		}
		
		if(usageFilterEnabled){
			// removes unused items
			for (TreeItem subtree : tree.getItems()) {
				pruneUnusedElements(subtree);
			}
			// color tree back to black
			for (TreeItem subtree : tree.getItems()) {
				colorTree(subtree, SWT.COLOR_BLACK);
			}
		}
		
		tree.update(); // force an update, sometimes the tree needs a hint...
	}
	
	/**
	 * Helper method for repopulating the permission group and protection level subtrees
	 * @param tree
	 * @param detailsText
	 */
	private void repopulatePermissionsTree(final Tree tree, final StyledText detailsText) {
		detailsText.setText(""); // clear out the details view
		tree.removeAll(); // clear the tree contents
		populatePermissionsTree(tree);
	}
	
	/**
	 * Helper method for populating the permission group and protection level subtrees
	 * @param tree
	 */
	private void populatePermissionsTree(final Tree tree) {
		// populate the protection level item sub tree
		TreeItem protectionLevelRootItem = new TreeItem(tree, SWT.NONE);
		protectionLevelRootItem.setText("Protection Level");
		protectionLevelRootItem.setData(null);

		// populate the permission group item sub tree
		TreeItem permissionGroupRootItem = new TreeItem(tree, SWT.NONE);
		permissionGroupRootItem.setText("Permission Group");
		permissionGroupRootItem.setData(null);

		for (ProtectionLevel protectionLevel : ProtectionLevel.getAllProtectionLevels()) {
			TreeItem protectionLevelItem = new TreeItem(protectionLevelRootItem, SWT.NONE);
			protectionLevelItem.setText(protectionLevel.getName());
			protectionLevelItem.setData(protectionLevel);
			// populate the permission for each protection level
			for (Permission permission : protectionLevel.getPermissions()) {
				TreeItem permissionItem = new TreeItem(protectionLevelItem, SWT.NONE);
				permissionItem.setText(permission.getQualifiedName());
				permissionItem.setData(permission);
				// populate the permission methods for each protection level
				boolean hasCallers = populatePermissionMethodsSubtree(permission, permissionItem);
				if (hasCallers) {
					permissionItem.setForeground(permissionItem.getDisplay().getSystemColor(PERMISSION_USAGE_COLOR));
					if(expandTreeEnabled) permissionItem.setExpanded(true);
					protectionLevelItem.setForeground(protectionLevelItem.getDisplay().getSystemColor(PERMISSION_USAGE_COLOR));
					if(expandTreeEnabled) protectionLevelItem.setExpanded(true);
					protectionLevelRootItem.setForeground(protectionLevelRootItem.getDisplay().getSystemColor(PERMISSION_USAGE_COLOR));
					if(expandTreeEnabled) protectionLevelRootItem.setExpanded(true);
				}
			}
		}
		for (PermissionGroup permissionGroup : PermissionGroup.getAllPermissionGroups()) {
			TreeItem permissionGroupItem = new TreeItem(permissionGroupRootItem, SWT.NONE);
			permissionGroupItem.setText(permissionGroup.getQualifiedName());
			permissionGroupItem.setData(permissionGroup);
			// populate the permission for each protection level
			for (Permission permission : permissionGroup.getPermissions()) {
				TreeItem permissionItem = new TreeItem(permissionGroupItem, SWT.NONE);
				permissionItem.setText(permission.getQualifiedName());
				permissionItem.setData(permission);
				// populate the permission methods for each protection level
				boolean hasCallers = populatePermissionMethodsSubtree(permission, permissionItem);
				if (hasCallers) {
					permissionItem.setForeground(permissionItem.getDisplay().getSystemColor(PERMISSION_USAGE_COLOR));
					if(expandTreeEnabled) permissionItem.setExpanded(true);
					permissionGroupItem.setForeground(permissionGroupItem.getDisplay().getSystemColor(PERMISSION_USAGE_COLOR));
					if(expandTreeEnabled) permissionGroupItem.setExpanded(true);
					permissionGroupRootItem.setForeground(permissionGroupRootItem.getDisplay().getSystemColor(PERMISSION_USAGE_COLOR));
					if(expandTreeEnabled) permissionGroupRootItem.setExpanded(true);
				}
			}
		}
		
		if(usageFilterEnabled){
			// removes unused items
			for (TreeItem subtree : tree.getItems()) {
				pruneUnusedElements(subtree);
			}
			// color tree back to black
			for (TreeItem subtree : tree.getItems()) {
				colorTree(subtree, SWT.COLOR_BLACK);
			}
		}
		
		tree.update(); // force an update, sometimes the tree needs a hint...
	}
	
	private String prettyPrintGraphElement(GraphElement ge) throws IOException{
		String tags = ge.tags().toString();
		FormattedSourceCorrespondence sc = FormattedSourceCorrespondence.getSourceCorrespondent(ge);
		if(sc != null){
			String lines = sc.getLineNumbers();
			return  "File: " + sc.getFile()
					+ "\nLine number" + ((lines.contains("-") ? "s: " : ": ") + lines) 
					+ "\nTags: " + tags;
		}
		return ge.toString();
	}

	/**
	 * Helper method to prune the tree for nodes not colored PERMISSION_USAGE_COLOR
	 * @param tree
	 */
	private void pruneUnusedElements(TreeItem tree){
		if(tree.getForeground().equals(tree.getDisplay().getSystemColor(PERMISSION_USAGE_COLOR))){
			// tree has used elements
			for (TreeItem subtree : tree.getItems()) {
				pruneUnusedElements(subtree);
			}
		} else {
			// tree does not have used elements
			tree.removeAll();
			tree.dispose();
		}
	}
	
	/**
	 * Helper method to color a tree or subtree the given color
	 * @param tree
	 * @param color
	 */
	private void colorTree(TreeItem tree, int color){
		tree.setForeground(tree.getDisplay().getSystemColor(color));
		int itemCount = tree.getItemCount();
		for (int i=0; i<itemCount; i++) {
			TreeItem subtree = tree.getItem(i);
			colorTree(subtree, color);
		}
	}
	
	/**
	 * Helper method for populating the permission restricted methods under a permission
	 * @param permission
	 * @param permissionItem
	 * @return returns true if the permission methods are called
	 */
	private boolean populatePermissionMethodsSubtree(Permission permission, TreeItem permissionItem) {
		boolean hasCallsites = false;
		Q methods = Common.universe().nodesTaggedWithAny(permission.getQualifiedName()).retainNodes();
		for (Node method : methods.eval().nodes()) {
			String qualifiedMethodName = StandardQueries.getQualifiedFunctionName(method);
			TreeItem methodItem = new TreeItem(permissionItem, SWT.NONE);
			methodItem.setText(qualifiedMethodName);
			methodItem.setData(method);
			// add call sites of the permission method
			Q methodQ = Common.toQ(Common.toGraph(method));
			Q call = Common.universe().edgesTaggedWithAny(XCSG.Call).retainEdges();
			for (Edge callEdge : call.reverseStep(methodQ).eval().edges()) {
				hasCallsites = true;
				methodItem.setForeground(methodItem.getDisplay().getSystemColor(PERMISSION_USAGE_COLOR));
				if(expandTreeEnabled) methodItem.setExpanded(true);
				String qualifiedCallerName = StandardQueries.getQualifiedFunctionName(callEdge.from());
				TreeItem item = new TreeItem(methodItem, SWT.NONE);
				item.setText(qualifiedCallerName);
				item.setData(callEdge);
				item.setForeground(methodItem.getDisplay().getSystemColor(PERMISSION_USAGE_COLOR));
			}
		}
		return hasCallsites;
	}
	
	

	@Override
	public void setFocus() {}
}