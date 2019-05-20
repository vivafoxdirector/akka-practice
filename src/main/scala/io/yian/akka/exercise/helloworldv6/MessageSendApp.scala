package io.yian.akka.exercise.helloworldv6

import akka.actor.{ActorSystem, Props}
import akka.routing.RoundRobinRoutingLogic

import scala.collection.immutable

/**
  * Router는 Routing을 하는 Logic클래스와 Routing되는 RouteeList가 필요하다
  * ref: http://kimutansk.hatenablog.com/entry/20140725/1406238670
  */
object MessageSendApp extends App {
  override def main(args: Array[String]) : Unit = {
    val system = ActorSystem.apply("MessageSendApp")
    val childActor1 = system.actorOf(Props.apply(new ChildActor("child1")))
    val childActor2 = system.actorOf(Props.apply(new ChildActor("child2")))
    val childActor3 = system.actorOf(Props.apply(new ChildActor("child3")))
    // 실행을 하면 child1, child2, child3순으로 송신된다.
    val seq = immutable.IndexedSeq(childActor1, childActor2, childActor3)

    val parentActor = system.actorOf(Props.apply(new ParentActor("parent1", seq, new RoundRobinRoutingLogic)))

    parentActor ! """Test1"""
    parentActor ! """Test2"""
    parentActor ! """Test3"""
    parentActor ! """Test4"""

    Thread.sleep(5000)

    system.shutdown()
  }
}
