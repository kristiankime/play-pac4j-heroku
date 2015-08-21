name := """play-2_4-pac4j-heroku"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
//  jdbc,
  cache,
  ws,
  specs2 % Test
)

// Slick
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "1.0.1",
  "com.typesafe.play" %% "play-slick" % "1.0.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "1.0.1",
  "com.h2database" % "h2" % "1.4.188"
)

// Pac4j Libs
libraryDependencies ++= Seq(
  "org.pac4j" %% "play-pac4j-scala" % "1.5.0",
  "org.pac4j" % "pac4j-http" % "1.7.0",
  "org.pac4j" % "pac4j-cas" % "1.7.0",
  "org.pac4j" % "pac4j-openid" % "1.7.0",
  "org.pac4j" % "pac4j-saml" % "1.7.0",
  "org.pac4j" % "pac4j-gae" % "1.7.0",
  "org.pac4j" % "pac4j-oidc" % "1.7.0",
  "org.pac4j" % "pac4j-oauth" % "1.7.0"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
