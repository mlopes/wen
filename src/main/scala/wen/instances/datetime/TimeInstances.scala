package wen.instances.datetime

import cats.implicits._
import cats.{Order, Show}
import wen.datetime.Offset.{OffsetType, UTCMinus, UTCPlus}
import wen.datetime.{Time, ZoneTime}
import wen.instances.HourInstances._
import wen.instances.MinuteInstances._
import wen.instances.MillisecondInstances._
import wen.instances.SecondInstances._

object TimeInstances {

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
      s"${t.hour.show}:${t.minute.show}:${t.second.show}.${t.millisecond.show}"
  }

  implicit val zoneTimeShowInstance: Show[ZoneTime] = new Show[ZoneTime] {
    override def show(t: ZoneTime): String = {
      lazy val offsetSymbol: OffsetType => String = {
        case UTCPlus => "+"
        case UTCMinus => "-"
      }

      val timeStr = s"${t.time.hour.show}:${t.time.minute.show}:${t.time.second.show}.${t.time.millisecond.show}"
      val offsetStr = s"${offsetSymbol(t.offset.offsetType)}${t.offset.hour.show}:${t.offset.minute.show}"
      s"$timeStr $offsetStr"
    }
  }
}
