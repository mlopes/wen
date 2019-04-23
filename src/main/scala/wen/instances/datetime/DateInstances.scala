package wen.instances.datetime

import cats.implicits._
import cats.{Order, Show}
import wen.instances.YearInstances._
import wen.instances.MonthInstances._
import wen.instances.DayInstances._
import wen.instances.EpochInstances._
import wen.datetime.Date

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
      s"${t.day.show} ${t.month.show} ${t.year.year} ${t.year.epoch.show}"
  }
}
