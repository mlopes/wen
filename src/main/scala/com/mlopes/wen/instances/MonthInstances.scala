package com.mlopes.wen.instances

import cats._
import com.mlopes.wen.types._
import cats.implicits._

object MonthInstances {
  implicit val monthOrderInstance: Order[Month] = new Order[Month] {
    override def compare(x: Month, y: Month): Int =
      Order[Int].compare(Month.toInt(x), Month.toInt(y))
  }

  implicit val monthShowInstance: Show[Month] = new Show[Month] {
    override def show(t: Month): String = t match {
      case January => "January"
      case February => "February"
      case March => "March"
      case April => "April"
      case May => "May"
      case June => "June"
      case July => "July"
      case August => "August"
      case September => "September"
      case October => "October"
      case November => "November"
      case December => "December"
    }
  }
}
