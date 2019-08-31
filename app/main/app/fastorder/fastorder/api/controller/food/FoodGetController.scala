package app.fastorder.fastorder.api.controller.food

import spray.json.DefaultJsonProtocol
import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import app.fastorder.fastorder.food.infrastructure.marshaller.FoodJsonFormatMarshaller._
import app.fastorder.fastorder.food.application.search.FoodSearcher

final class FoodGetController(searcher: FoodSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.all())
}
