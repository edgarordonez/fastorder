package app.fastorder.fastorder.drinks.infrastructure.marshaller

import spray.json._
import app.fastorder.fastorder.shared.infrastructure.marshaller.price.PriceJsonFormatMarshaller._
import app.fastorder.fastorder.drinks.infrastructure.marshaller.DrinkIdJsonFormatMarshaller._
import app.fastorder.fastorder.drinks.infrastructure.marshaller.DrinkNameJsonFormatMarshaller._
import app.fastorder.fastorder.shared.domain.price.Price
import app.fastorder.fastorder.drinks.domain.{Drink, DrinkId, DrinkName}

object DrinkJsonFormatMarshaller extends DefaultJsonProtocol {
  implicit val DrinkFormat: RootJsonFormat[Drink] = jsonFormat3(Drink.apply(_: DrinkId, _: DrinkName, _: Price))
}
