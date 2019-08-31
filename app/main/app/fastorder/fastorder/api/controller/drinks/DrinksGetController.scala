package app.fastorder.fastorder.api.controller.drinks

import spray.json.DefaultJsonProtocol
import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import app.fastorder.fastorder.drinks.infrastructure.marshaller.DrinkJsonFormatMarshaller._
import app.fastorder.fastorder.drinks.application.search.DrinkSearcher

final class DrinksGetController(searcher: DrinkSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.all())
}
