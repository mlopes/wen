package wen.instances.datetime

import cats.implicits._
import cats.{Order, Show}
import wen.instances.YearInstances._
import wen.instances.MonthInstances._
import wen.instances.DayInstances._
import wen.instances.EpochInstances._
import wen.datetime.Date
import wen.types.{AD, BC, Epoch, Year}

object DateInstances extends DateInstances

trait DateInstances {

  implicit val dateOrderInstance: Order[Date] = new Order[Date] {
    override def compare(x: Date, y: Date): Int =
      if (x.year =!= y.year)
        x.year compare y.year
      else if (x.month =!= y.month)
        x.month compare y.month
      else
        x.day compare y.day

  }

  implicit val dateShowInstance: Show[Date] = new Show[Date] {
    override def show(t: Date): String =
      t match {
        case Date(d, m, Year(y, AD)) => s"${d.show} ${m.show} ${y}"
        case Date(d, m, Year(y, BC)) => s"${d.show} ${m.show} ${y} ${(BC: Epoch).show}"
      }

  }
}
