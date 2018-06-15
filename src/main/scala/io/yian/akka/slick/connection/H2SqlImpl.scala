package io.yian.akka.slick.connection

import slick.driver.H2Driver

trait H2SqlImpl {
    val driver = H2Driver
    import driver.api._
    val db: Database = H2SqlDB.connectionPool
}

private[connection] object H2SqlDB {
    import slick.driver.H2Driver.api._
    val connectionPool = Database.forConfig("h2")
}