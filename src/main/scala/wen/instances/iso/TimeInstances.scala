package wen.instances.iso

import cats.implicits._
import cats.Show
import wen.datetime.Offset.OffsetType
import wen.datetime.{Offset, Time, ZoneTime}
import wen.types.{Hour, Minute, Second}

object TimeInstances extends TimeInstances

trait TimeInstances {
  implicit val isoTimeShowInstance: Show[Time] = new Show[Time] {
    override def show(t: Time): String =
      t match {
        case Time(Hour(h), Minute(m), Second(s), _) => f"${h.value}%02d:${m.value}%02d:${s.value}%02d"
      }
  }

  implicit val isoZoneTimeShowInstance: Show[ZoneTime] = new Show[ZoneTime] {
    override def show(t: ZoneTime): String =
      t match {
        case ZoneTime(t, Offset(_, Hour(oh), Minute(om))) if (oh.value === 0 && om.value === 0) =>
          s"${t.show}Z"
        case ZoneTime(t, Offset(ot, Hour(oh), Minute(om))) =>
          f"${t.show}${OffsetType.symbol(ot)}${oh.value}%02d:${om.value}%02d"
      }
  }

  implicit val isoOffsetShowInstance: Show[Offset] = new Show[Offset] {
    override def show(t: Offset): String =
      t match {
        case Offset(_, Hour(h), Minute(m)) if (h.value === 0 && m.value === 0) => "Z"
        case Offset(t, Hour(h), Minute(m)) => f"${OffsetType.symbol(t)}${h.value}%02d:${m.value}%02d"
      }
  }
}
