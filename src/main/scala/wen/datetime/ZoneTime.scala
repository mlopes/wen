package wen.datetime

import java.time.{OffsetTime, ZoneOffset}

import wen.datetime.Offset.OffsetType
import wen.types.{Hour, Minute}
import eu.timepit.refined.auto._

final case class ZoneTime(time: Time, offset: Offset)

final object ZoneTime {
  def apply(offsetTime: OffsetTime): ZoneTime = {
    ZoneTime(Time(offsetTime.toLocalTime), Offset(offsetTime.getOffset))
  }
}

final case class Offset(offsetType: OffsetType, hour: Hour, minute: Minute)
final object Offset {

  def apply(zoneOffset: ZoneOffset): Offset = {
    def getSignal(s: Int): OffsetType =
      if (s >= 0) UTCPlus
      else UTCMinus
    def getHour(s: Int): Int = Math.abs(s) / 60 / 60
    def getMinute(s: Int): Int = Math.abs(s) / 60 % 60

    val s: Int = zoneOffset.getTotalSeconds

    // Zone offset doesn't allow for more than -18 to +18 hours, we can
    // safely run get on Hour, and Minute of course, as we're guaranteeing
    // it doesn't go over 59
    Offset(getSignal(s), Hour.fromInt(getHour(s)).get, Minute.fromInt(getMinute(s)).get)
  }

  sealed trait OffsetType
  final case object UTCMinus extends OffsetType
  final case object UTCPlus extends OffsetType

  val UTC: Offset = Offset(UTCPlus, Hour(0), Minute(0))
}
