package com.mlopes.wen.instances.datetime

import cats.{Order, Show}
import com.mlopes.wen.instances.YearInstances._
import com.mlopes.wen.instances.MonthInstances._
import com.mlopes.wen.instances.DayInstances._
import com.mlopes.wen.instances.EpochInstances._
import com.mlopes.wen.datetime.Date
import com.mlopes.wen.types.{Day, Epoch, Month, Year}

object DateInstances {

  implicit val dateOrderInstance: Order[Date] = new Order[Date] {
    override def compare(x: Date, y: Date): Int =
      if (x.year != y.year)
        Order[Year].compare(x.year, y.year)
      else if (x.month != x.month)
        Order[Month].compare(x.month, y.month)
      else
        Order[Day].compare(x.day, y.day)

  }

  implicit val dateShowInstance: Show[Date] = new Show[Date] {
    override def show(t: Date): String =
      s"${Show[Day].show(t.day)} ${Show[Month].show(t.month)} ${t.year.year} ${Show[Epoch].show(t.year.epoch)}"
  }
}
