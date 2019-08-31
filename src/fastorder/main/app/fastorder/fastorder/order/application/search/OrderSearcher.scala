package app.fastorder.fastorder.order.application.search

import scala.concurrent.Future
import app.fastorder.fastorder.order.domain.{Order, OrderRepository}

final class OrderSearcher(repository: OrderRepository) {
  def all(): Future[Seq[Order]] = repository.all()
}
