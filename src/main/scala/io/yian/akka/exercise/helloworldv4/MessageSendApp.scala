package io.yian.akka.exercise.helloworldv3

import akka.actor.{ActorSystem, Props}

// ref: http://kimutansk.hatenablog.com/entry/20140725/1406238670
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
