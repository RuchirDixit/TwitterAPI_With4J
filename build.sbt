name := "Twitter_Streaming_withScala"

version := "0.1"

scalaVersion := "2.13.4"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies += "com.danielasfregola" %% "twitter4s" % "7.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime
libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "4.0.7"
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.7.0"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.8.0",