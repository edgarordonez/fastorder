package app.fastorder.fastorder.shared.domain.price

object Price {
  def apply(value: String): Price = new Price(value.toDouble)
}

case class Price(value: Double)
