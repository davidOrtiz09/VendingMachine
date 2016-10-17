package co.com.vending.machine.shop.ws

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import co.com.vending.machine.shop.entities.Product
import spray.json._

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol{

  implicit val productFormat = jsonFormat3(Product)


}
