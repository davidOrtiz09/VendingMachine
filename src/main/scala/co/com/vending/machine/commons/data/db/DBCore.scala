package co.com.vending.machine.commons.data.db

import co.com.vending.machine.commons.data.mock.MockDataBase
import co.com.vending.machine.core.{BootedCore, Core}
import co.com.vending.machine.shop.storage.dao.MockDaoProducts
import co.com.vending.machine.shop.storage.repository.ShopRepository

/**
  * Initialize all the DAOs an repos using in the system
  */
trait DBCore {

  this: Core with BootedCore =>

  import system.dispatcher

  private val fileName = appConfig.dataBaseFileName

  private  val dbConfig = MockDataBase(fileName)
  private val  mockDaoProducts = MockDaoProducts(dbConfig)

  val vendingMachineRepository =  ShopRepository(mockDaoProducts)

}
