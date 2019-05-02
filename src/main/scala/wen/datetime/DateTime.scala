package wen.datetime

import java.time.LocalDateTime

final case class DateTime(date: Date, time: Time)

object DateTime {
  def apply(localDateTime: LocalDateTime): DateTime =
    DateTime(Date(localDateTime.toLocalDate), Time(localDateTime.toLocalTime))
}
