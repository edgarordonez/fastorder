package app.fastorder.fastorder.order.domain

import scala.concurrent.Future

trait OrderRepository {
  def all(): Future[Seq[Order]]
  def save(order: Order): Future[Unit]
}
