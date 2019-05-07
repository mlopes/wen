package wen.types

import wen.types.NumericTypes._
import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.{Interval, Positive}
import eu.timepit.refined.types.{time => refinedTimeTypes}

sealed trait Month {
  def asInt: Int =
    this match {
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
}
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

final object Month {
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

  def apply(numericMonth: NumericMonth): Month =
    Month(numericMonth.value).get

  def fromString: String => Option[Month] = {
    case "January" => Some(January)
    case "February" => Some(February)
    case "March" => Some(March)
    case "April" => Some(April)
    case "May" => Some(May)
    case "June" => Some(June)
    case "July" => Some(July)
    case "August" => Some(August)
    case "September" => Some(September)
    case "October" => Some(October)
    case "November" => Some(November)
    case "December" => Some(December)
    case _ => None
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

final object WeekDay {
  def fromString: String => Option[WeekDay] = {
    case "Sunday" => Some(Sunday)
    case "Monday" => Some(Monday)
    case "Tuesday" => Some(Tuesday)
    case "Wednesday" => Some(Wednesday)
    case "Thursday" => Some(Thursday)
    case "Friday" => Some(Friday)
    case "Saturday" => Some(Saturday)
    case _ => None
  }
}

sealed trait Epoch
final case object AD extends Epoch
final case object BC extends Epoch

final object Epoch {
  def fromString: String => Option[Epoch] = {
    case "AD" => Some(AD)
    case "BC" => Some(BC)
    case _ => None
  }
}

final case class Year(year: NumericYear, epoch: Epoch)

final object Year {
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

  def apply(year: NumericYear): Year = new Year(year, AD)

  def apply(year: Int): Option[Year] = Year(year, AD)
}

final case class Day(day: NumericDay)

final object Day {
  private[wen] val min: Int = 1
  private[wen] val max: Int = 31

  def apply(day: Int): Option[Day] =
  // See comment on Year for the reasoning behind running unsafeFrom
    if (day >= min && day <= max)
      Some(new Day(refineV[Interval.Closed[W.`1`.T, W.`31`.T]].unsafeFrom(day)))
    else
      None
}

final case class Hour(hour: NumericHour)

final object Hour {
  private[wen] val min: Int = 0
  private[wen] val max: Int = 23

  def apply(hour: Int): Option[Hour] =
    // See comment on Year for the reasoning behind running unsafeFrom
    if (hour >= min && hour <= max)
      Some(new Hour(refineV[Interval.Closed[W.`0`.T, W.`23`.T]].unsafeFrom(hour)))
    else
      None
}

final case class Minute(minute: NumericMinute)

final object Minute {
  private[wen] val min: Int = 0
  private[wen] val max: Int = 59

  def apply(minute: Int): Option[Minute] =
    // See comment on Year for the reasoning behind running unsafeFrom
    if (minute >= min && minute <= max)
      Some(new Minute(refineV[Interval.Closed[W.`0`.T, W.`59`.T]].unsafeFrom(minute)))
    else
      None
}

final case class Second(second: NumericSecond)

final object Second {
  private[wen] val min: Int = 0
  private[wen] val max: Int = 59

  def apply(second: Int): Option[Second] =
    // See comment on Year for the reasoning behind running unsafeFrom
    if (second >= min && second <= max)
      Some(new Second(refineV[Interval.Closed[W.`0`.T, W.`59`.T]].unsafeFrom(second)))
    else
      None
}

final case class Millisecond(millisecond: NumericMillisecond)

final object Millisecond {
  private[wen] val min: Int = 0
  private[wen] val max: Int = 999

  def apply(millisecond: Int): Option[Millisecond] =
    // See comment on Year for the reasoning behind running unsafeFrom
    if(millisecond >= min && millisecond <= max)
      Some(new Millisecond(refineV[Interval.Closed[W.`0`.T, W.`999`.T]].unsafeFrom(millisecond)))
    else
      None
}

final object NumericTypes {
  type NumericYear = Int Refined Positive
  type NumericMonth = refinedTimeTypes.Month
  type NumericDay = refinedTimeTypes.Day
  type NumericHour = refinedTimeTypes.Hour
  type NumericMinute = refinedTimeTypes.Minute
  type NumericSecond = refinedTimeTypes.Second
  type NumericMillisecond = refinedTimeTypes.Millis
}

