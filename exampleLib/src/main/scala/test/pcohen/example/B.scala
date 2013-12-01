package test.pcohen.example

class B(s:String) extends A(s) {
  override def authorizedTask = super.authorizedTask
  override def unAuthorizedTask = super.unAuthorizedTask
  def trickyTask = super.unAuthorizedTask

  override def authorizedTask2() = super.authorizedTask2()
  override def unAuthorizedTask2() = super.unAuthorizedTask2()
  def trickyTask2() = super.unAuthorizedTask2()
}

object B extends A("") 