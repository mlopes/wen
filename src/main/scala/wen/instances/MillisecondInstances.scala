package wen.instances

import cats.implicits._
import cats._
import wen.types.Millisecond

object MillisecondInstances {
  implicit val millisecondOrderInstance: Order[Millisecond] = new Order[Millisecond] {
    override def compare(x: Millisecond, y: Millisecond): Int = Order[Int].compare(x.millisecond.value, y.millisecond.value)
  }

  implicit val millisecondShowInstance: Show[Millisecond] = new Show[Millisecond] {
    override def show(t: Millisecond): String = s"${t.millisecond.value}"
  }
}
