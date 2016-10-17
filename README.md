## VendingMachine
Vending machine emulator using Akka actors



## Libraries

* Scala 2.11.8
* SBT 0.13.5

|   library        |   Version         |
|   -------------    |   -------------   |
|   (akka-http-experimental)                                                    |   2.4.11 |
|   (akka-actor)                                                                |   2.4.11 |
|   (akka-http-spray-json-experimental)                                         |   2.4.11 |
|   (com.typesafe.config)                                                       |   1.3.1  |
|   (akka-testkit)                                                              |   2.4.11 |
|   (akka-http-testkit)                                                         |   2.4.11 |
|   (org.scalatest)                                                             |   3.0.0  |


## Restrictions

  - Only one currency is accepted
  - Only this values are accepted (10 , 5) to insert in the vending machine

## Products Available

code , name , cost

 - 145692,coke,15
 - 845327,pepsi,15
 - 761234,ham & cheese sandwich,30
 - 789991,peanut,5
 - 100012,chocolate bar,10

## API Routes

|   Route            |   Http         |
|   -------------     |   -------------  |
|   (/product)                                                                  |    GET   |
|   (/product/:productCode)                                                     |    GET   |
|   (/product/pay)                                                              |    POST  |

# /product Example Response
```json
 {
   [
     {
       "code": "145692",
       "name": "coke",
       "cost": 15
     },
     {
       "code": "845327",
       "name": "pepsi",
       "cost": 15
     }
   ]
 }
 ```

# /product/:productCode Example Response
 ```json
  {
    "idRequest": "b63ed342-03ec-4db9-93b6-3b731dbaee57",
    "productCost": 15
  }
  ```

# /product/pay Example Request
   ```json
    {
      	"requestId" : "b63ed342-03ec-4db9-93b6-3b731dbaee57",
      	"pay": 5
    }
    ```

# /product/pay Example Response
     ```json
      {
              "idRequest": "9d6d4a5a-06eb-4a43-92e2-1a373bc071dd",
              "debt": 10,
              "message": "Insert more coins",
              "cashBack": 0
      }
      ```

## How it works ?

 To start the app just digit sbt run in the project directory, the app will run in 0.0.0.0:8080

    - First, look for all the products in the vending machine (/product).

    - Second, select the product you want (/product/:productCode).
      This will give you a requestId that you have to use to pay the product.

    - Finally, pay the product value following the currency restrictions and using the requestId (/product/pay)





