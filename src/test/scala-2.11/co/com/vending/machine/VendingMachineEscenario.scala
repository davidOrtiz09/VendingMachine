package co.com.vending.machine

import akka.actor.ActorSystem
import co.com.vending.machine.commons.config.AppConfig
import co.com.vending.machine.commons.data.mock.MockDataBase
import co.com.vending.machine.shop.storage.dao.MockDaoProducts
import co.com.vending.machine.shop.storage.repository.ShopRepository
import com.typesafe.config.ConfigFactory

/**
  * Main escenario to test the app
  */
object VendingMachineEscenario {

  implicit val appConfig: AppConfig  = AppConfig(ConfigFactory.load())
  implicit val system: ActorSystem = ActorSystem("MainVendingMachine-System")
  import system.dispatcher

  private val fileName = appConfig.dataBaseFileName

  private  val dbConfig = MockDataBase(fileName)
  private val  mockDaoProducts = MockDaoProducts(dbConfig)

  val vendingMachineRepository =  ShopRepository(mockDaoProducts)

}
