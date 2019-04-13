package app.fastorder

import app.fastorder.fastorder.api.WebServer

object Initializer {
  private val FastOrderArgument = "fast-order"

  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      throw new RuntimeException("You need to specify which app to start.")
    }

    args(0) match {
      case FastOrderArgument => WebServer.start()
      case _                 => println("Application doesn't exists.")
    }
  }
}
