package app.fastorder.fastorder.waiter.infrastructure.marshaller

import spray.json.{DeserializationException, JsString, JsValue, JsonFormat}
import app.fastorder.fastorder.waiter.domain.WaiterName

object WaiterNameJsonFormatMarshaller {
  implicit object UserNameMarshaller extends JsonFormat[WaiterName] {
    override def write(value: WaiterName): JsValue = JsString(value.value)

    override def read(value: JsValue): WaiterName = value match {
      case JsString(name) => WaiterName(name)
      case _              => throw DeserializationException("Expected 1 string for UserName")
    }
  }
}
