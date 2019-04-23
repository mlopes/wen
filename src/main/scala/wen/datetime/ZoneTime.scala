package wen.datetime

import wen.datetime.Offset.OffsetType
import wen.types.{Hour, Minute}
import eu.timepit.refined.auto._

final case class ZoneTime(time: Time, offset: Offset)

case class Offset(offsetType: OffsetType, hour: Hour, minute: Minute)
object Offset {
  sealed trait OffsetType
  final case object UTCMinus extends OffsetType
  final case object UTCPlus extends OffsetType

  val UTC: Offset = Offset(UTCPlus, Hour(0), Minute(0))
}
