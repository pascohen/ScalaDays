package test.pcohen.scaladays.demo

import scala.tools.nsc._
import scala.tools.nsc.interpreter._

class Evaluator(stream: java.io.PrintStream) {

  def this() = this(System.out)  

  val settings = new Settings()
  settings.usejavacp.value = true

  lazy val interpreter = createInterpreter
  
  def addImport(s:String):Unit = addImport(List(s))
  
  def addImport(l:List[String]) = l foreach { i => eval("import "+i)}

  def bind(s:String, t:String, o:Object) {
    interpreter.bind(s,t,o)
  }

  def eval(s: String) = {
      interpreter.eval(s)
  }

  def interpret(s: String) = {
      interpreter.interpret(s)
  }

  def withPluginsDir(dir: String) = {
    settings.pluginsDir.value = dir
    this
  }

  def withPluginOption(s: String) = {
    settings.pluginOptions.appendToValue(s)
    this
  }

  def withContinuations() = {
    withPluginOption("continuations:enable")
  }

  def addPlugins(plugins: String*) = {
    plugins foreach { plugin => settings.plugin.appendToValue(plugin) }
    this
  }

  
  def withLibsDir(dir: String) = withLibsDirs(dir)

  def withLibsDirs(dirs: String*) = {
    var bootcpList = List[String]()
    dirs foreach { dir => 
      for(e <- new java.io.File(dir).list.filter(_.endsWith(".jar"))) {
        bootcpList = (dir+System.getProperty("file.separator")+e)::bootcpList 
      }
    }
    val bootcp = bootcpList.mkString(System.getProperty("path.separator"))
    println("Add "+bootcp+" in cp")
    settings.bootclasspath.value=bootcp
    this
  }

  private[this] val oldOut = System.out
  private[this] val oldErr = System.err
  
  private[this] val oldCOut = Console.out
  private[this] val oldCErr = Console.err

  private[this] def createInterpreter = {
    System.setOut(stream)
    System.setErr(stream)

    Console.setOut(stream)
    Console.setErr(stream)
    
    new scala.tools.nsc.interpreter.IMain(settings)
  }
  
  def close() = {
    interpreter.close()
    System.setOut(oldOut)
    System.setErr(oldErr)

    Console.setOut(oldCOut)
    Console.setErr(oldCErr)
  }
  
}