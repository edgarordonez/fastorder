package app.fastorder.fastorder.api.controller.waiter

import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import app.fastorder.fastorder.waiter.infrastructure.marshaller.WaiterJsonFormatMarshaller._
import app.fastorder.fastorder.waiter.application.search.WaiterSearcher

final class WaiterGetController(searcher: WaiterSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.all())
}
