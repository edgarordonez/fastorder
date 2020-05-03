package app.fastorder.fastorder.api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import app.fastorder.fastorder.orders.domain.{OrderDrink, OrderFood}
import spray.json.DefaultJsonProtocol._
import spray.json._

final class Routes(container: EntryPointDependencyContainer) {
  private val status = get {
    path("status")(container.statusGetController.get())
  }

  private val waiters = get {
      path("waiters")(container.waiterGetController.get())
    } ~
    post {
      path("waiters") {
        jsonBody { body =>
          container.waiterPostController.post(
            body("id").convertTo[String],
            body("name").convertTo[String]
          )
        }
      }
    }

  private val drinks = get {
      path("drinks")(container.drinkGetController.get())
    } ~
    post {
      path("drinks") {
        jsonBody { body =>
          container.drinkPostController.post(
            body("id").convertTo[String],
            body("name").convertTo[String],
            body("price").convertTo[Double]
          )
        }
      }
    }

  private val food = get {
      path("food")(container.foodGetController.get())
    } ~
    post {
      path("food") {
        jsonBody { body =>
          container.foodPostController.post(
            body("id").convertTo[String],
            body("name").convertTo[String],
            body("price").convertTo[Double]
          )
        }
      }
    }

  private val orders = {
    val drinksToSeqOrderDrink = (obj: JsValue) => {
      import app.fastorder.fastorder.orders.infrastructure.marshaller.OrderDrinkJsonFormatMarshaller._

      obj.convertTo[Seq[OrderDrink]]
    }

    val foodToSeqOrderFood = (obj: JsValue) => {
      import app.fastorder.fastorder.orders.infrastructure.marshaller.OrderFoodJsonFormatMarshaller._

      obj.convertTo[Seq[OrderFood]]
    }

    get {
      path("orders")(container.orderGetController.get())
    } ~ post {
      path("orders") {
        jsonBody { body =>
          container.orderPostController.post(
            body("id").convertTo[String],
            body("waiterId").convertTo[String],
            body("table").convertTo[Int],
            drinksToSeqOrderDrink(body("drinks")),
            foodToSeqOrderFood(body("food"))
          )
        }
      }
    }
  }

  private def jsonBody(handler: Map[String, JsValue] => Route): Route =
    entity(as[JsValue])(json => handler(json.asJsObject.fields))

  val all: Route = status ~ waiters ~ drinks ~ food ~ orders
}
