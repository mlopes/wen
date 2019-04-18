package com.mlopes.wen.types

import com.mlopes.wen.types.Year.Year
import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.{Interval, Positive}
import eu.timepit.refined._

sealed trait Month
final case object January extends Month
final case object February extends Month
final case object March extends Month
final case object April extends Month
final case object May extends Month
final case object June extends Month
final case object July extends Month
final case object August extends Month
final case object September extends Month
final case object October extends Month
final case object November extends Month
final case object December extends Month

object Month {
  def apply(month: Int Refined Interval.Closed[W.`1`.T, W.`12`.T]): Month =
    month match {
      case m if m.value == 1 => January
      case m if m.value == 2 => February
      case m if m.value == 3 => March
      case m if m.value == 4 => April
      case m if m.value == 5 => May
      case m if m.value == 6 => June
      case m if m.value == 7 => July
      case m if m.value == 8 => August
      case m if m.value == 9 => September
      case m if m.value == 10 => October
      case m if m.value == 11 => November
      case m if m.value == 12 => December
    }
}

sealed trait WeekDay
final case object Sunday extends WeekDay
final case object Monday extends WeekDay
final case object Tuesday extends WeekDay
final case object Wednesday extends WeekDay
final case object Thursday extends WeekDay
final case object Friday extends WeekDay
final case object Saturday extends WeekDay

sealed trait Epoch
final case object AC extends Epoch
final case object BC extends Epoch

final case class EpochYear(year: Year, epoch: Epoch)

object Year {
  type Year = Int Refined Positive
}
