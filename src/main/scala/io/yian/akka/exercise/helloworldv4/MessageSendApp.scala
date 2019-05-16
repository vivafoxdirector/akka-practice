package io.yian.akka.exercise.helloworldv4

import akka.actor.{ActorSystem, Props}

// ref: http://kimutansk.hatenablog.com/entry/20140725/1406238670
/**
  * "sender를 사용함으로써 Actor는 가장 마지막 메시지를 보낸 Actor를 참조할 수 있다." 이므로
  * Actor메시지를 받는 대상은 Actor가 된다. 이를 알아보기 위해 부모와 자식 관계를 가진 Actor를 작성해본다.
  */
object MessageSendApp extends App {
  override def main(args: Array[String]) : Unit = {
    val system = ActorSystem.apply("MessageSendApp")
    val childActor = system.actorOf(Props.apply(new ChildActor("child1")))
    val parentActor = system.actorOf(Props.apply(new ParentActor("parent1", childActor)))

    parentActor ! """Test1"""
    parentActor ! """Test2"""

    Thread.sleep(5000)

    system.shutdown()
  }
}
