package co.com.vending.machine.shop.storage.repository

import co.com.vending.machine.commons.config.AppConfig
import co.com.vending.machine.shop.storage.dto.ProductDTO

/**
  * Created by David on 16/10/16.
  */
case class ShopRepository(appConfig: AppConfig) {


  def getAllProducts:List[ProductDTO] = ???

}
