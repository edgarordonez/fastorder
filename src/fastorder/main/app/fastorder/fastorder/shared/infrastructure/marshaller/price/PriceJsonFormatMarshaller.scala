package app.fastorder.fastorder.shared.infrastructure.marshaller.price

import spray.json._
import app.fastorder.fastorder.shared.domain.price.Price

object PriceJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit object PriceMarshaller extends JsonFormat[Price] {
    override def write(obj: Price): JsValue = obj.value.toJson

    override def read(json: JsValue): Price = Price(json.convertTo[Double])
  }
}
