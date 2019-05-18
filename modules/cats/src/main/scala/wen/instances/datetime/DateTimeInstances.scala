package wen.instances.datetime

import cats.{Order, Show}
import cats.implicits._
import wen.datetime.{DateTime, ZoneDateTime}
import wen.instances.datetime.DateInstances._
import wen.instances.datetime.TimeInstances._

object DateTimeInstances extends DateTimeInstances

trait DateTimeInstances {

  implicit val dateTimeOrderInstance: Order[DateTime] = new Order[DateTime] {
    override def compare(x: DateTime, y: DateTime): Int =
      (x, y) match {
        case (DateTime(date1, time1), DateTime(date2, time2)) if date1 === date2 => time1 compare time2
        case (DateTime(date1, _), DateTime(date2, _)) => date1 compare date2
      }
  }

  implicit val zoneDateTimeOrderInstance: Order[ZoneDateTime] = new Order[ZoneDateTime] {
    override def compare(x: ZoneDateTime, y: ZoneDateTime): Int =
      (x, y) match {
        case (ZoneDateTime(date1, zoneTime1), ZoneDateTime(date2, zoneTime2)) if date1 === date2 =>
          zoneTime1 compare zoneTime2
        case (ZoneDateTime(date1, _), ZoneDateTime(date2, _)) => date1 compare date2
      }
  }

  implicit val dateTimeShowInstance: Show[DateTime] = new Show[DateTime] {
    override def show(t: DateTime): String = s"${t.date.show} ${t.time.show}"
  }

  implicit val zoneDateTimeShowInstance: Show[ZoneDateTime] = new Show[ZoneDateTime] {
    override def show(t: ZoneDateTime): String = s"${t.date.show} ${t.zoneTime.show}"
  }
}
