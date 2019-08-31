package app.fastorder.fastorder.api.controller.order

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import app.fastorder.fastorder.order.application.search.OrderSearcher
import app.fastorder.fastorder.order.infrastructure.marshaller.OrderJsonFormatMarshaller._
import spray.json.DefaultJsonProtocol

final class OrderGetController(searcher: OrderSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.all())
}
