package com.mlopes.wen.instances

import com.mlopes.wen.instances.EpochInstances._
import cats._
import cats.implicits._
import com.mlopes.wen.types.{AD, BC, Epoch, Year}

object YearInstances {

  implicit val yearOrderInstance: Order[Year] = new Order[Year] {
    override def compare(x: Year, y: Year): Int =
      if(x.epoch == y.epoch) {
        Order[Int].compare(x.year.value, y.year.value)
      } else {
        Order[Epoch].compare(x.epoch, y.epoch)
      }
  }

  implicit val yearShowInstances: Show[Year] = new Show[Year] {
    override def show(t: Year): String = t match {
      case Year(y, BC) => s"${y.value} BC"
      case Year(y, AD) => s"AD ${y.value}"
    }
  }
}
