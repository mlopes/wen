package com.mlopes.wen.types

sealed trait Month
final case object January extends Month
final case object February extends Month
final case object March extends Month
final case object April extends Month
final case object May extends Month
final case object June extends Month
final case object July extends Month
final case object August extends Month
final case object September extends Month
final case object October extends Month
final case object November extends Month
final case object December extends Month

object Month {
  def apply(month: Int): Option[Month] =
      if (month == 1) Some(January)
      else if (month == 2) Some(February)
      else if (month == 3) Some(March)
      else if (month == 4) Some(April)
      else if (month == 5) Some(May)
      else if (month == 6) Some(June)
      else if (month == 7) Some(July)
      else if (month == 8) Some(August)
      else if (month == 9) Some(September)
      else if (month == 10) Some(October)
      else if (month == 11) Some(November)
      else if (month == 12) Some(December)
      else None

  def toInt: Month => Int = {
    case January => 1
    case February => 2
    case March => 3
    case April => 4
    case May => 5
    case June => 6
    case July => 7
    case August => 8
    case September => 9
    case October => 10
    case November => 11
    case December => 12
  }
}

sealed trait WeekDay
final case object Sunday extends WeekDay
final case object Monday extends WeekDay
final case object Tuesday extends WeekDay
final case object Wednesday extends WeekDay
final case object Thursday extends WeekDay
final case object Friday extends WeekDay
final case object Saturday extends WeekDay

sealed trait Epoch
final case object AC extends Epoch
final case object BC extends Epoch

final class Year private(val year: Int, val epoch: Epoch) {
}

object Year {
  def apply(year: Int, epoch: Epoch): Option[Year] = {
    if(year > 0) Some(new Year(year, epoch))
    else None
  }

  def unapply(y: Year): Option[(Int, Epoch)] = Some((y.year, y.epoch))
}
