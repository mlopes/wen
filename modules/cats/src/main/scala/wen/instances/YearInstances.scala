package wen.instances

import cats._
import cats.implicits._
import wen.types.{AD, BC, Year}
import wen.instances.EpochInstances._

object YearInstances extends YearInstances

trait YearInstances {

  implicit val yearOrderInstance: Order[Year] = new Order[Year] {
    override def compare(x: Year, y: Year): Int =
      (x, y) match {
        case (Year(y1, e1), Year(y2, e2)) if e1 === e2 => Order[Int].compare(y1.value, y2.value)
        case (Year(_, e1), Year(_, e2)) => e1 compare e2
      }
  }

  implicit val yearShowInstances: Show[Year] = new Show[Year] {
    override def show(t: Year): String = t match {
      case Year(y, BC) => s"${y.value} BC"
      case Year(y, AD) => s"${y.value}"
    }
  }
}
