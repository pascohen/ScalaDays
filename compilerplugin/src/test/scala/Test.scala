import demo.DemoApi

object Test extends App {
val d = DemoApi("a")
d.method("t")
val s = d.getState
}
