package wen.refine

import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.WordSpec
import org.scalacheck.Prop.forAll

class refineSpec extends WordSpec {

  "refineSpec" should {

    "refine Second should succeed" in {
      val second = Gen.choose(0, 59)

      forAll(second) { s =>
        refineSecond(s) match {
          case Right(s1) => s1 ===(s)
          case _ => false
        }
      }
    }

    "refine Second should fail" in {
      val notASecond = Arbitrary.arbitrary[Int] suchThat (x => x < 0 || x > 59)

      forAll(notASecond) { s =>
        refineSecond(s) match {
          case Left(_) => true
          case _ => false
        }
      }
    }

    "refine Minute should succeed" in {
      val minute = Gen.choose(0, 59)

      forAll(minute) { m =>
        refineMinute(m) match {
          case Right(m1) => m1 ===(m)
          case _ => false
        }
      }
    }

    "refine Minute should fail" in {
      val notAMinute = Arbitrary.arbitrary[Int] suchThat (x => x < 0 || x > 59)

      forAll(notAMinute) { m =>
        refineMinute(m) match {
          case Left(_) => true
          case _ => false
        }
      }
    }

    "refine Year should succeed" in {
      val year = Gen.posNum[Int]

      forAll(year) { y =>
        refineYear(y) match {
          case Right(y1) => y1 ===(y)
          case _ => false
        }
      }
    }

    "refine Year should fail for negative numbers" in {
      val notAYear = Gen.negNum[Int]

      forAll(notAYear) { y =>
        refineYear(y) match {
          case Left(_) => true
          case _ => false
        }
      }
    }

    "refine Year should fail for 0" in {
      refineYear(0) match {
        case Left(_) => true
        case _ => false
      }
    }

    "refine Hour should succeed" in {
      val hour = Gen.choose(1, 23)

      forAll(hour) { h =>
        refineHour(h) match {
          case Right(h1) => h1 ===(h)
          case _ => false
        }
      }
    }

    "refine Hour should fail" in {
      val notAHour = Arbitrary.arbitrary[Int] suchThat (x => x < 0 || x > 23)

      forAll(notAHour) { h =>
        refineHour(h) match {
          case Left(_) => true
          case _ => false
        }
      }
    }

    "refine Millisecond should succeed" in {
      val millisecond = Gen.choose(0, 999)

      forAll(millisecond) { m =>
        refineMilliSecond(m) match {
          case Right(m1) => m1 ===(m)
          case _ => false
        }
      }
    }

    "refine Millisecond should fail" in {
      val notAMillisecond = Arbitrary.arbitrary[Int] suchThat (x => x < 0 || x > 999)

      forAll(notAMillisecond) { m =>
        refineMilliSecond(m) match {
          case Left(_) => true
          case _ => false
        }
      }
    }

    "refine Day should succeed" in {
      val day = Gen.choose(1, 31)

      forAll(day) { d =>
        refineDay(d) match {
          case Right(d1) => d1 ===(d)
          case _ => false
        }
      }
    }

    "refine Day should fail" in {
      val notADay = Arbitrary.arbitrary[Int] suchThat (x => x < 1 || x > 31)

      forAll(notADay) { d =>
        refineMonth(d) match {
          case Left(_) => true
          case _ => false
        }
      }
    }

    "refine Month should succeed" in {
      val month = Gen.choose(1, 12)

      forAll(month) { m =>
        refineMonth(m) match {
          case Right(m1) => m1 ===(m)
          case _ => false
        }
      }
    }

    "refine Month should fail" in {
      val notAMonth = Arbitrary.arbitrary[Int] suchThat (x => x < 1 || x > 12)

      forAll(notAMonth) { m =>
        refineMonth(m) match {
          case Left(_) => true
          case _ => false
        }
      }
    }
  }
}
