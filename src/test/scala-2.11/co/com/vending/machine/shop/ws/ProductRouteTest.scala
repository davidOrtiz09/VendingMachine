package co.com.vending.machine.shop.ws

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}
import co.com.vending.machine.VendingMachineEscenario._
import co.com.vending.machine.shop.entities.Product
/**
  * Product Route test case
  */
class ProductRouteTest extends WordSpec with Matchers with ScalatestRouteTest with JsonSupport{

  "The product service" should {
    val products1 = Product("145692","coke",15)
    val products2 = Product("845327","pepsi",15)
    val products3 = Product("761234","ham & cheese sandwich",30)
    val products4 = Product("789991","peanut",5)
    val products5 = Product("100012","chocolate bar",10)

    val products = List(products1,products2,products3,products4,products5)

    "return a product for GET requests to the root path" in {
      val cokeCode = "145692"
      // tests:
      Get("/" + appConfig.productRoute + "/" + cokeCode) ~> productRoute ~> check {
        status === StatusCodes.OK
        responseAs[Product] shouldEqual products1
      }
    }

    "return an error of a non existing product for GET requests to the root path" in {
      val noneCode = "0000"
      // tests:
      Get("/" + appConfig.productRoute + "/" + noneCode) ~> productRoute ~> check {
        status === StatusCodes.custom(404 ,"PRODUCT NOT FOUND","PRODUCT NOT FOUND")
      }
    }

    "return all the product for GET requests to the root path" in {

      // tests:
      Get("/" + appConfig.productRoute) ~> productRoute ~> check {
        status === StatusCodes.OK
        responseAs[List[Product]] shouldEqual products
      }
    }

    "return an error due to empty vending machine for GET requests to the root path" in {

      // tests:
      Get("/" + appConfig.productRoute) ~> productRouteFake ~> check {

        status === StatusCodes.custom(404 ,"NOT PRODUCTS AVAILABLE","NOT PRODUCTS AVAILABLE")

      }
    }
  }

}
