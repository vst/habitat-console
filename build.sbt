// Define versions for libraries:
val VersionCats              = "1.4.0"
val VersionCatsEffect        = "1.0.0"
val VersionCatsTaglessMacros = "0.1.0"
val VersionScalameta         = "3.0.0-M11"

// Configure the root project:
lazy val root = (project in file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    // Top-level Settings:
    name := "habitat-console",
    organization := "com.vsthost.rnd",
    scalaVersion := "2.12.7",
    version := "0.1.0-SNAPSHOT",

    // Scalac Options:
    scalacOptions += "-deprecation",
    scalacOptions += "-Xplugin-require:macroparadise",
    scalacOptions in (Compile, console) := Seq(),

    // BuildInfo Settings:
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
    buildInfoPackage := "com.vsthost.rnd.habitat.console",

    // Compiler plugins:
    addCompilerPlugin(("org.scalameta" % "paradise" % VersionScalameta).cross(CrossVersion.full)),

    // Libraries:
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core"           % VersionCats,
      "org.typelevel" %% "cats-effect"         % VersionCatsEffect,
      "org.typelevel" %% "cats-tagless-macros" % VersionCatsTaglessMacros,
    )
  )
