package wen

import wen.instances.datetime._
import wen.instances._

object implicits
  extends DateInstances
  with TimeInstances
  with DateTimeInstances
  with YearInstances
  with EpochInstances
  with MonthInstances
  with DayInstances
  with WeekDayInstances
  with HourInstances
  with MinuteInstances
  with SecondInstances
  with MillisecondInstances

