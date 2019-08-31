package app.fastorder.fastorder.order.infrastructure.marshaller

import java.util.UUID
import spray.json._
import app.fastorder.shared.infrastructure.marshaller.UuidJsonFormatMarshaller._
import app.fastorder.fastorder.order.domain.OrderId

object OrderIdJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object OrderIdMarshaller extends JsonFormat[OrderId] {
    override def write(obj: OrderId): JsValue = obj.value.toJson

    override def read(json: JsValue): OrderId = OrderId(json.convertTo[UUID])
  }
}
