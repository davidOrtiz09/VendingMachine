package co.com.vending.machine.shop.entities

/**
  * Business entity that represents a product in the vending machine
  * @param code: product id
  * @param name: product name
  * @param cost: product cost
  */
case class Product(code: String , name:String , cost: Int )
