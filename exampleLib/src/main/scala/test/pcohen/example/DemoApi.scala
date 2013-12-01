package test.pcohen.example

trait DemoApi {
  def doSomeJob(s:String):Unit
  def getState:String
}

object DemoApi {
  def apply(s:String) = new DemoApiImpl(s)
}

class DemoApiImpl(val s:String) extends DemoApi {
  def doSomeJob(m:String) = println("Handling "+m+" in "+s+" instance")
  def getState = "Ok"
}