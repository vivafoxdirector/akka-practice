package io.yian.akka.httpslick.slick.connection

import slick.driver.H2Driver

// 참조: https://qiita.com/yabaiwebyasan/items/a4a8be77f39d4a357093
trait H2SqlImpl {
    val driver = H2Driver
    import driver.api._
    val db: Database = H2SqlDB.connectionPool
}

private[connection] object H2SqlDB {
    import slick.driver.H2Driver.api._
    val connectionPool = Database.forConfig("h2")
}