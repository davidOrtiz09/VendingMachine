package co.com.vending.machine.core

import akka.actor.ActorSystem
import co.com.vending.machine.aggregate.VendingMachineMaster
import co.com.vending.machine.commons.config.AppConfig
import co.com.vending.machine.commons.data.db.DBCore
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Initialaze all the main actors of the system
  */
trait ActorsCore {
  this: Core with BootedCore with DBCore =>

  private val vendingMachineMasterProps = VendingMachineMaster.props(vendingMachineRepository)

  private val vendingMachineMasterName = appConfig.vendingMachineMasterName

  val vendingMachineMaster = system.actorOf(vendingMachineMasterProps , vendingMachineMasterName)

}


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

