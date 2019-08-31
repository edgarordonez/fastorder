package app.fastorder.fastorder.shared.infrastructure.marshaller.waiter

import java.util.UUID
import spray.json._
import app.fastorder.shared.infrastructure.marshaller.UuidJsonFormatMarshaller._
import app.fastorder.fastorder.shared.domain.waiter.WaiterId

object WaiterIdJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object WaiterIdMarshaller extends JsonFormat[WaiterId] {
    override def write(obj: WaiterId): JsValue = obj.value.toJson

    override def read(json: JsValue): WaiterId = WaiterId(json.convertTo[UUID])
  }
}
