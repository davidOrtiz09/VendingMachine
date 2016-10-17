package co.com.vending.machine.shop.storage.repository


import co.com.vending.machine.shop.storage.dao.MockDaoProducts
import co.com.vending.machine.shop.entities._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Main vending machine repository
  */
case class ShopRepository(mockDao:MockDaoProducts) {


  /**
    * fetch all the products in the vending machine
    * @param ec: System's execution context
    * @return
    */
  def getAllProducts(implicit  ec: ExecutionContext):Future[List[Product]] = mockDao.findAll.map( _.toList.map( x => ProductCompanion.apply(x)))

  /**
    * Get the selected product
    * @param code: Product id
    * @param ec: System's execution context
    * @return
    */
  def getProductByCode(code:String)(implicit  ec: ExecutionContext):Future[Option[Product]] = mockDao.findById(code).map(_.map( x => ProductCompanion.apply(x)))

}
