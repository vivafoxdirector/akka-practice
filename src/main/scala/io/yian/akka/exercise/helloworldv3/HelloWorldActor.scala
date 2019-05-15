package io.yian.akka.exercise.helloworldv3

import akka.actor.Actor

/**
  * receive 메소드
  * @param name
  */
class HelloWorldActor(name:String) extends Actor {
  override def preStart = {
    println(name + " is started.")
  }

  /**
    * receive 메소드 결과를 반환하도록 수정한다.
    * 수신받은 메시지를 일부 문자열을 첨가하여 송신측에 송신한다.
    * @return
    */
  override def receive: Receive = {
    case msg : String => {
      // print received msg
      println("HelloWorldActor: Hello world! "+ msg + " My name is " + name)

      // send msg
      //"HelloWorldActor: Hello world! " + msg + " My name is " + name

      // send를 사용하면 Actor가 마지막으로 수신받은 메시지 Akka의 디폴트 Actor인 deadLetters를 설정하고 있다.
      sender ! "HelloWorldActor: Hello world! " + msg + " My name is " + name
    }
  }

  override def postStop = {
    println(name + " is stopped.")
  }
}
