import test.pcohen.example._

object Test2 extends App {

  val a = new A("a")
  val b = new B("b")

  a.authorizedTask
  a.unAuthorizedTask

  a.authorizedTask2()
  a.unAuthorizedTask2()

  a.authorizedTask2
  a.unAuthorizedTask2

  b.authorizedTask
  b.unAuthorizedTask
  b.trickyTask

  b.authorizedTask2()
  b.unAuthorizedTask2()
  b.trickyTask2()

  b.authorizedTask2
  b.unAuthorizedTask2
  b.trickyTask2

  A.authorizedObjectTask
  A.unAuthorizedObjectTask

  A.authorizedObjectTask2()
  A.unAuthorizedObjectTask2()

  A.authorizedObjectTask2
  A.unAuthorizedObjectTask2

  B.authorizedTask
  B.unAuthorizedTask

  B.authorizedTask2()
  B.unAuthorizedTask2()

  B.authorizedTask2
  B.unAuthorizedTask2

  val d = System.currentTimeMillis()
  System.exit(-1)
}
