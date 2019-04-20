package com.mlopes.wen.instances

import cats.Order
import com.mlopes.wen.types.Month
import cats.implicits._

object MonthInstances {
  implicit val monthOrderInstance: Order[Month] = new Order[Month] {
    override def compare(x: Month, y: Month): Int =
      Order[Int].compare(Month.toInt(x), Month.toInt(y))
  }
}
