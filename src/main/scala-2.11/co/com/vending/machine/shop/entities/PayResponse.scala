package co.com.vending.machine.shop.entities


/**
  * This entity respresnts the vending mache answer when you pay
  * @param idRequest: Request's id
  * @param debt: your debt so far
  * @param message: says if you still have to pay more or if you can retrieve your product now
  * @param cashBack:  money back if you pay more than the product's value
  */
case class PayResponse(idRequest: String , debt: Int , message: String , cashBack:Int)
