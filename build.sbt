name := """play-pac4j-heroku"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.pac4j"         % "play-pac4j_scala2.11" % "1.4.0",
  "org.pac4j"         % "pac4j-oauth"          % "1.7.0"
)
