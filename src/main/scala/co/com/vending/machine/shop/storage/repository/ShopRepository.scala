package co.com.vending.machine.shop.storage.repository


import co.com.vending.machine.shop.storage.dao.MockDaoProducts
import co.com.vending.machine.shop.entities.Product

import scala.concurrent.{ExecutionContext, Future}

/**
  * Main vending machine repository
  */
case class ShopRepository(mockDao:MockDaoProducts) {


  def getAllProducts(implicit  ec: ExecutionContext):Future[List[Product]] = mockDao.findAll.map( _.toList.map(Product(_)))

  def getProductByCode(code:String)(implicit  ec: ExecutionContext):Future[Option[Product]] = mockDao.findById(code).map(_.map(Product(_)))

}
