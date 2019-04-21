package app.fastorder.fastorder.shared.infrastructure.marshaller.waiter

import java.util.UUID
import spray.json.{DefaultJsonProtocol, JsValue, JsonFormat, _}
import app.fastorder.shared.infrastructure.marshaller.UuidJsonFormatMarshaller._
import app.fastorder.fastorder.shared.domain.waiter.WaiterId

object WaiterIdJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object WaiterIdMarshaller extends JsonFormat[WaiterId] {
    override def write(value: WaiterId): JsValue = value.value.toJson
    override def read(value: JsValue): WaiterId  = WaiterId(value.convertTo[UUID])
  }
}
