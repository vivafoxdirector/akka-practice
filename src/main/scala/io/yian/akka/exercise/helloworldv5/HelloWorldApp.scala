package io.yian.akka.exercise.helloworldv5

import java.util.concurrent.TimeUnit

import akka.pattern.ask
import akka.actor.{ActorSystem, Props}
import akka.util.Timeout

import scala.concurrent.Await

/**
  * 단 sender를 사용하지 않고 단순히 결과를 반환하는 경우 응답은 호출한 곳에 보내지 않고
  * 호출한 곳에서 타임아웃이 발생된다.
  *
  * 비동기를 구현한 것이다.
  * ref: http://kimutansk.hatenablog.com/entry/20140726/1406330944
  */
object HelloWorldApp extends App {
    override def main(args: Array[String]): Unit = {
        val system = ActorSystem("HelloWorldApp")
        val helloWorldActor = system.actorOf(Props.apply(new HelloWorldActor("actor1")), "HelloWorldActor")
        implicit val timeout = Timeout(5000, TimeUnit.MILLISECONDS) // ? 실행시 암묵적 타임아웃 설정
    
        // ? 사용하면 메시지를 보내면 응답을 받을 수 있다.
        // ! 경우는 응답을 보내기만 하고 받지를 않기 때문에 1통의 메시지는 deadLetters로 가게 되는것을 알 수 있다.
        val futureTest1 = helloWorldActor ? """Test1"""
        val futureTest2 = helloWorldActor ? """ Test2"""
        val unitTest3 = helloWorldActor ! """Test3"""
    
        println("Test future is " + futureTest1)
        val resultTest1 = Await.result(futureTest1, timeout.duration).asInstanceOf[String]
        println("Test1 future is " + resultTest1)
    
        println("Test2 future is " + futureTest2)
        val resultTest2 = Await.result(futureTest2, timeout.duration).asInstanceOf[String]
        println("Test2 result is " + resultTest2)
    
        println("Test3 unit is " + unitTest3)
        println("Test3 unit class is " + unitTest3.getClass)
    
        Thread.sleep(5000)
        system.shutdown()
    }
}
