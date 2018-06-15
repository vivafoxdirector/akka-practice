package io.yian.akka.slick.model

import io.yian.akka.slick.connection.H2SqlImpl
import spray.json.DefaultJsonProtocol

trait DogTable extends DefaultJsonProtocol {
  this: H2SqlImpl =>

  import driver.api._

  implicit lazy val dogFormat = jsonFormat2(Dog)
  implicit lazy val dogListFormat = jsonFormat1(DogList)

  class DogTable(tag:Tag) extends Table[Dog](tag, "dog") {
    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val name = column[String]("name")

    def * = (name, id.?) <>(Dog.tupled, Dog.unapply)
  }
}

case class Dog(name:String, id:Option[Int] = None)
case class DogList(dogs: List[Dog])
