package app.fastorder.fastorder.drinks.infrastructure.marshaller

import java.util.UUID
import spray.json._
import app.fastorder.shared.infrastructure.marshaller.UuidJsonFormatMarshaller._
import app.fastorder.fastorder.drinks.domain.DrinkId

object DrinkIdJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object DrinkIdMarshaller extends JsonFormat[DrinkId] {
    override def write(obj: DrinkId): JsValue = obj.value.toJson

    override def read(json: JsValue): DrinkId = DrinkId(json.convertTo[UUID])
  }
}
