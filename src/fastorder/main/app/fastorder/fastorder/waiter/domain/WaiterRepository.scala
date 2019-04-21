package app.fastorder.fastorder.waiter.domain

import scala.concurrent.Future

trait WaiterRepository {
  def all(): Future[Seq[Waiter]]
  def save(waiter: Waiter): Future[Unit]
}
