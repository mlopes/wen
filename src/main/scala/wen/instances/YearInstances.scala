package wen.instances

import cats._
import cats.implicits._
import wen.types.{AD, BC, Year}
import wen.instances.EpochInstances._

object YearInstances extends YearInstances

trait YearInstances {

  implicit val yearOrderInstance: Order[Year] = new Order[Year] {
    override def compare(x: Year, y: Year): Int =
      if(x.epoch == y.epoch) {
        Order[Int].compare(x.year.value, y.year.value)
      } else {
        x.epoch compare y.epoch
      }
  }

  implicit val yearShowInstances: Show[Year] = new Show[Year] {
    override def show(t: Year): String = t match {
      case Year(y, BC) => s"${y.value} BC"
      case Year(y, AD) => s"${y.value}"
    }
  }
}
