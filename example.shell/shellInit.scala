/*
 * This is an initialization file for the Atlas Shell.  Each time a Shell linked
 * with this project is opened or restarted, the code in this file will be run as scala code.  Below
 * is included the default initialization code for the Shell.  As long as this file exists only
 * the code in this file will be run on Shell startup; this default code will not be run if you
 * remove it from this file.
 * 
 * You do not need to put initialization code in a scala object or class.
 */
import com.ensoftcorp.atlas.core.xcsg.XCSG
import com.ensoftcorp.atlas.core.db.graph._
import com.ensoftcorp.atlas.core.db.graph.operation._
import com.ensoftcorp.atlas.core.db.set._
import com.ensoftcorp.atlas.core.db.view.View
import com.ensoftcorp.atlas.core.db.graph.operation._
import com.ensoftcorp.atlas.core.query.Query
import com.ensoftcorp.atlas.core.query.Q
import com.ensoftcorp.atlas.core.query.Attr
import com.ensoftcorp.atlas.core.query.Attr._
import com.ensoftcorp.atlas.core.query.Attr.Edge
import com.ensoftcorp.atlas.core.query.Attr.Node
import com.ensoftcorp.atlas.core.xcsg.XCSG;

import com.ensoftcorp.atlas.java.core.script.Common._
import com.ensoftcorp.atlas.java.core.script.Common 
import com.ensoftcorp.atlas.java.core.script.CommonQueries 
import com.ensoftcorp.atlas.java.core.script.CommonQueries._ 
import com.ensoftcorp.atlas.core.script.Common._
import com.ensoftcorp.atlas.core.script.Common 
import com.ensoftcorp.atlas.core.script.CommonQueries 
import com.ensoftcorp.atlas.core.script.CommonQueries._ 
import com.ensoftcorp.atlas.core.script.CommonQueries.TraversalDirection
import com.ensoftcorp.atlas.core.script.CommonQueries.TraversalDirection._ 
import com.ensoftcorp.atlas.core.script.UniverseManipulator
import com.ensoftcorp.atlas.ui.shell.lib.Common._
import com.ensoftcorp.atlas.core.highlight._

// color for graph highlighting
import java.awt.Color

// android essentials
import com.ensoftcorp.open.android.essentials._
import com.ensoftcorp.open.android.essentials.permissions._
import com.ensoftcorp.open.android.essentials.permissions.mappings._

// toolbox.analysis project
import toolbox.analysis._