package app.fastorder.fastorder.waiters.infrastructure.marshaller

import spray.json.{DeserializationException, JsString, JsValue, JsonFormat}
import app.fastorder.fastorder.waiters.domain.WaiterName

object WaiterNameJsonFormatMarshaller {
  implicit object UserNameMarshaller extends JsonFormat[WaiterName] {
    override def write(obj: WaiterName): JsValue = JsString(obj.value)

    override def read(json: JsValue): WaiterName = json match {
      case JsString(value) => WaiterName(value)
      case _               => throw DeserializationException("Expected 1 string for UserName")
    }
  }
}
