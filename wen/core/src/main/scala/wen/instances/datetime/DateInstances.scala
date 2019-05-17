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
      (x, y) match {
        case (Date(_, _, year1), Date(_, _, year2)) if year1 =!= year2 => year1 compare year2
        case (Date(_, month1, _), Date(_, month2, _)) if month1 =!= month2 => month1 compare month2
        case (Date(day1, _, _), Date(day2, _,_)) => day1 compare day2
      }
  }

  implicit val dateShowInstance: Show[Date] = new Show[Date] {
    override def show(t: Date): String =
      t match {
        case Date(d, m, Year(y, AD)) => s"${d.show} ${m.show} ${y}"
        case Date(d, m, Year(y, BC)) => s"${d.show} ${m.show} ${y} ${(BC: Epoch).show}"
      }

  }
}
