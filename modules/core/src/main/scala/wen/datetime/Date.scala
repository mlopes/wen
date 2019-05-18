package wen.datetime

import java.time.LocalDate

import wen.types._
import wen.refine._
import eu.timepit.refined._

final case class Date private (day: Day, month: Month, year: Year)

final object Date {
  def safe(day: Day, month: Month, year: Year): Option[Date] = {
    def isLeapYear: Year => Boolean = {
      case Year(year, _) =>
        (year.value % 4 == 0 && year.value % 100 != 0) || year.value % 400 == 0
    }

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
    // We run unsafe operations here, because unless there's a bug in java.time.LocalDate
    // we'll always have a valid day, month and year, and we don't want the user to have to
    // deal with an Option that is never a None, as it breaks semantics.
    val day = refineV[NumericDayConstraint].unsafeFrom(localDate.getDayOfMonth)
    val month = refineV[NumericMonthConstraint].unsafeFrom(localDate.getMonthValue)
    val year =
      if (localDate.getYear > 0)
        Year(refineV[NumericYearConstraint].unsafeFrom(localDate.getYear))
      else
        Year(refineV[NumericYearConstraint].unsafeFrom(Math.abs(localDate.getYear - 1)), BC)
    Date.unsafe(Day(day),Month(month), year)
  }

  def unsafe(day: Day, month: Month, year: Year): Date = new Date(day, month, year)
}
