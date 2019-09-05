package app.fastorder.fastorder.order.infrastructure.marshaller

import app.fastorder.fastorder.order.domain.OrderDrink
import spray.json._

object OrderDrinkJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object OrderDrinkFormat extends JsonFormat[OrderDrink] {
    override def write(obj: OrderDrink): JsValue = JsObject(
      "id"       -> JsString(obj.id),
      "name"     -> JsString(obj.name),
      "price"    -> JsNumber(obj.price),
      "quantity" -> JsNumber(obj.quantity)
    )

    override def read(json: JsValue): OrderDrink =
      json.asJsObject.getFields("id", "name", "price", "quantity") match {
        case Seq(
            JsString(id),
            JsString(name),
            JsNumber(price),
            JsNumber(quantity)
            ) =>
          OrderDrink(id, name, price.toDouble, quantity.toInt)
        case unknown =>
          throw DeserializationException(
            s"Error reading VideoCreated JSON <$unknown>"
          )
      }
  }
}
