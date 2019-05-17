package wen.datetime

import java.time.LocalTime

import wen.types.{Hour, Millisecond, Minute, Second}
import wen.refine._
import eu.timepit.refined._

final case class Time(hour: Hour, minute: Minute, second: Second, millisecond: Millisecond)

final object Time {
  def apply(hour: Hour, minute: Minute, second: Second): Time =
    new Time(hour, minute, second, Millisecond(refineMV(0)))

  def apply(hour: Hour, minute: Minute): Time =
    new Time(hour, minute, Second(refineMV(0)), Millisecond(refineMV(0)))

  def apply(hour: Hour): Time =
    new Time(hour, Minute(refineMV(0)), Second(refineMV(0)), Millisecond(refineMV(0)))

  def apply(time: LocalTime): Time = {
    // We run unsafe operations here, because unless there's a bug in java.time.LocalDate
    // we'll always have a valid hour, minute, second, and millisecond, and we don't want
    // the user to have to deal with an Option that is never a None, as it breaks semantics.
    val hour = refineV[NumericHourConstraint].unsafeFrom(time.getHour)
    val minute = refineV[NumericMinuteConstraint].unsafeFrom(time.getMinute)
    val second = refineV[NumericSecondConstraint].unsafeFrom(time.getSecond)
    val millisecond = refineV[NumericMillisecondConstraint].unsafeFrom(time.getNano / 1000000)
    new Time(Hour(hour), Minute(minute), Second(second), Millisecond(millisecond))
  }
}
