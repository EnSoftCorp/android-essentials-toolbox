/*
 * This is an initialization file for the Atlas Shell.  Each time a Shell linked
 * with this project is opened or restarted, the code in this file will be run as scala code.  Below
 * is included the default initialization code for the Shell.  As long as this file exists only
 * the code in this file will be run on Shell startup; this default code will not be run if you
 * remove it from this file.
 * 
 * You do not need to put initialization code in a scala object or class.
 */
import com.ensoftcorp.atlas.java.core.query.Q
import com.ensoftcorp.atlas.java.core.query.Attr
import com.ensoftcorp.atlas.java.core.query.Attr.Edge
import com.ensoftcorp.atlas.java.core.query.Attr.Node
import com.ensoftcorp.atlas.java.core.script.Common._
import com.ensoftcorp.atlas.java.interpreter.lib.Common._
import com.ensoftcorp.atlas.core.db.Accuracy._
import com.ensoftcorp.atlas.core.db.graph.Graph

// color for graph highlighting
import java.awt.Color

// android essentials
import com.ensoftcorp.open.android.essentials._
import com.ensoftcorp.open.android.essentials.permissions._
import com.ensoftcorp.open.android.essentials.permissions.mappings._

// toolbox.analysis project
import toolbox.analysis._