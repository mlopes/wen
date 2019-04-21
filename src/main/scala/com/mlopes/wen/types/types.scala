package com.mlopes.wen.types

import com.mlopes.wen.types.NumericTypes._
import com.mlopes.wen.types.Offset.OffsetType
import eu.timepit.refined._
import eu.timepit.refined.auto._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.{Interval, Positive}

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
  def apply(month: Int): Option[Month] =
      if (month == 1) Some(January)
      else if (month == 2) Some(February)
      else if (month == 3) Some(March)
      else if (month == 4) Some(April)
      else if (month == 5) Some(May)
      else if (month == 6) Some(June)
      else if (month == 7) Some(July)
      else if (month == 8) Some(August)
      else if (month == 9) Some(September)
      else if (month == 10) Some(October)
      else if (month == 11) Some(November)
      else if (month == 12) Some(December)
      else None

  def toInt: Month => Int = {
    case January => 1
    case February => 2
    case March => 3
    case April => 4
    case May => 5
    case June => 6
    case July => 7
    case August => 8
    case September => 9
    case October => 10
    case November => 11
    case December => 12
  }

  def fromNumericMonth(numericMonth: NumericMonth): Month =
    Month(numericMonth.value).get
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
final case object AD extends Epoch
final case object BC extends Epoch

final class Year private(val year: NumericYear, val epoch: Epoch)

object Year {
  def apply(year: Int, epoch: Epoch): Option[Year] =
    /* We're running unsafe here because we're using the if as a
       safe-guard to guarantee that we can always refine the value
       The reason for this, is to make the refinement transparent for
       users of the library.
    */
    if (year > 0)
      Some(new Year(refineV[Positive].unsafeFrom(year), epoch))
    else
      None

  def unapply(y: Year): Option[(NumericYear, Epoch)] = Some((y.year, y.epoch))

  def fromNumericYear(numericYear: NumericYear, epoch: Epoch): Year =
    Year(numericYear.value, epoch).get
}

final class Hour private(val hour: NumericHour)

object Hour {
  def apply(hour: Int): Option[Hour] =
    // See comment on Year for the reasoning behind running unsafeFrom
    if (hour >= 0 && hour <= 23)
      Some(new Hour(refineV[Interval.Closed[W.`0`.T, W.`23`.T]].unsafeFrom(hour)))
    else
      None

  def unapply(h: Hour): Option[NumericHour] = Some(h.hour)

  def fromNumericHour(numericHour: NumericHour): Hour =
    Hour(numericHour.value).get
}

final class Minute private(val minute: NumericMinute)

object Minute {
  def apply(minute: Int): Option[Minute] =
    // See comment on Year for the reasoning behind running unsafeFrom
    if (minute >= 0 && minute <= 59)
      Some(new Minute(refineV[Interval.Closed[W.`0`.T, W.`59`.T]].unsafeFrom(minute)))
    else
      None

  def unapply(m: Minute): Option[NumericMinute] = Some(m.minute)

  def fromNumericMinute(numericMinute: NumericMinute): Minute =
    Minute(numericMinute.value).get
}

final class Second private(val second: NumericSecond)

object Second {
  def apply(second: Int): Option[Second] =
    // See comment on Year for the reasoning behind running unsafeFrom
    if (second >= 0 && second <= 59)
      Some(new Second(refineV[Interval.Closed[W.`0`.T, W.`59`.T]].unsafeFrom(second)))
    else
      None

  def unapply(s: Second): Option[NumericSecond] = Some(s.second)

  def fromNumericSecond(numericSecond: NumericSecond): Second =
    Second(numericSecond.value).get
}

final class Millisecond private(val millisecond: NumericMillisecond)

object Millisecond {
  def apply(millisecond: Int): Option[Millisecond] =
    // See comment on Year for the reasoning behind running unsafeFrom
    if(millisecond >= 0 && millisecond <= 999)
      Some(new Millisecond(refineV[Interval.Closed[W.`0`.T, W.`999`.T]].unsafeFrom(millisecond)))
    else
      None

  def unapply(m: Millisecond): Option[NumericMillisecond] = Some(m.millisecond)

  def fromNumericMillisecond(numericMillisecond: NumericMillisecond): Millisecond =
    Millisecond(numericMillisecond.value).get
}

object NumericTypes {
  type NumericYear = Int Refined Positive
  type NumericMonth = Int Refined Interval.Closed[W.`1`.T, W.`12`.T]
  type NumericHour = Int Refined Interval.Closed[W.`0`.T, W.`23`.T]
  type NumericMinute = Int Refined Interval.Closed[W.`0`.T, W.`59`.T]
  type NumericSecond = Int Refined Interval.Closed[W.`0`.T, W.`59`.T]
  type NumericMillisecond = Int Refined Interval.Closed[W.`0`.T, W.`999`.T]
}

case class Offset(offsetType: OffsetType, hour: Hour, minute: Minute)
object Offset {
  sealed trait OffsetType
  final case object UTCMinus extends OffsetType
  final case object UTCPlus extends OffsetType

  val UTC: Offset = Offset(UTCPlus, Hour.fromNumericHour(0), Minute.fromNumericMinute(0))
}
