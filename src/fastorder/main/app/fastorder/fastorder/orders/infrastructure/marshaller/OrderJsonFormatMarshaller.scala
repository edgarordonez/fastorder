package app.fastorder.fastorder.orders.infrastructure.marshaller

import app.fastorder.fastorder.orders.domain.{Order, OrderDrink, OrderFood}
import app.fastorder.fastorder.orders.infrastructure.marshaller.OrderDrinkJsonFormatMarshaller._
import app.fastorder.fastorder.orders.infrastructure.marshaller.OrderFoodJsonFormatMarshaller._
import spray.json._

object OrderJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object OrderFormat extends JsonFormat[Order] {
    override def write(obj: Order): JsValue = JsObject(
      "id"           -> JsString(obj.id.value.toString),
      "waiter_id"    -> JsString(obj.waiterId.value.toString),
      "dinner_table" -> JsNumber(obj.table),
      "drinks"       -> obj.drinks.toJson,
      "food"         -> obj.food.toJson,
      "amount"       -> JsNumber(obj.amount)
    )

    override def read(json: JsValue): Order =
      json.asJsObject.getFields("id", "waiter_id", "dinner_table", "drinks", "food", "amount") match {
        case Seq(
            JsString(id),
            JsString(waiterId),
            JsNumber(table),
            JsObject(drinks),
            JsObject(food),
            JsNumber(amount)
            ) =>
          Order(
            id,
            waiterId,
            table.toInt,
            drinks.toJson.convertTo[Seq[OrderDrink]],
            food.toJson.convertTo[Seq[OrderFood]],
            amount.toDouble
          )
        case unknown =>
          throw DeserializationException(
            s"Error reading Order JSON <$unknown>"
          )
      }
  }
}
