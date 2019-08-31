package app.fastorder.fastorder.food.infrastructure.marshaller

import java.util.UUID
import spray.json._
import app.fastorder.fastorder.food.domain.FoodId
import app.fastorder.shared.infrastructure.marshaller.UuidJsonFormatMarshaller._

object FoodIdJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object FoodIdMarshaller extends JsonFormat[FoodId] {
    override def write(obj: FoodId): JsValue = obj.value.toJson

    override def read(json: JsValue): FoodId = FoodId(json.convertTo[UUID])
  }
}
