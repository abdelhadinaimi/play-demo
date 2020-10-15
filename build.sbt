
lazy val api = (project /*.dependsOn(allModules: _*)*/ in file("."))
  .enablePlugins(PlayScala)
  .aggregate(common, submodule1)
  .dependsOn(common, submodule1)
  .settings(
    name := """api""",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )

lazy val common = project.in(file("modules/common"))
//lazy val views = project.in(file("modules/views")).enablePlugins(PlayScala)
lazy val model = project.in(file("modules/model")).enablePlugins(PlayScala)
lazy val submodule1 = project.in(file("modules/submodule1"))
  .enablePlugins(PlayScala)
  .dependsOn(common, model)
  .aggregate(common, model)
