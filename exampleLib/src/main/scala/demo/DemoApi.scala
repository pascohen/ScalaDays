package demo

trait DemoApi {
  def method(s:String):Unit
  def getState:String
}

object DemoApi {
  def apply(s:String) = new DemoApiImpl(s)
}

class DemoApiImpl(val s:String) extends DemoApi {
  def method(m:String) = println("Method method - instance_value="+s+"/arg="+m)
  def getState = "Ok"
}