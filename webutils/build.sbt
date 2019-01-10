name := "Web utilities"

resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith", "maven")

libraryDependencies ++= Seq(
  "io.github.neelsmith" %% "btreporter" % "2.4.0",
  "com.github.nscala-time" %% "nscala-time" % "2.16.0"
)
