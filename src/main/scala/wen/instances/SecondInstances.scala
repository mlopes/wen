package wen.instances

import cats.implicits._
import cats._
import wen.types.Second

object SecondInstances extends SecondInstances

trait SecondInstances {
  implicit val secondOrderInstance: Order[Second] = new Order[Second] {
    override def compare(x: Second, y: Second): Int = Order[Int].compare(x.second.value, y.second.value)
  }

  implicit val secondShowInstance: Show[Second] = new Show[Second] {
    override def show(t: Second): String = f"${t.second.value}%02d"
  }
}
