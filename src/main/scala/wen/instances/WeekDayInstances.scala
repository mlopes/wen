package wen.instances

import cats.implicits._
import cats.{Order, Show}
import wen.types._

object WeekDayInstances {

  implicit val weekDayOrderInstance: Order[WeekDay] = new Order[WeekDay] {
    override def compare(x: WeekDay, y: WeekDay): Int = {
      lazy val weekDayToInt: WeekDay => Int = {
        case Sunday => 1
        case Monday => 2
        case Tuesday => 3
        case Wednesday => 4
        case Thursday => 5
        case Friday => 6
        case Saturday => 7
      }
      Order[Int].compare(weekDayToInt(x), weekDayToInt(y))
    }
  }

  implicit val weekdayShowInstance: Show[WeekDay] = new Show[WeekDay] {
    override def show(t: WeekDay): String = t match {
      case Sunday => "Sunday"
      case Monday => "Monday"
      case Tuesday => "Tuesday"
      case Wednesday => "Wednesday"
      case Thursday => "Thursday"
      case Friday => "Friday"
      case Saturday => "Saturday"
    }
  }
}
