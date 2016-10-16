package co.com.vending.machine.shop.storage.dto

import co.com.vending.machine.shop.entities.Product

/**
  * DTO that represents a product in the vending machine
 *
  * @param code: product id
  * @param name: product name
  * @param cost: product cost
  */
case class ProductDTO(code: String , name:String , cost: Int )


object  ProductDTO {

  def apply(dto: ProductDTO):Product = {
    Product(dto.code,dto.name,dto.cost)
  }
}
