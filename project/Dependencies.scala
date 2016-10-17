import sbt._

/**
  * Manage all the dependencies of the app
  */
object Dependencies {


  /**
    * Libraries used to compile the project
    */
  private[Dependencies] object Compile {

    import Versions._

    val akkaHttp: ModuleID         = "com.typesafe.akka" %% "akka-http-experimental"                   % akkaHttpVersion
    val akka: ModuleID             = "com.typesafe.akka" %% "akka-actor"                               % akkaVersion
    val akkaHttpSpray: ModuleID   = "com.typesafe.akka" %% "akka-http-spray-json-experimental"         % akkaHttpSprayVersion
    val configUtil: ModuleID       = "com.typesafe"       % "config"                                   % utilConfigVersion

  }


  /**
    * Libraries used only for testing
    */
  private[Dependencies] object TestDep {

    import Versions._

    val akkaTestKit: ModuleID      = "com.typesafe.akka" %% "akka-testkit"      % akkaVersion % Test
    val akkaHttpTestKit: ModuleID =  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpTestVersion % Test

    val scalatestLib: ModuleID     = "org.scalatest"     %% "scalatest"         % scalaTestVersion % Test
  }

  import Dependencies.Compile._
  import Dependencies.TestDep._

  val vendingMachineLibs: Seq[ModuleID]  = Seq(akkaHttp, akka, akkaHttpSpray , configUtil)
  val testLibs: Seq[ModuleID] = Seq(akkaTestKit, akkaHttpTestKit,scalatestLib)

}
