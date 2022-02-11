ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.1"

lazy val root = (project in file("."))
  .settings(
    name := "httpthis",
    idePackagePrefix := Some("com.francobatta.httpthis")
  )

libraryDependencies += "org.typelevel" %% "cats-core" % "2.7.0"
