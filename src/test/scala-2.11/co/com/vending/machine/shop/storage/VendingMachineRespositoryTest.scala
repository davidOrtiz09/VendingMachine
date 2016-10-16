package co.com.vending.machine.shop.storage

import co.com.vending.machine.VendingMachineEscenario._
import co.com.vending.machine.shop.entities.Product
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}

/**
  * Persistence layer test cases
  */
class VendingMachineRespositoryTest  extends WordSpec with Matchers with ScalaFutures with BeforeAndAfter{

 import system.dispatcher

  "The vendig machine repository " should {

    val products1 = Product("145692","coke",15)
    val products2 = Product("845327","pepsi",15)
    val products3 = Product("761234","ham & cheese sandwich",30)
    val products4 = Product("789991","peanut",5)
    val products5 = Product("100012","chocolate bar",10)

    val products = List(products1,products2,products3,products4,products5)


    "Fetch all the products in the system" in {

      val allProducts  = vendingMachineRepository.getAllProducts
      allProducts.futureValue shouldEqual products
    }

    "obtain a coke from the vending machine" in {
      val cokeCode = "145692"

      val coke  = vendingMachineRepository.getProductByCode(cokeCode)
     coke.futureValue shouldEqual Option(products1)

    }

    "obtain peanut from the vending machine" in {
      val peanutCode = "789991"

      val peanut  = vendingMachineRepository.getProductByCode(peanutCode)
      peanut.futureValue shouldEqual Option(products4)

    }

    "obtain a not existing big mac from the vending machine" in {
      val bigMacCode = "113456"

      val bigmac  = vendingMachineRepository.getProductByCode(bigMacCode)
      bigmac.futureValue shouldEqual Option.empty

    }
  }

}
