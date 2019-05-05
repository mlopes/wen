package wen.instances.datetime

import cats.implicits._
import cats.{Order, Show}
import wen.datetime.Offset.{OffsetType, UTCMinus, UTCPlus}
import wen.datetime.{Offset, Time, ZoneTime}
import wen.types.{Hour, Millisecond, Minute, Second}

object TimeInstances extends TimeInstances

trait TimeInstances {

  implicit val timeOrderInstance: Order[Time] = new Order[Time] {
    override def compare(x: Time, y: Time): Int = {
      lazy val timeToInt: Time => Int = { t =>
        val minutes = t.hour.hour.value * 60 + t.minute.minute.value
        val seconds = minutes * 60 + t.second.second.value
        seconds * 1000 + t.millisecond.millisecond.value
      }

      Order[Int].compare(timeToInt(x), timeToInt(y))
    }
  }

  implicit val zoneTimeOrderInstance: Order[ZoneTime] = new Order[ZoneTime] {
    override def compare(x: ZoneTime, y: ZoneTime): Int = {
      lazy val offsetOperation: OffsetType => (Int => Int => Int) = {
        case UTCMinus => {a: Int => { b: Int => a - b }}
        case UTCPlus => {a: Int => { b: Int => a + b }}
      }
      lazy val zoneTimeToInt: ZoneTime => Int = { t: ZoneTime =>
        val op = offsetOperation(t.offset.offsetType)
        val minutes = op(t.time.hour.hour.value * 60 + t.time.minute.minute.value)(
                         t.offset.hour.hour.value * 60 + t.offset.minute.minute.value)
        val seconds = minutes * 60 + t.time.second.second.value
        seconds * 1000 + t.time.millisecond.millisecond.value
      }

      Order[Int].compare(zoneTimeToInt(x), zoneTimeToInt(y))
    }
  }

  implicit val timeShowInstance: Show[Time] = new Show[Time] {
    override def show(t: Time): String =
      t match {
        case Time(Hour(h), Minute(m), Second(s), Millisecond(ms)) =>
          f"${h.value}%02d:${m.value}%02d:${s.value}%02d.${ms.value}"
      }

  }

  implicit val zoneTimeShowInstance: Show[ZoneTime] = new Show[ZoneTime] {
    override def show(t: ZoneTime): String = {
      lazy val offsetSymbol: OffsetType => String = {
        case UTCPlus => "+"
        case UTCMinus => "-"
      }

      t match {
        case ZoneTime(Time(Hour(h), Minute(m), Second(s), Millisecond(ms)), Offset(ot, Hour(oh), Minute(om))) =>
          val timeStr = f"${h.value}%02d:${m.value}%02d:${s.value}%02d.${ms.value}"
          val offsetStr = f"${offsetSymbol(ot)}${oh.value}%02d:${om.value}%02d"
          s"$timeStr $offsetStr"
      }
    }
  }
}
