package app.fastorder.shared.infrastructure.doobie

import scala.concurrent.{ExecutionContext, Future}
import cats.effect.{ContextShift, IO}
import doobie.util.transactor.Transactor
import doobie.syntax.ConnectionIOOps
import doobie.util.transactor.Transactor.Aux

final class DoobieDbConnection(jdbcConfig: JdbcConfig) {
  implicit val cs: ContextShift[IO] = IO.contextShift(ExecutionContext.global)

  val transactor: Aux[IO, Unit] = Transactor.fromDriverManager[IO](
    jdbcConfig.driver,
    jdbcConfig.url,
    jdbcConfig.user,
    jdbcConfig.password
  )

  def read[T](query: ConnectionIOOps[T]): Future[T] = query.transact(transactor).unsafeToFuture()
}
