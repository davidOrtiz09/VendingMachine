package co.com.vending.machine.core

import akka.actor.ActorSystem
import co.com.vending.machine.commons.config.AppConfig
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import scala.concurrent.duration.Duration


/**
  * Initialaze all the core components of the system
  */
trait BootedCore extends Core {

  implicit val appConfig: AppConfig  = AppConfig(ConfigFactory.load())
  implicit val system: ActorSystem = ActorSystem("MainVendingMachine-System")

  sys.addShutdownHook {
    Await.ready(system.terminate(), Duration.Inf)
    ()
  }
}

/**
  * Define all the core components of the system.
  */
trait Core {

  implicit def appConfig: AppConfig

  implicit def system: ActorSystem
}

