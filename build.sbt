name := "akka-practice"
version := "1.0"
scalaVersion := "2.11.12"

libraryDependencies ++= {
	Seq(
	"com.typesafe.akka" %% "akka-actor" % "2.5.12",
	
	        // Akka Http
        "com.typesafe.akka" %% "akka-http"   % "10.1.2",
        "com.typesafe.akka" %% "akka-stream" % "2.5.12",

        // Slick
        "com.typesafe.slick" %% "slick" % "3.1.1",
        "com.typesafe.slick" % "slick-hikaricp_2.11" % "3.1.1",
        // Embedded DB => H2
        "com.h2database"  % "h2" % "1.4.197"
	)
}
