package app.fastorder.fastorder.food.infrastructure.marshaller

import spray.json.{DeserializationException, JsString, JsValue, JsonFormat}
import app.fastorder.fastorder.food.domain.FoodName

object FoodNameJsonFormatMarshaller {
  implicit object FoodNameMarshaller extends JsonFormat[FoodName] {
    override def write(obj: FoodName): JsValue = JsString(obj.value)

    override def read(json: JsValue): FoodName = json match {
      case JsString(value) => FoodName(value)
      case _               => throw DeserializationException("Expected 1 string for UserName")
    }
  }
}
