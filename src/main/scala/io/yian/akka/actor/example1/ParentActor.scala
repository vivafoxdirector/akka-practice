package io.yian.akka

import akka.actor.{ActorSystem, Actor};
import akka.actor.Props
import akka.event.Logging

// ref: http://kimutansk.hatenablog.com/entry/20140725/1406238670
class ParentActor(name:String, child:ActorRef) extends Actor {
}

class ChildActor(name:String) extends Actor {
}

class HelloWorldActor(name:String) extends Actor {
  // Actor initialize
  override def preStart = { println(name + " is started.")}

  // message received
  def receive = {
    case msg:String => {
      println("HelloWorldActor:Hello World!" + msg + " My name is "+ name)
      "HelloWorldActor: Hello world!" + msg + " My name is " + name
    }
  }

  override def postStop = { println(name + " is stopped.") }
}

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
