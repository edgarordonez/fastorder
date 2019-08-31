package app.fastorder.shared.infrastructure.marshaller

import java.util.UUID
import spray.json.{DeserializationException, JsString, JsValue, JsonFormat}

object UuidJsonFormatMarshaller {
  implicit object UuidMarshaller extends JsonFormat[UUID] {
    override def write(obj: UUID): JsValue = JsString(obj.toString)

    override def read(json: JsValue): UUID = json match {
      case JsString(value) => UUID.fromString(value)
      case unknown         => throw DeserializationException(s"Expected hexadecimal UUID string, got <$unknown>")
    }
  }
}
