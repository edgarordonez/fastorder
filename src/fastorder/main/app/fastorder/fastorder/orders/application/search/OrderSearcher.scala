package app.fastorder.fastorder.orders.application.search

import scala.concurrent.Future
import app.fastorder.fastorder.orders.domain.{Order, OrderRepository}

final class OrderSearcher(repository: OrderRepository) {
  def all(): Future[Seq[Order]] = repository.all()
}
