import test.pcohen.example._

object Test extends App {
val d = DemoApi("a")
d.doSomeJob("t")
val s = d.getState
}
