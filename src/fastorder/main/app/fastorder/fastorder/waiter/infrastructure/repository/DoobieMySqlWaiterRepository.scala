package app.fastorder.fastorder.waiter.infrastructure.repository

import scala.concurrent.{ExecutionContext, Future}
import doobie.implicits._
import app.fastorder.fastorder.shared.infrastructure.doobie.TypesConversions._
import app.fastorder.shared.infrastructure.doobie.DoobieDbConnection
import app.fastorder.fastorder.waiter.domain.{Waiter, WaiterRepository}

final class DoobieMySqlWaiterRepository(db: DoobieDbConnection)(implicit executionContext: ExecutionContext)
    extends WaiterRepository {
  override def all(): Future[Seq[Waiter]] = {
    db.read(sql"SELECT waiter_id, name FROM waiters".query[Waiter].to[Seq])
  }

  override def save(waiter: Waiter): Future[Unit] = {
    sql"INSERT INTO waiters(waiter_id, name) VALUES (${waiter.id}, ${waiter.name})".update.run
      .transact(db.transactor)
      .unsafeToFuture()
      .map(_ => ())
  }
}
