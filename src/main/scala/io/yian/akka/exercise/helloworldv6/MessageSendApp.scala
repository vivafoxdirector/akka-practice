package io.yian.akka.exercise.helloworldv6

import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinRoutingLogic

import scala.collection.immutable

// ref: http://kimutansk.hatenablog.com/entry/20140725/1406238670
/**
  * "sender를 사용함으로써 Actor는 가장 마지막 메시지를 보낸 Actor를 참조할 수 있다." 이므로
  * Actor메시지를 받는 대상은 Actor가 된다. 이를 알아보기 위해 부모와 자식 관계를 가진 Actor를 작성해본다.
  */
object MessageSendApp extends App {
  override def main(args: Array[String]) : Unit = {
    val system = ActorSystem.apply("MessageSendApp")
    val childActor1 = system.actorOf(Props.apply(new ChildActor("child1")))
    val childActor2 = system.actorOf(Props.apply(new ChildActor("child2")))
    val childActor3 = system.actorOf(Props.apply(new ChildActor("child3")))
    val seq = immutable.IndexedSeq(childActor1, childActor2, childActor3)

    val routingLogic = new RoundRobinRoutingLogic

    val parentActor = system.actorOf(Props.apply(new ParentActor("parent1", seq, routingLogic)))

    parentActor ! """Test1"""
    parentActor ! """Test2"""
    parentActor ! """Test3"""
    parentActor ! """Test4"""

    Thread.sleep(5000)

    system.shutdown()
  }
}
