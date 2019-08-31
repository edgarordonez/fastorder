package app.fastorder.fastorder.food.infrastructure.marshaller

import spray.json.{DefaultJsonProtocol, RootJsonFormat}
import app.fastorder.fastorder.shared.infrastructure.marshaller.price.PriceJsonFormatMarshaller._
import app.fastorder.fastorder.food.infrastructure.marshaller.FoodIdJsonFormatMarshaller._
import app.fastorder.fastorder.food.infrastructure.marshaller.FoodNameJsonFormatMarshaller._
import app.fastorder.fastorder.shared.domain.price.Price
import app.fastorder.fastorder.food.domain.{Food, FoodId, FoodName}

object FoodJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit val FoodFormat: RootJsonFormat[Food] = jsonFormat3(Food.apply(_: FoodId, _: FoodName, _: Price))
}
