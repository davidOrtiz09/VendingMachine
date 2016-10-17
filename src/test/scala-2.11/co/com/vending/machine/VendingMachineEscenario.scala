package co.com.vending.machine

import akka.actor.ActorSystem
import co.com.vending.machine.aggregate.VendingMachineMaster
import co.com.vending.machine.commons.config.AppConfig
import co.com.vending.machine.commons.data.mock.MockDataBase
import co.com.vending.machine.shop.storage.dao.MockDaoProducts
import co.com.vending.machine.shop.storage.repository.ShopRepository
import co.com.vending.machine.shop.ws.ProductsRoute
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
  private  val dbConfigFake = MockDataBase("error.txt")
  private val  mockDaoProducts = MockDaoProducts(dbConfig)
  private val  mockDaoProductsFake = MockDaoProducts(dbConfigFake)

  val vendingMachineRepository =  ShopRepository(mockDaoProducts)
  val vendingMachineRepositoryFake =  ShopRepository(mockDaoProductsFake)

  private val vendingMachineMasterProps = VendingMachineMaster.props(vendingMachineRepository)

  private val vendingMachineMasterName = appConfig.vendingMachineMasterName

  val vendingMachineMaster = system.actorOf(vendingMachineMasterProps , vendingMachineMasterName)

  val productRoute = ProductsRoute(vendingMachineRepository , vendingMachineMaster).route
  val productRouteFake = ProductsRoute(vendingMachineRepositoryFake , vendingMachineMaster).route

}
