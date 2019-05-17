package wen.instances

import cats.implicits._
import cats._
import wen.types.Day

object DayInstances extends DayInstances

trait DayInstances {
  implicit val dayOrderInstance: Order[Day] = new Order[Day] {
    override def compare(x: Day, y: Day): Int = Order[Int].compare(x.day.value, y.day.value)
  }

  implicit val dayShowInstance: Show[Day] = new Show[Day] {
    override def show(t: Day): String = s"${t.day.value}"
  }
}
