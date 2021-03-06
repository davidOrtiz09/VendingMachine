package co.com.vending.machine.shop.ws

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import co.com.vending.machine.shop.entities._
import spray.json._

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol{

  implicit val productFormat = jsonFormat3(Product)
  implicit val productRequestFormat = jsonFormat2(ProductRequest)
  implicit val payRequestFormat = jsonFormat2(PayRequest)
  implicit val payResponseFormat = jsonFormat4(PayResponse)
  implicit val cancelResposneFormat = jsonFormat2(CancelResposne)


}
