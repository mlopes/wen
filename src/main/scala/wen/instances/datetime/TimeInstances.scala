package wen.instances.datetime

import cats.implicits._
import cats.{Order, Show}
import wen.datetime.Offset.{OffsetType, UTCMinus, UTCPlus}
import wen.datetime.{Offset, Time, ZoneTime}
import wen.types._

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
      lazy val zoneTimeToInt: ZoneTime => Int = {
        case ZoneTime(Time(Hour(h), Minute(m), Second(s), Millisecond(ms)), Offset(ot, Hour(oh), Minute(om))) =>
          val minutes = offsetOperation(ot)(h.value * 60 + m.value)(oh.value * 60 + om.value)
          val seconds = minutes * 60 + s.value
          seconds * 1000 + ms.value
      }

      Order[Int].compare(zoneTimeToInt(x), zoneTimeToInt(y))
    }
  }

  implicit val offsetOrderInstance: Order[Offset] = new Order[Offset] {
    override def compare(x: Offset, y: Offset): Int = {
      (x, y) match {
        case (Offset(_, Hour(h1), Minute(m1)), Offset(_, Hour(h2), Minute(m2)))
          if h1.value === 0 && m1.value === 0 && h2.value === 0 && m2.value === 0 => 0
        case (Offset(UTCPlus, _, _), Offset(UTCMinus, _, _)) => 1
        case (Offset(UTCMinus, _, _), Offset(UTCPlus, _, _)) => -1
        case (Offset(UTCPlus, h1, m1), Offset(UTCPlus, h2, m2)) =>
          Time(h1, m1) compare Time(h2, m2)
        case (Offset(UTCMinus, h1, m1), Offset(UTCMinus, h2, m2)) =>
          (Time(h1, m1) compare Time(h2, m2)) * -1
      }
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
    override def show(t: ZoneTime): String =
      t match {
        case ZoneTime(Time(Hour(h), Minute(m), Second(s), Millisecond(ms)), Offset(ot, Hour(oh), Minute(om))) =>
          val timeStr = f"${h.value}%02d:${m.value}%02d:${s.value}%02d.${ms.value}"
          val offsetStr = f"${offsetSymbol(ot)}${oh.value}%02d:${om.value}%02d"
          s"$timeStr $offsetStr"
      }
  }

  implicit val offsetShowInstance: Show[Offset] = new Show[Offset] {
    override def show(t: Offset): String =
      t match {
        case Offset(_, Hour(h), Minute(m)) if h.value === 0 && m.value === 0 => "00:00"
        case Offset(t, Hour(h), Minute(m)) => f"${offsetSymbol(t)}${h.value}%02d:${m.value}%02d"
      }
  }

  private lazy val offsetSymbol: OffsetType => String = {
    case UTCPlus => "+"
    case UTCMinus => "-"
  }
}
