package wen.datetime

import java.time.{Instant, OffsetDateTime, ZoneOffset}


final case class ZoneDateTime(date: Date, zoneTime: ZoneTime)

object ZoneDateTime {
  def apply(offsetDateTime: OffsetDateTime): ZoneDateTime =
    ZoneDateTime(Date(offsetDateTime.toLocalDate), ZoneTime(offsetDateTime.toOffsetTime))

  def apply(instant: Instant): ZoneDateTime =
    ZoneDateTime(instant.atOffset(ZoneOffset.UTC))
}
