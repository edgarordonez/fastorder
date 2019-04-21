package app.fastorder.shared.infrastructure.doobie

import cats.effect.IO

import scala.concurrent.ExecutionContext

object contextShift {
  implicit val cs = IO.contextShift(ExecutionContext.global)
}
