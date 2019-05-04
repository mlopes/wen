package wen.datetime

import java.time.LocalDate

import eu.timepit.refined.auto._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import wen.types._
import wen.test.Arbitraries._
import wen.test.Generators._

class DateSpec extends WordSpec with Matchers with TypeCheckedTripleEquals with ScalaCheckDrivenPropertyChecks {
  "Date" should {
    "be created for a valid date" in {
      val date1 = Date(Day(31), January, Year(2012, AD))
      val date2 = Date(Day(29), February, Year(2012, AD))
      val date3 = Date(Day(28), February, Year(2015, AD))
      val date4 = Date(Day(30), September, Year(2018, AD))

      date1 should ===(Some(Date.unsafe(Day(31), January, Year(2012, AD))))
      date2 should ===(Some(Date.unsafe(Day(29), February, Year(2012, AD))))
      date3 should ===(Some(Date.unsafe(Day(28), February, Year(2015, AD))))
      date4 should ===(Some(Date.unsafe(Day(30), September, Year(2018, AD))))
    }

    "fail to be created for an invalid date" in {
      val date1 = Date(Day(29), February, Year(2015, AD))
      val date2 = Date(Day(31), April, Year(2019, AD))
      val date3 = Date(Day(31), June, Year(2019, AD))
      val date4 = Date(Day(31), September, Year(2019, AD))
      val date5 = Date(Day(31), November, Year(2019, AD))

      date1 should ===(None)
      date2 should ===(None)
      date3 should ===(None)
      date4 should ===(None)
      date5 should ===(None)
    }

    "be created from an invalid date using unsafe" in {
      val date = Date.unsafe(Day(31), April, Year(2019, AD))

      date.day.day.value should ===(31)
      date.month should ===(April)
      date.year.year.value should ===(2019)
      date.year.epoch should ===(AD)
    }

    "be created from a LocalDate" in forAll { localDate: LocalDate =>
      val date = Date(localDate)
      date shouldBe a[Date]
    }

    "allow creation from LocalDate even for invalid dates" in forAll(negativeLeapYearAsIntGen) { negativeLeapYearAsInt: Int =>
      val localDate = LocalDate.parse(f"${negativeLeapYearAsInt}%05d-02-29")
      val date = Date(localDate)
      date shouldBe a[Date]
    }
  }
}
