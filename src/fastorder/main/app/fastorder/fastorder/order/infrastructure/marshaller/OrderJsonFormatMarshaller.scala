package app.fastorder.fastorder.order.infrastructure.marshaller

import app.fastorder.fastorder.order.domain.{Order, OrderDrink}
import app.fastorder.fastorder.order.infrastructure.marshaller.OrderDrinkJsonFormatMarshaller._
import spray.json._

object OrderJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object OrderFormat extends JsonFormat[Order] {
    override def write(obj: Order): JsValue = JsObject(
      "id"           -> JsString(obj.id.value.toString),
      "waiter_id"    -> JsString(obj.waiterId.value.toString),
      "dinner_table" -> JsNumber(obj.table),
      "drinks"       -> obj.drinks.toJson,
      "food"         -> JsonParser(obj.food),
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
          Order(id, waiterId, table.toInt, drinks.toJson.convertTo[Seq[OrderDrink]], food.toString, amount.toDouble)
        case unknown =>
          throw DeserializationException(
            s"Error reading VideoCreated JSON <$unknown>"
          )
      }
  }
}
