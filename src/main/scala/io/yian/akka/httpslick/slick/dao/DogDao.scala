package io.yian.akka.httpslick.slick.dao

import io.yian.akka.httpslick.slick.connection.H2SqlImpl
import io.yian.akka.httpslick.slick.model.{Dog, DogTable}

import scala.concurrent.Future

trait DogDao extends DogTable with H2SqlImpl {
  import driver.api._
  protected val dogTableQuery = TableQuery[DogTable]

  // insert
  def create(dog:Dog):Future[Int] = db.run {
    dogTableAutoInc += dog
  }

  // update
  def update(dog:Dog):Future[Int] = db.run {
    dogTableQuery.filter(_.id === dog.id.get).update(dog)
  }

  // select
  def getById(id:Int):Future[Option[Dog]] = db.run {
    dogTableQuery.filter(_.id === id).result.headOption
  }

  // select all
  def getAll:Future[List[Dog]] = db.run {
    dogTableQuery.to[List].result
  }

  // delete
  def delete(id:Int):Future[Int] = db.run {
    dogTableQuery.filter(_.id === id).delete
  }

  // crate
  def ddl = db.run {
    dogTableQuery.schema.create
  }

  protected def dogTableAutoInc = dogTableQuery returning dogTableQuery.map(_.id)
}
