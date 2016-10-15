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

    val akkaHttp: ModuleID         = "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion
    val akka: ModuleID             = "com.typesafe.akka" %% "akka-actor"             % akkaVersion
    val akkaHttpJson4s: ModuleID   = "de.heikoseeberger" %% "akka-http-json4s"       % json4sVersion

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

  val vendingMachineLibs: Seq[ModuleID]  = Seq(akkaHttp, akka, akkaHttpJson4s)
  val testLibs: Seq[ModuleID] = Seq(akkaTestKit, akkaHttpTestKit,scalatestLib)

}
