import akka.actor.{Actor, ActorRef, ActorSystem, Props, Terminated}
import akka.pattern.ask
import akka.util.Timeout
import akka.event.Logging

import scala.concurrent.duration._
import scala.concurrent.Future
import scala.concurrent.Await

// https://www.qoosky.io/techs/e0f555bc87
class MyActorTerminate extends Actor {
  val log = Logging(context.system, this) // 로그
  
  // 자식 스레드를 생성하고 감시, 장애시 정지는 탐지하지 못한다. 정상 종료만 탐지함.
  val child = context.actorOf(Props[MyActorTerminate2], name="myChild")
  context.watch(child)

  // 의뢰
  var lastSender: ActorRef = context.system.deadLetters // deadLetters은 '/dev/null'과 같다.

  def receive = {
    case "kill" => {
      context.stop(child)   // 정지
      lastSender = sender   // 답신 받을 액터
    }
    case Terminated(`child`) => {
      // 변수로 인식되지 않도록 `을 사용
      lastSender ! "finished"
    }
    case _ => {
      context.stop(self)    // 자신을 중지하게 할 수 있다.
    }
  }
}

class MyActorTerminate2 extends Actor {
  def receive = {
    case _ => {
    }
  }
}

object MyActorTerminateMain {
  def main(args:Array[String]):Unit = {
    val system = ActorSystem("mySystem")

    // 액터 설정
    val props = Props[MyActorTerminate]

    // 액터 생성. ActorRef 반환
    val actor = system.actorOf(props, name = "myActor")
    Thread.sleep(1000)

    // 자식 스레드 정지 요청
    implicit val timeout = Timeout(5 seconds) // ? 암묵적 필요 정보

    val future: Future[Any] = actor ? "kill"
    // val future: Future[String] = ask(actor, "kill").mapTo[String] // 위와 같다.

    // 동기 처리(비동기가 아니다. 이부분에서 처리가 종료)
    val result = Await.result(future, timeout.duration).asInstanceOf[String]

    println(result)

    // 액터 정지
    system.stop(actor)
    Thread.sleep(1000)

    // 시스템 정지 (`system.shutdown`은 권장하지 않는다.
    system.terminate
  }
}
