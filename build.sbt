name := "akka-practice"
version := "1.0"
scalaVersion := "2.11.12"

libraryDependencies ++= {
  val akkaV = "2.4.10"
	Seq(
    // Akka
    "com.typesafe.akka" %% "akka-actor" % akkaV,

    // Akka Http
//    "com.typesafe.akka" %% "akka-http"   % "10.1.2",
//    "com.typesafe.akka" %% "akka-stream" % akkaV,

    // Akka Http Experimental
    "com.typesafe.akka" %% "akka-http-experimental" % akkaV,

    // Akka Http Json
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaV,

    // Slick
    "com.typesafe.slick" %% "slick" % "3.1.1",
    "com.typesafe.slick" % "slick-hikaricp_2.11" % "3.1.1",

    // Embedded DB => H2
    "com.h2database"  % "h2" % "1.4.197",

    // Slf4j
    "org.slf4j" % "slf4j-nop" % "1.6.4"
	)
}
