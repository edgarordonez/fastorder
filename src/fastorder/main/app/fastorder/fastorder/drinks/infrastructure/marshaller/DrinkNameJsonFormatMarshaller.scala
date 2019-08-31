package app.fastorder.fastorder.drinks.infrastructure.marshaller

import spray.json.{DeserializationException, JsString, JsValue, JsonFormat}
import app.fastorder.fastorder.drinks.domain.DrinkName

object DrinkNameJsonFormatMarshaller {
  implicit object DrinkNameMarshaller extends JsonFormat[DrinkName] {
    override def write(obj: DrinkName): JsValue = JsString(obj.value)

    override def read(json: JsValue): DrinkName = json match {
      case JsString(value) => DrinkName(value)
      case _               => throw DeserializationException("Expected 1 string for UserName")
    }
  }
}
