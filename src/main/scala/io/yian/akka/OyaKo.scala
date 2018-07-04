package io.yian.akka

import akka.actor.{ActorSystem, Actor, ActorRef};
import akka.actor.Props
import akka.event.Logging

// ref: http://kimutansk.hatenablog.com/entry/20140725/1406238670
class ParentActor(name:String, child:ActorRef) extends Actor {
  def receive = {
    case msg:String => {
      println("ParentActor:Received String" + msg + " My name is " + name)
      child ! "Hello World!" + msg + " My name is " + name
    }
    case msg:Int => {
      println("ParentActor:Received Int" + msg + " My name is  " + name)
    }
  }
}

class ChildActor(name:String) extends Actor {
  def receive = {
    case msg:String => {
      val message = "ChildActor: Received String " + msg + " My name is " + name
      println(message)
      sender ! message.length
    }
  }
}

object OyaKo extends App {
  override def main(args: Array[String]) : Unit = {
    val system = ActorSystem.apply("OyaKoApp")
    val childActor = system.actorOf(Props.apply(new ChildActor("child1")))
    val parentActor = system.actorOf(Props.apply(new ParentActor("parent1", childActor)))

    parentActor ! """Test1"""
    parentActor ! """Test2"""

    Thread.sleep(5000)
    system.shutdown()
  }
}
