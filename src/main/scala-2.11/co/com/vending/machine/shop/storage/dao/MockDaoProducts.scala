package co.com.vending.machine.shop.storage.dao

import co.com.vending.machine.commons.data.db.DAO
import co.com.vending.machine.commons.data.mock.MockDataBase
import co.com.vending.machine.shop.storage.dto.ProductDTO

import scala.concurrent.{ExecutionContext, Future}

/**
  * Mock data product DAO
  */
case class MockDaoProducts(dbConfig: MockDataBase)(implicit val ec: ExecutionContext) extends DAO[String ,ProductDTO]{
  private val db = dbConfig.loadAllItems

   def findAll: Future[Seq[ProductDTO]] = Future{db}

   def findById(id: String): Future[Option[ProductDTO]] = Future{db.find(_.code == id)}
}
