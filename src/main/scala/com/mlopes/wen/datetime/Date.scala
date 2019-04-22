package com.mlopes.wen.datetime

import com.mlopes.wen.types._

final case class Date private (day: Day, month: Month, year: Year)

object Date {
  def apply(day: Day, month: Month, year: Year): Option[Date] = {
    def isLeapYear(y: Year): Boolean =
      (y.year.value % 4 == 0 && y.year.value % 100 != 0) || y.year.value % 400 == 0

    month match {
      case February
        if ((isLeapYear(year) && day.day.value > 29) || ((!isLeapYear(year)) && day.day.value > 28))
          => None
      case April if (day.day.value > 30) => None
      case June if (day.day.value > 30) => None
      case September if (day.day.value > 30) => None
      case November if (day.day.value > 30) => None
      case _ => Some(new Date(day, month, year))
    }
  }

  def unsafe(day: Day, month: Month, year: Year): Date = new Date(day, month, year)
}
