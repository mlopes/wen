package wen.instances.iso

import cats.implicits._
import cats.Show
import wen.datetime.Offset.{OffsetType, UTCMinus, UTCPlus}
import wen.datetime.{Offset, Time, ZoneTime}
import wen.types.{Hour, Minute, Second}

object TimeInstances {
  implicit val isoTimeShowInstance: Show[Time] = new Show[Time] {
    override def show(t: Time): String =
      t match {
        case Time(Hour(h), Minute(m), Second(s), _) => f"${h.value}%02d:${m.value}%02d:${s.value}%02d"
      }
  }

  implicit val isoZoneTimeShowInstance: Show[ZoneTime] = new Show[ZoneTime] {
    override def show(t: ZoneTime): String = {
      lazy val offsetSymbol: OffsetType => String = {
        case UTCPlus => "+"
        case UTCMinus => "-"
      }

      t match {
        case ZoneTime(t, Offset(_, Hour(oh), Minute(om))) if (oh.value === 0 && om.value === 0) =>
          s"${t.show}Z"
        case ZoneTime(t, Offset(ot, Hour(oh), Minute(om))) =>
          f"${t.show}${offsetSymbol(ot)}${oh.value}%02d:${om.value}%02d"
      }
    }
  }
}
