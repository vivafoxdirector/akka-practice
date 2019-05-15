package io.yian.akka.exercise.helloworldv2

import akka.actor.Actor

class HelloWorldActor(name:String) extends Actor {
  /**
    * Actor 초기화 처리
    */
  override def preStart = {
    println(name + " is started.")
  }
  
  /**
    * 메시지 수신 처리
    * @return
    */
  override def receive: Receive = {
    case msg : String => {
      // 파라메타로 넘긴 name도 가져와서 출력하는것도 가능하다.
      println("HelloWorldActor: Hello world! "+ msg + " My name is " + name)
    }
  }
  
  /**
    * Actor 종료시 처리
    */
  override def postStop = {
    println(name + " is stopped.")
  }
}
