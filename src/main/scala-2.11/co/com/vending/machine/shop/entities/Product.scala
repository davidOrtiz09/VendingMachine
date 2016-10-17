package co.com.vending.machine.shop.entities

import co.com.vending.machine.shop.storage.dto.ProductDTO


object  ProductCompanion {

  def apply(dto: ProductDTO):Product = {
    Product(dto.code,dto.name,dto.cost)
  }
}

/**
  * Business entity that represents a product in the vending machine
  *
  * @param code: product id
  * @param name: product name
  * @param cost: product cost
  */
case class Product(code: String , name:String , cost: Int )

