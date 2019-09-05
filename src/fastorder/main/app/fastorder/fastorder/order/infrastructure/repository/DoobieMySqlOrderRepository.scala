package app.fastorder.fastorder.order.infrastructure.repository

import app.fastorder.fastorder.order.domain.{Order, OrderRepository}
import app.fastorder.fastorder.order.infrastructure.marshaller.OrderDrinkJsonFormatMarshaller._
import app.fastorder.fastorder.shared.infrastructure.doobie.TypesConversions._
import app.fastorder.shared.infrastructure.doobie.DoobieDbConnection
import doobie.implicits._
import spray.json._

import scala.concurrent.{ExecutionContext, Future}

final class DoobieMySqlOrderRepository(db: DoobieDbConnection)(implicit executionContext: ExecutionContext)
    extends OrderRepository {

  override def all(): Future[Seq[Order]] =
    db.read(sql"SELECT id, waiter_id, dinner_table, drinks, food, amount FROM orders".query[Order].to[Seq])

  override def save(order: Order): Future[Unit] =
    sql"INSERT INTO orders(id, waiter_id, dinner_table, drinks, food, amount) VALUES (${order.id}, ${order.waiterId}, ${order.table}, ${order.drinks.toJson.toString}, ${order.food}, ${order.amount})".update.run
      .transact(db.transactor)
      .unsafeToFuture()
      .map(_ => ())
}
