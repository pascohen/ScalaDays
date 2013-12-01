package test.pcohen.scaladays.compilerplugin

import scala.tools.nsc.plugins.PluginComponent
import scala.tools.nsc.Global
import scala.reflect.internal.Phase
import scala.tools.nsc.transform.Transform

object DSLTransformerComponent {
  def apply(name: String, global: Global) = new DSLTransformerComponent(name, global)
}

class DSLTransformerComponent(val name: String, val global: Global) extends PluginComponent with Transform {

  val runsAfter = List[String]("parser");
  override val description = "Enhance syntax for DSL component"

  val phaseName = "dslTransform"

  def newTransformer(unit: global.CompilationUnit) = new DSLTransformer(unit)

  class DSLTransformer(unit: global.CompilationUnit) extends global.Transformer {
    import global._

    def transformTree: PartialFunction[Tree, Tree] = {
      case Apply(Select(x, TermName("assign_to")), List(Ident(TermName(name)))) =>
        ValDef(Modifiers(), TermName(name), TypeTree(), x)
      case Apply(Select(x, TermName("$minus$minus$greater")), List(Ident(TermName(name)))) =>
        ValDef(Modifiers(), TermName(name), TypeTree(), x)
      case Apply(Select(x, TermName("$eq$eq$greater")), List(Ident(TermName(name)))) =>
        ValDef(Modifiers(), TermName(name), TypeTree(), x)
    }

    def handleList(item: List[Tree]): List[Tree] = {
      item.map(it => handle(it))
    }

    def handle(item: Tree): Tree = {
      if (transformTree.isDefinedAt(item)) {
        transformTree(item)
      } else item match {
        case LabelDef(a, b, c) => LabelDef(a, b, handle(c))
        case _ => item
      }
    }

    def postTransform(tree: global.Tree): global.Tree = {
      tree match {
        case Apply(fun, _) =>
          handle(tree)
        case _ => tree
      }
    }

    override def transform(tree: global.Tree): global.Tree = {
      postTransform(super.transform(tree))
    }
  }
}