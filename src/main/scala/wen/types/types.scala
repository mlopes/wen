package wen.types

import wen.types.NumericTypes._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Positive
import eu.timepit.refined.types.{time => refinedTimeTypes}
import wen.refine._

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
  def apply(numericMonth: NumericMonth): Month =
    numericMonth match {
      case Refined(1)=> January
      case Refined(2)=> February
      case Refined(3)=> March
      case Refined(4)=> April
      case Refined(5)=> May
      case Refined(6)=> June
      case Refined(7)=> July
      case Refined(8)=> August
      case Refined(9)=> September
      case Refined(10)=> October
      case Refined(11)=> November
      case Refined(12)=> December
    }

  def fromInt(i: Int): Option[Month] =
    refineMonth(i) match {
      case Right(numericMonth) => Some(Month(numericMonth))
      case Left(_) => None
    }

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
  def apply(year: NumericYear): Year = new Year(year, AD)

  def fromIntWithEpoch(year: Int, epoch: Epoch): Option[Year] =
    refineYear(year) match {
      case Right(y) => Some(Year(y, epoch))
      case Left(_) => None
    }

  def fromInt(year: Int): Option[Year] = Year.fromIntWithEpoch(year, AD)

}

final case class Day(day: NumericDay)

final object Day {
  private[wen] val min: Int = 1
  private[wen] val max: Int = 31

  def fromInt(day: Int): Option[Day] =
    refineDay(day) match {
      case Right(d) => Some(Day(d))
      case Left(_) => None
    }
}

final case class Hour(hour: NumericHour)

final object Hour {
  private[wen] val min: Int = 0
  private[wen] val max: Int = 23

  def fromInt(hour: Int): Option[Hour] =
    refineHour(hour) match {
      case Right(h) => Some(Hour(h))
      case Left(_) => None
    }
}

final case class Minute(minute: NumericMinute)

final object Minute {
  private[wen] val min: Int = 0
  private[wen] val max: Int = 59

  def fromInt(minute: Int): Option[Minute] =
    refineMinute(minute) match {
      case Right(m) => Some(Minute(m))
      case Left(_) => None
    }
}

final case class Second(second: NumericSecond)

final object Second {
  private[wen] val min: Int = 0
  private[wen] val max: Int = 59

  def fromInt(second: Int): Option[Second] =
    refineSecond(second) match {
      case Right(s) => Some(Second(s))
      case Left(_) => None
    }
}

final case class Millisecond(millisecond: NumericMillisecond)

final object Millisecond {
  private[wen] val min: Int = 0
  private[wen] val max: Int = 999

  def fromInt(millisecond: Int): Option[Millisecond] =
    refineMillisecond(millisecond) match {
      case Right(m) => Some(Millisecond(m))
      case Left(_) => None
    }
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

