package app.fastorder.fastorder.drinks.infrastructure.repository

import scala.concurrent.{ExecutionContext, Future}
import doobie.implicits._
import app.fastorder.fastorder.shared.infrastructure.doobie.TypesConversions._
import app.fastorder.shared.infrastructure.doobie.DoobieDbConnection
import app.fastorder.fastorder.drinks.domain.{Drink, DrinkRepository}

final class DoobieMySqlDrinkRepository(db: DoobieDbConnection)(implicit executionContext: ExecutionContext)
    extends DrinkRepository {

  override def all(): Future[Seq[Drink]] =
    db.read(sql"SELECT id, name, price FROM drinks".query[Drink].to[Seq])

  override def save(drinks: Drink): Future[Unit] =
    sql"INSERT INTO drinks(id, name, price) VALUES (${drinks.id}, ${drinks.name}, ${drinks.price})".update.run
      .transact(db.transactor)
      .unsafeToFuture()
      .map(_ => ())
}
