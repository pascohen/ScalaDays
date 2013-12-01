package test.pcohen.scaladays.compilerplugin

import scala.tools.nsc
import nsc.Global
import nsc.Phase
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent
import scala.tools.nsc.transform.Transform
import scala.tools.nsc.transform.TypingTransformers
import scala.tools.nsc.ast.TreeDSL

class PluginCompiler(val global: Global) extends Plugin {

  val name = "demoplugin"
  override val description = "Demo plugin"
  private[this] val registerComponent = RegisterComponent(name,global)
  private[this] val filterComponent = FilterComponent(name,global)
  private[this] val dslTransformer = DSLTransformerComponent(name,global)
  private[this] val logAdder = LogAdderComponent(name,global)

  val components = List[PluginComponent](registerComponent,filterComponent,dslTransformer,logAdder)
}
