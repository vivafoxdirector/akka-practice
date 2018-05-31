import akka.actor._

class HotSwapActor extends Actor {
  import context._
  def angry: Receive = {
    case "foo" => sender() ! "! am already antry?"
    case "bar" => become(happy)
  }

  def happy: Receive = {
    case "bar" => sender() ! "I am already happy :-)"
    case "foo" => become(angry)
  }

  def receive = {
    case "foo" => become(angry)
    case "bar" => become(happy)
  }
}
