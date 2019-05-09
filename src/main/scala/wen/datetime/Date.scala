package wen.datetime

import java.time.LocalDate

import wen.types._
import wen.refine.{refineDay, refineMonth, refineYear}

final case class Date private (day: Day, month: Month, year: Year)

final object Date {
  def safe(day: Day, month: Month, year: Year): Option[Date] = {
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

  def apply(localDate: LocalDate): Date = {
    val eitherDate: Either[String, Date] = for {
      day <- refineDay(localDate.getDayOfMonth)
      month <- refineMonth(localDate.getMonthValue)
      year <-
        if (localDate.getYear > 0)
          refineYear(localDate.getYear).map[Year](Year(_))
        else
          refineYear(Math.abs(localDate.getYear - 1)).map[Year](Year(_, BC))
    } yield Date.unsafe(Day(day),Month(month), year)

    // We run an unsafe operation here, because unless there's a bug in java.time.LocalDate
    // we'll always have a Right, and we don't want the user to have to deal with an Option
    // that is never a None.
    eitherDate.toOption.get
  }

  def unsafe(day: Day, month: Month, year: Year): Date = new Date(day, month, year)
}
