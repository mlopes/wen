package wen.datetime

import wen.types.{Hour, Millisecond, Minute, Second}
import eu.timepit.refined.auto._

final case class Time(hour: Hour, minute: Minute, second: Second, millisecond: Millisecond)

object Time {
  def apply(hour: Hour, minute: Minute, second: Second): Time =
    new Time(hour, minute, second, Millisecond(0))

  def apply(hour: Hour, minute: Minute): Time =
    new Time(hour, minute, Second(0), Millisecond(0))

  def apply(hour: Hour): Time =
    new Time(hour, Minute(0), Second(0), Millisecond(0))
}
