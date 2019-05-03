package wen

import eu.timepit.refined.numeric.{Interval, Positive}
import eu.timepit.refined._
import wen.types.NumericTypes._

package object refine {

  def refineHour(hour: Int): Either[String, NumericHour] =
    refineV[NumericHourConstraint](hour)

  def refineMinute(minute: Int): Either[String, NumericMinute] =
    refineV[NumericMinuteConstraint](minute)

  def refineSecond(second: Int): Either[String, NumericSecond] =
    refineV[NumericSecondConstraint](second)

  def refineMillisecond(millisecond: Int): Either[String, NumericMillisecond] =
    refineV[NumericMillisecondConstraint](millisecond)

  def refineYear(year: Int): Either[String, NumericYear] =
    refineV[NumericYearConstraint](year)

  def refineMonth(month: Int): Either[String, NumericMonth] =
    refineV[NumericMonthConstraint](month)

  def refineDay(day: Int): Either[String, NumericDay] =
    refineV[NumericDayConstraint](day)


  private[wen] type NumericHourConstraint = Interval.Closed[W.`0`.T, W.`23`.T]
  private[wen] type NumericMinuteConstraint = Interval.Closed[W.`0`.T, W.`59`.T]
  private[wen] type NumericSecondConstraint = Interval.Closed[W.`0`.T, W.`59`.T]
  private[wen] type NumericMillisecondConstraint = Interval.Closed[W.`0`.T, W.`999`.T]
  private[wen] type NumericYearConstraint = Positive
  private[wen] type NumericMonthConstraint = Interval.Closed[W.`1`.T, W.`12`.T]
  private[wen] type NumericDayConstraint = Interval.Closed[W.`1`.T, W.`31`.T]
}
