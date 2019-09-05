package app.fastorder.fastorder.order.infrastructure.marshaller

import app.fastorder.fastorder.order.domain.OrderFood
import spray.json._

object OrderFoodJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object OrderFoodFormat extends JsonFormat[OrderFood] {
    override def write(obj: OrderFood): JsValue = JsObject(
      "id"       -> JsString(obj.id),
      "name"     -> JsString(obj.name),
      "price"    -> JsNumber(obj.price),
      "quantity" -> JsNumber(obj.quantity)
    )

    override def read(json: JsValue): OrderFood =
      json.asJsObject.getFields("id", "name", "price", "quantity") match {
        case Seq(
            JsString(id),
            JsString(name),
            JsNumber(price),
            JsNumber(quantity)
            ) =>
          OrderFood(id, name, price.toDouble, quantity.toInt)
        case unknown =>
          throw DeserializationException(
            s"Error reading VideoCreated JSON <$unknown>"
          )
      }
  }
}
