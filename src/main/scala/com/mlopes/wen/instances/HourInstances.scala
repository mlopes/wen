package com.mlopes.wen.instances

import cats.implicits._
import cats._
import com.mlopes.wen.types.Hour

object HourInstances {
  implicit val hourOrderInstance: Order[Hour] = new Order[Hour] {
    override def compare(x: Hour, y: Hour): Int = Order[Int].compare(x.hour.value, y.hour.value)
  }

  implicit val hourShowInstance: Show[Hour] = new Show[Hour] {
    override def show(t: Hour): String = f"${t.hour.value}%02d"
  }
}
