package wen.datetime

import java.time.LocalTime

import wen.types.{Hour, Millisecond, Minute, Second}
import eu.timepit.refined.auto._
import eu.timepit.refined.refineV
import wen.types.NumericTypes._

final case class Time(hour: Hour, minute: Minute, second: Second, millisecond: Millisecond)

object Time {
  def apply(hour: Hour, minute: Minute, second: Second): Time =
    new Time(hour, minute, second, Millisecond(0))

  def apply(hour: Hour, minute: Minute): Time =
    new Time(hour, minute, Second(0), Millisecond(0))

  def apply(hour: Hour): Time =
    new Time(hour, Minute(0), Second(0), Millisecond(0))

  def apply(time: LocalTime): Time = {
    val eitherTime: Either[String, Time] = for {
      hour <- refineV[NumericHourConstraint](time.getHour)
      minute <- refineV[NumericMinuteConstraint](time.getMinute)
      second <- refineV[NumericSecondConstraint](time.getSecond)
      millisecond <- refineV[NumericMillisecondConstraint](time.getNano / 1000000)
    } yield new Time(Hour(hour), Minute(minute), Second(second), Millisecond(millisecond))

    // We run an unsafe operation here, because unless there's a bug in java.time.LocalTime
    // we'll always have a Right, and we don't want the user to have to deal with an Option
    // that is never a None.
    eitherTime.toOption.get
  }
}
