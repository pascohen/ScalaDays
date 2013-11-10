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
  val description = "Demo plugin"
  val components = List[PluginComponent](FilterComponent)

  private object FilterComponent extends PluginComponent {
    val global: PluginCompiler.this.global.type = PluginCompiler.this.global

    val runsAfter = List[String]("refchecks");

    val phaseName = "filter"
    def newPhase(prev: Phase): Phase = new FilterPhase(prev)

    class FilterPhase(prev: Phase) extends StdPhase(prev) {
      override def name = PluginCompiler.this.name

      def apply(unit: global.CompilationUnit) {
        println("=============== CompilerV33 plugin Handling "+unit)
      }
    }
  }
}
