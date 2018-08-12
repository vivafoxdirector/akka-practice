package io.yian.akka.exam2

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

// https://www.qoosky.io/techs/e0f555bc87
class MyActor extends Actor {
  val log = Logging(context.system, this) // 로그

  // 액터내에서 액터를 생성할 수 있다.
  val child = context.actorOf(Props(classOf[MyActor2], "myArgs3", "myArgs4"), name = "myChild")

  def receive = {
    case s: String => {
      log.info("received: %s" format s)
      child ! s
    }
    case _ => {
    }
  }
}

class MyActor2(arg1:String, arg2:String) extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case s: String => {
      log.info("args: %s, %s, received: %s" format (arg1, arg2, s))
    }
    case _ => {
    }
  }
}

object Main {
  def main(args:Array[String]):Unit = {
    // 무거운 처리. 어플리케이션내에 하나만 만들도록 한다.
    val system = ActorSystem("mySystem")

    // 액터 설정. immutable하도록 하여 스레드 안전
    val props1 = Props[MyActor]
    val props2 = Props(classOf[MyActor2], "myArg1", "myArgs2")

    // 액터 생성. ActorRef 반환
    val actor1 = system.actorOf(props1, name = "myActor1")
    val actor2 = system.actorOf(props2, name = "myActor2")

    while(true) {
      actor1 ! "hi actor1"
      actor2 ! "hi actor2"
      Thread.sleep(1000)
    }
  }
}
