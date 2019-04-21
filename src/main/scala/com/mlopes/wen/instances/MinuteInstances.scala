package com.mlopes.wen.instances

import cats.implicits._
import cats._
import com.mlopes.wen.types.Minute

object MinuteInstances {
  implicit val minuteOrderInstance: Order[Minute] = new Order[Minute] {
    override def compare(x: Minute, y: Minute): Int = Order[Int].compare(x.minute.value, y.minute.value)
  }

  implicit val minuteShowInstance: Show[Minute] = new Show[Minute] {
    override def show(t: Minute): String = f"${t.minute.value}%02d"
  }
}
