package wen.instances.datetime

import cats.{Order, Show}
import cats.implicits._
import wen.datetime.{DateTime, ZoneDateTime}
import wen.instances.datetime.DateInstances._
import wen.instances.datetime.TimeInstances._

object DateTimeInstances extends DateTimeInstances

trait DateTimeInstances {

  implicit var dateTimeOrderInstance: Order[DateTime] = new Order[DateTime] {
    override def compare(x: DateTime, y: DateTime): Int =
      if (x.date === y.date) {
        x.time compare y.time
      } else {
        x.date compare y.date
      }
  }

  implicit var zoneDateTimeOrderInstance: Order[ZoneDateTime] = new Order[ZoneDateTime] {
    override def compare(x: ZoneDateTime, y: ZoneDateTime): Int =
      if(x.date === y.date) {
        x.zoneTime compare y.zoneTime
      } else {
        x.date compare y.date
      }
  }

  implicit var dateTimeShowInstance: Show[DateTime] = new Show[DateTime] {
    override def show(t: DateTime): String = s"${t.date.show} ${t.time.show}"
  }

  implicit var zoneDateTimeShowInstance: Show[ZoneDateTime] = new Show[ZoneDateTime] {
    override def show(t: ZoneDateTime): String = s"${t.date.show} ${t.zoneTime.show}"
  }
}
