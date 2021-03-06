[![Build Status](https://travis-ci.org/mlopes/wen.svg?branch=master)](https://travis-ci.org/mlopes/wen) 
[![Coverage Status](https://coveralls.io/repos/github/mlopes/wen/badge.svg?branch=master)](https://coveralls.io/github/mlopes/wen?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b6dc3789e00d4f19a3e8a4a02bf4ac61)](https://app.codacy.com/app/marco.paulo.lopes/wen?utm_source=github.com&utm_medium=referral&utm_content=mlopes/wen&utm_campaign=Badge_Grade_Dashboard)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
![Maven Central](https://img.shields.io/maven-central/v/dev.mlopes/wen_2.12.svg?label=version)

# wen

> 'Wen considered the nature of time and understood that the universe is, instant by instant, recreated anew. Therefore, he understood, there is in truth no past, only a memory of the past. Blink your eyes, and the world you see next did not exist when you closed them. Therefore, he said, the only appropriate state of the mind is surprise. The only appropriate state of the heart is joy. The sky you see now, you have never seen before. The perfect moment is now. Be glad of it.'
>
> ― Terry Pratchett, Thief of Time

Date and time types and instances

- [wen](#wen)
  - [Getting Started](#getting-started)
  - [What is Wen?](#what-is-wen)
    - [What Wen isn't?](#what-wen-isnt)
  - [Types](#types)
    - [Date/Time Components](#datetime-components)
      - [Day](#day)
      - [Month](#month)
      - [Year](#year)
      - [Epoch](#epoch)
      - [WeekDay](#weekday)
      - [Hour](#hour)
      - [Minute](#minute)
      - [Second](#second)
      - [Millisecond](#millisecond)
    - [Full Representations](#full-representations)
      - [Time](#time)
      - [ZoneTime](#zonetime)
        - [Offset](#offset)
        - [OffsetType](#offsettype)
      - [Date](#date)
      - [DateTime](#datetime)
      - [ZoneDateTime](#zonedatetime)
    - [Numeric types](#numeric-types)
      - [NumericDay](#numericday)
      - [NumericMonth](#numericmonth)
      - [NumericYear](#numericyear)
      - [NumericHour](#numerichour)
      - [NumericMinute](#numericminute)
      - [NumericSecond](#numericsecond)
      - [NumericMillisecond](#numericmillisecond)
  - [Refinement Helpers](#refinement-helpers)
  - [ISO-8601 instances](#iso-8601-instances)
  - [Circe Instances](#circe-instances)

## Getting Started

Wen is available for Scala 2.12. You can add wen to your sbt project by adding the following to
your `build.sbt`:

```scala
libraryDependencies += "dev.mlopes" %% "wen" % "1.0.0-M1"
libraryDependencies += "dev.mlopes" %% "wen-cats" % "1.0.0-M1" // For cats instances
libraryDependencies += "dev.mlopes" %% "wen-circe" % "1.0.0-M1" // For circe encoders and decoders
```

## What is Wen?

Wen provides types to safely represent months, weekdays, years, epoch, hours, minutes,
seconds and milliseconds, as well as instances of the `Order`, `Eq` and `Show` cats type classes.

Should cats ever support the `Enum` and `Bounded` type classes, and we should implement those as
well, for the relevant types.

### What Wen isn't?

Wen does not aim for date/time related functionality like the one provided by the
`java.time`. Its purpose is to provide stateless representations for date/time data types.

## Types

Wen provides types to represent date/time components, as well as types for full time and date representations, and auxiliary refined types for numeric representation of date/time components.

### Date/Time Components

#### Day

| Constructors |
| ------------ |
| Day(day: [NumericDay](#NumericDay)): Day |
| Day.fromInt(day: Int): Option[Day] |

| Instances |
| --------- |
| Order[Day] |
| Eq[Day] |
| Show[Day] |

**Usage**

```scala
import wen.types._
import eu.timepit.refined.{W, refineMV}
import eu.timepit.refined.numeric.Interval

val day = Day.fromInt(31)
// day: Option[wen.types.Day] = Some(Day(31))

val notDay = Day.fromInt(32)
// notDay: Option[wen.types.Day] = None

// You need to use refineV when refining non-literal values
val refinedDay = Day(refineMV[Interval.Closed[W.`1`.T, W.`31`.T]](22))
// refinedDay: wen.types.Day = Day(22)

import eu.timepit.refined.auto._


// This works because we add the type annotation to `autoRefinedDay`
// so Scala infers that we're using the constructor
// Day(numericDay: NumericDay): Day
// refined.auto provides the necessary implicit conversion from Int.
val autoRefinedDay: Day = Day(25)
// autoRefinedDay: wen.types.Day = Day(25)
```

Because instances of cats's `Eq`, `Order` and `Show` are available, we can also do the following:

```scala
import cats.implicits._ // for cats syntax
import wen.implicits._ // for wen's instances

refinedDay.show
// res0: String = 22

autoRefinedDay >= refinedDay
// res1: Boolean = true

refinedDay =!= autoRefinedDay
// res2: Boolean = true

```

#### Month

| Constructors |
| ------------ |
| January |
| February |
| March |
| April |
| May |
| June |
| July |
| August|
| September |
| October |
| November |
| December |
| Month(numericMonth: [NumericMonth](#NumericMonth)): Month |
| Month.fromInt(month: Int): Option[Month] |
| Month.fromString(month: String): Option[Month] |

| Instances |
| --------- |
| Order[Month] |
| Eq[Month] |
| Show[Month] |

**Members**

```scala
Month.asInt: Int
```

Returns the ordinal number of a Month, starting at 1 for January and ending in 12 for December.

**Usage**

```scala
import wen.types._
import eu.timepit.refined.{W, refineMV}
import eu.timepit.refined.numeric.Interval

val month: Month = December
// month: wen.types.Month = December

val monthFromInt = Month.fromInt(7)
// monthFromInt: Option[wen.types.Month] = Some(July)

val monthFromString = Month.fromString("July")
// monthFromString: Option[wen.types.Month] = Some(July)

val notMonth = Month.fromInt(24)
// notMonth: Option[wen.types.Month] = None

val notMonthFromString = Month.fromString("Grune")
// notMonthFromString: Option[wen.types.Month] = None

// You need to use refineV when refining non-literal values
val refinedMonth = Month(refineMV[Interval.Closed[W.`1`.T, W.`12`.T]](4))
// refinedMonth: wen.types.Month = April

val monthInt = June.asInt
// monthInt: Int = 6

import eu.timepit.refined.auto._

val autoRefinedMonth: Month =  Month(8)
// autoRefinedMonth: wen.types.Month = August
```

Because instances of cats's `Eq`, `Order` and `Show` are available, we can also do the following:

```scala
import cats.implicits._ // for cats syntax
import wen.implicits._ // for wen's instances

refinedMonth.show
// res0: String = April

refinedMonth > autoRefinedMonth
// res1: Boolean = false

refinedMonth === autoRefinedMonth
// res2: Boolean = false

// Note that we have to specify the type Month so that Scala doesn't infer the type as July
(July : Month).show
// res3: String = July
```

#### Year

| Constructors |
| ------------ |
| Year(year: [NumericYear](#NumericYear)): Year |
| Year(year: [NumericYear](#NumericYear), epoch: [Epoch](#Epoch)): Year |
| Year.fromInt(year: Int): Option[Year] |
| Year.fromIntWithEpoch(year: Int, epoch: [Epoch](#Epoch)): Option[Year] |

| Instances |
| --------- |
| Order[Year] |
| Eq[Year] |
| Show[Year] |

**Usage**

```scala
import wen.types._
import eu.timepit.refined.{W, refineMV}
import eu.timepit.refined.numeric.{Interval, Positive}

val adYear = Year.fromIntWithEpoch(2019, AD)
// adYear: Option[wen.types.Year] = Some(Year(2019,AD))

val yearWithDefaultEpoch1 = Year.fromInt(2012)
// yearWithDefaultEpoch1: Option[wen.types.Year] = Some(Year(2012,AD))

val bcYear = Year.fromIntWithEpoch(19, BC)
// bcYear: Option[wen.types.Year] = Some(Year(19,BC))

val notYear = Year.fromIntWithEpoch(-21, AD)
// notYear: Option[wen.types.Year] = None

// You need to use refineV when refining non-literal values
val refinedYear = Year(refineMV[Positive](2019), AD)
// refinedYear: wen.types.Year = Year(2019,AD)

val yearWithDefaultEpoch = Year(refineMV[Positive](2011))
// yearWithDefaultEpoch: wen.types.Year = Year(2011,AD)

import eu.timepit.refined.auto._

val autoRefinedYear: Year = Year(2000, AD)
// autoRefinedYear: wen.types.Year = Year(2000,AD)
```

Because instances of cats's `Eq`, `Order` and `Show` are available, we can also do the following:

```scala
import cats.implicits._ // for cats syntax
import wen.implicits._ // for wen's instances

refinedYear.show
// res0: String = 2019

yearWithDefaultEpoch.show
// res1: String = 2011

refinedYear <= refinedYear
// res2: Boolean = true

refinedYear ===  autoRefinedYear
// res3: Boolean = false
```

#### Epoch

| Constructors |
| ------------ |
| BC |
| AD |
| Epoch.fromString(epoch: String) |

| Instances |
| --------- |
| Order[Epoch] |
| Eq[Epoch] |
| Show[Epoch] |

```scala
val epochFromString = Epoch.fromString("AD")
// epochFromString: Option[wen.types.Epoch] = Some(AD)

val notEpochFromString = Epoch.fromString("ACDC")
// notEpochFromString: Option[wen.types.Epoch] = None
```

#### WeekDay

| Constructors |
| ------------ |
| Sunday |
| Monday |
| Tuesday |
| Wednesday |
| Thursday |
| Friday |
| Saturday |
| WeekDay.fromString(weekDay: String): Option[WeekDay] |

| Instances |
| --------- |
| Order[WeekDay] |
| Eq[WeekDay] |
| Show[WeekDay] |

The provided `Order` instance starts on `Sunday` and ends on `Saturday`.

Because instances of cats's `Eq`, `Order` and `Show` are available, we can do the following:

**Usage**

```scala
import cats.implicits._ // for cats syntax
import wen.implicits._ // for wen's instances

val oneDay: WeekDay = Tuesday
// oneDay: wen.types.WeekDay = Tuesday

val otherDay: WeekDay = Friday
// otherDay: wen.types.WeekDay = Friday

val yetAnotherDay: WeekDay = WeekDay.fromString("Saturday")
// yetAnotherDay: Option[wen.types.WeekDay] = Some(Saturday)

val notAnotherDay: WeekDay = WeekDay.fromString("Caturday")
// notAnotherDay: Option[wen.types.WeekDay] = None

otherDay < oneDay
// res0: Boolean = false

otherDay ===  oneDay
// res1: Boolean = false

otherDay.show
// res2: String = Friday

// Note that we have to specify the type WeekDay so that Scala doesn't infer the type as Saturday
(Saturday: WeekDay).show
// res3: String = Saturday
```

There's also an `Order[WeekDay]`  instance starting on `Monday`, but it requires the user
to explicitly import it and make it implicit. Note that if you import the default `Order`
instance for weekday, it will conflict with this one and the compiler won't be able to infer
which one to use.

**Usage**

```scala
import cats.implicits._

implicit val orderInstance = wen.instances.WeekDayInstances.mondayFirstWeekDayOrderInstance
orderInstance: cats.Order[wen.types.WeekDay] = wen.instances.WeekDayInstances$$anon$1@7da2d02d

import wen.types._

val oneDay: WeekDay = Sunday
// oneDay: wen.types.WeekDay = Sunday

val otherDay: WeekDay = Monday
// otherDay: wen.types.WeekDay = Monday

otherDay < oneDay
// res0: Boolean = true
```

#### Hour

| Constructors |
| ------------ |
| Hour(hour: [NumericHour](#NumericHour)): Hour |
| Hour.fromInt(hour: Int): Option[Hour] |

| Instances |
| --------- |
| Order[Hour] |
| Eq[Hour] |
| Show[Hour] |

**Usage**

```scala
import wen.types._
import eu.timepit.refined.{W, refineMV}
import eu.timepit.refined.numeric.{Interval, Positive}

val hour = Hour.fromInt(23)
// hour: Option[wen.types.Hour] = Some(Hour(23))

val notHour = Hour.fromInt(25)
// notHour: Option[wen.types.Hour] = None

// You need to use refineV when refining non-literal values
val refinedHour = Hour(refineMV[Interval.Closed[W.`0`.T, W.`23`.T]](10))
// refinedHour: wen.types.Hour = Hour(10)

import eu.timepit.refined.auto._

val autoRefinedHour: Hour = Hour(12)
// autoRefinedHour: wen.types.Hour = Hour(12)
```

Because instances of cats's `Eq`, `Order` and `Show` are available, we can also do the following:

```scala
import cats.implicits._ // for cats syntax
import wen.implicits._ // for wen's instances

refinedHour.show
// res0: String = 10

refinedHour > autoRefinedHour
// res1: Boolean = false

autoRefinedHour === autoRefinedHour
// res2: Boolean = true
```

#### Minute

| Constructors |
| ------------ |
| Minute(minute: [NumericMinute](#NumericMinute)): Minute |
| Minute.fromInt(minute: Int): Option[Minute] |

| Instances |
| --------- |
| Order[Minute] |
| Eq[Minute] |
| Show[Minute] |

**Usage**

```scala
import wen.types._
import eu.timepit.refined.{W, refineMV}
import eu.timepit.refined.numeric.{Interval, Positive}

val minute = Minute.fromInt(0)
// minute: Option[wen.types.Minute] = Some(Minute(0))

val notMinute = Minute.fromInt(-12)
// notMinute: Option[wen.types.Minute] = None

// You need to use refineV when refining non-literal values
val refinedMinute: Minute = Minute(refineMV[Interval.Closed[W.`0`.T, W.`59`.T]](15))
// refinedMinute: wen.types.Minute = Minute(15)

import eu.timepit.refined.auto._

val autoRefinedMinute: Minute = Minute(45)
// autoRefinedMinute: wen.types.Minute = Minute(45)
```

Because instances of cats's `Eq`, `Order` and `Show` are available, we can also do the following:

```scala
import cats.implicits._ // for cats syntax
import wen.implicits._ // for wen's instances

autoRefinedMinute.show
// res0: String = 45

autoRefinedMinute > refinedMinute
// res1: Boolean = true

autoRefinedMinute =!= refinedMinute
// res2: Boolean = true
```

#### Second

| Constructors |
| ------------ |
| Second(second: [NumericSecond](#NumericSecond)): Second |
| Second.fromInt(second: Int): Option[Second] |

| Instances |
| --------- |
| Order[Second] |
| Eq[Second] |
| Show[Second] |

**Usage**

```scala
import wen.types._
import eu.timepit.refined.{W, refineMV}
import eu.timepit.refined.numeric.{Interval, Positive}

val second = Second.fromInt(42)
// second: Option[wen.types.Second] = Some(Second(42))

val notSecond = Second.fromInt(591)
// notSecond: Option[wen.types.Second] = None

// You need to use refineV when refining non-literal values
val refinedSecond = Second(refineMV[Interval.Closed[W.`0`.T, W.`59`.T]](20))
// refinedSecond: wen.types.Second = Second(20)

import eu.timepit.refined.auto._

val autoRefinedSecond: Second = Second(12)
// autoRefinedSecond: wen.types.Second = Second(12)
```

Because instances of cats's `Eq`, `Order` and `Show` are available, we can also do the following:

```scala
import cats.implicits._ // for cats syntax
import wen.implicits._ // for wen's instances

refinedSecond.show
// res0: String = Some(20)

refinedSecond > autoRefinedSecond
// res1: Boolean = true

refinedSecond === autoRefinedSecond
// res2: Boolean = false
```

#### Millisecond

| Constructors |
| ------------ |
| Millisecond(millisecond: [NumericMillisecond](#NumericMillisecond)): Millisecond |
| Millisecond.fromInt(millisecond: Int): Option[Millisecond] |

| Instances |
| --------- |
| Order[Millisecond] |
| Eq[Millisecond] |
| Show[Millisecond] |

**Usage**

```scala
import wen.types._
import eu.timepit.refined.{W, refineMV}
import eu.timepit.refined.numeric.{Interval, Positive}

val millisecond = Millisecond.fromInt(456)
// millisecond: Option[wen.types.Millisecond] = Some(Millisecond(456))

val notMillisecond = Millisecond.fromInt(-1)
// notMillisecond: Option[wen.types.Millisecond] = None

// You need to use refineV when refining non-literal values
val refinedMillisecond = Millisecond(refineMV[Interval.Closed[W.`0`.T,
W.`999`.T]](999))
// refinedMillisecond: wen.types.Millisecond = Millisecond(999)

import eu.timepit.refined.auto._

val autoRefinedMillisecond: Millisecond = Millisecond(999)
// autoRefinedMillisecond: wen.types.Millisecond = Millisecond(999)
```

Because instances of cats's `Eq`, `Order` and `Show` are available, we can also do the following:

```scala
import cats.implicits._ // for cats syntax
import wen.implicits._ // for wen's instances

refinedMillisecond.show
// res0: String = 999

refinedMillisecond >= autoRefinedMillisecond
// res1: Boolean = true

refinedMillisecond === autoRefinedMillisecond
// res2: Boolean = true
```

### Full Representations

#### Time

| Constructors |
| ------------ |
| Time(hour: [Hour](#Hour), minute: [Minute](#Minute), second: [Second](#Second), millisecond: [Millisecond](#Millisecond)): Time |
| Time(hour: [Hour](#Hour), minute: [Minute](#Minute), second: [Second](#Second)): Time |
| Time(hour: [Hour](#Hour), minute: [Minute](#Minute)): Time |
| Time(hour: [Hour](#Hour)): Time |
| Time(time: LocalTime): Time  |

| Instances |
| --------- |
| Order[Time] |
| Eq[Time] |
| Show[Time] |

Values for the properties that are not specified, will default to 0.

**Usage**

```scala
import wen.types._
import eu.timepit.refined.auto._
import wen.datetime._

val time1 = Time(Hour(7), Minute(5), Second(10), Millisecond(200))
// time1: wen.datetime.Time = Time(Hour(7),Minute(5),Second(10),Millisecond(200))

val time2 = Time(Hour(7), Minute(5), Second(10))
// time2: wen.datetime.Time = Time(Hour(7),Minute(5),Second(10),Millisecond(0))

val time3 = Time(Hour(7), Minute(5))
// time3: wen.datetime.Time = Time(Hour(7),Minute(5),Second(0),Millisecond(0))

val time4 = Time(Hour(7))
// time4: wen.datetime.Time = Time(Hour(7),Minute(0),Second(0),Millisecond(0))

import java.time.LocalTime

val time5 = Time(LocalTime.now)
// time5: wen.datetime.Time = Time(Hour(7),Minute(2),Second(48),Millisecond(159))
```

Because instances of cats's `Eq`, `Order` and `Show` are available, we can also do the following:

```scala
import cats.implicits._ // for cats syntax
import wen.implicits._ // for wen's instances

time1.show
// res0: String = 07:05:10.200

time3.show
// res1: String = 07:05:00.0

time1 > time3
// res2: Boolean = true

time2 =!= time4
// res3: Boolean = true
```

To create a `Time` from non-refined values, you can do the following:

```scala
import wen.types._
import wen.datetime._

val optionTime = for {
  hour <- Hour.fromInt(12)
  minute <- Minute.fromInt(15)
  second <- Second.fromInt(59)
  millisecond <- Millisecond.fromInt(908)
} yield Time(hour, minute, second, millisecond)
// optionTime: Option[wen.datetime.Time] = Some(Time(Hour(12),Minute(15),Second(59),Millisecond(908)))
```

#### ZoneTime

| Constructors |
| ------------ |
| ZoneTime(time: [Time](#Time), offset: [Offset](#Offset)): ZoneTime |
| ZoneTime(offsetTime: OffsetTime): ZoneTime |

| Instances |
| --------- |
| Order[ZoneTime] |
| Eq[ZoneTime] |
| Show[ZoneTime] |

##### Offset

| Constructors |
| ------------ |
| Offset(offsetType: [OffsetType](#OffsetType), hour: [Hour](#Hour), minute: [Minute](#Minute)): Offset |
| Offset(zoneOffset: ZoneOffset): Offset |

**Members**

```scala
UTC: Offset = Offset([UTCPlus](#OffsetType), Hour(0), Minute(0))
```

Because instances of cats's `Eq`, `Order` and `Show` are available, we can also do the following:

```scala
import wen.datetime._
import wen.implicits._
import cats.implicits._
import wen.types.

val offset1 = Offset.UTC
// offset1: wen.datetime.Offset = Offset(UTCPlus,Hour(0),Minute(0))

val offset2 = Offset(Offset.UTCMinus, Hour.fromInt(5).get, Minute.fromInt(45).get)
// offset2: wen.datetime.Offset = Offset(UTCMinus,Hour(5),Minute(45))

val offset3 = Offset(Offset.UTCPlus, Hour.fromInt(12).get, Minute.fromInt(20).get)
// offset3: wen.datetime.Offset = Offset(UTCPlus,Hour(12),Minute(20))


offset1 > offset2
// res0: Boolean = true

offset3 > offset2
// res1: Boolean = true

offset2 > offset3
// res2: Boolean = false

offset1 === offset2
// res3: Boolean = false

offset1 =!= offset3
// res4: Boolean = true

offset1.show
// res5: String = +00:00

offset2.show
// res6: String = -05:45

offset3.show
// res7: String = +12:20
```

A Show instance in ISO format is also available:

```scala
import wen.types._
import cats.implicits._
import wen.datetime._
import wen.instances.iso.isoOffsetShowInstance

val offset1 = Offset.UTC
// offset1: wen.datetime.Offset = Offset(UTCPlus,Hour(0),Minute(0))

val offset2 = Offset(Offset.UTCMinus, Hour.fromInt(5).get, Minute.fromInt(45).get)
// offset2: wen.datetime.Offset = Offset(UTCMinus,Hour(5),Minute(45))

val offset3 = Offset(Offset.UTCPlus, Hour.fromInt(12).get, Minute.fromInt(20).get)
// offset3: wen.datetime.Offset = Offset(UTCPlus,Hour(12),Minute(20))

offset1.show
// res0: String = Z

offset2.show
// res1: String = -05:45

offset3.show
// res2: String = +12:20
```


##### OffsetType
 
| Constructors |
| ------------ |
| UTCMinus |
| UTCPlus |

**Usage**

```scala
import wen.types._
import eu.timepit.refined.auto._
import wen.datetime._

val time1 = Time(Hour(10), Minute(11), Second(12), Millisecond(13))
// time1: wen.datetime.Time = Time(Hour(10),Minute(11),Second(12),Millisecond(13))

val zoneTime1 = ZoneTime(time1, Offset.UTC)
// zoneTime1: wen.datetime.ZoneTime = ZoneTime(Time(Hour(10),Minute(11),Second(12),Millisecond(13)),Offset(UTCPlus,Hour(0),Minute(0)))

val offset1 = Offset(Offset.UTCPlus, Hour(1), Minute(0))
// offset1: wen.datetime.Offset = Offset(UTCPlus,Hour(1),Minute(0))

val offset2 = Offset(Offset.UTCMinus, Hour(1), Minute(0))
// offset2: wen.datetime.Offset = Offset(UTCMinus,Hour(1),Minute(0))

val zoneTime2 = ZoneTime(time1, offset1)
// zoneTime2: wen.datetime.ZoneTime = ZoneTime(Time(Hour(10),Minute(11),Second(12),Millisecond(13)),Offset(UTCPlus,Hour(1),Minute(0)))

val zoneTime3 = ZoneTime(time1, offset2)
// zoneTime3: wen.datetime.ZoneTime = ZoneTime(Time(Hour(10),Minute(11),Second(12),Millisecond(13)),Offset(UTCMinus,Hour(1),Minute(0)))

val time2 = Time(Hour(9), Minute(11), Second(12), Millisecond(13))
time2: wen.datetime.Time = Time(Hour(9),Minute(11),Second(12),Millisecond(13))

val zoneTime4 = ZoneTime(time2, offset1)
// zoneTime4: wen.datetime.ZoneTime = ZoneTime(Time(Hour(9),Minute(11),Second(12),Millisecond(13)),Offset(UTCPlus,Hour(1),Minute(0)))

import java.time.ZoneOffset

val offset3 = Offset(ZoneOffset.MIN)
// offset3: wen.datetime.Offset = Offset(UTCMinus,Hour(18),Minute(0))

import java.time.OffsetTime

val zoneTime5 = ZoneTime(OffsetTime.now)
// zoneTime5: wen.datetime.ZoneTime = ZoneTime(Time(Hour(21),Minute(33),Second(10),Millisecond(1)),Offset(UTCPlus,Hour(1),Minute(0)))
```

Because instances of cats's `Eq`, `Order` and `Show` are available, we can also do the following:

```scala
import cats.implicits._ // for cats syntax
import wen.implicits._ // for wen's instances

zoneTime1.show
// res0: String = 10:11:12.13 +00:00

zoneTime2.show
// res1: String = 10:11:12.13 +01:00

zoneTime4.show
// res2: String = 09:11:12.13 +01:00

zoneTime1 < zoneTime2
// res3: Boolean = true

zoneTime1 === zoneTime4
// res4: Boolean = true
```

#### Date

| Constructors |
| ------------ |
| Date(localDate: LocalDate): Date |
| Date.safe(day: [Day](#Day), month: [Month](#Month), year: [Year](#Year)): Option[Date] |
| Date.unsafe(day: [Day](#Day), month: [Month](#Month), year: [Year](#Year)): Date |

| Instances |
| --------- |
| Order[Date] |
| Eq[Date] |
| Show[Date] |

The constructor `Date.safe(day: Day, month: Month, year: Year): Option[Date]` only allows the creation of valid dates, and will return `None` for invalid dates. The unsafe constructor `Date.unsafe(day: Day, month: Month, year: Year): Date` allows for creating invalid date combinations such as _30 February 2019_.

Dates created from `java.time.LocalDate are unsafe, as `java.time.LocalDate` allow for invalid dates in the BC epoch. See [issue #19](https://github.com/mlopes/wen/issues/19) for details of why. For all AD, and non leap year BC dates, date creation should yield valid dates only. This is a problem with `java.time.LocalDate`, if you wish to avoid this you'll need to use one of the other safe constructors.

**Usage**

```scala
import wen.types._
import eu.timepit.refined.auto._
import wen.datetime._

val date = Date.safe(Day(12), Month(8), Year(2016, AD)) 
// date: Option[wen.datetime.Date] = Some(Date(Day(12),August,Year(2016,AD)))

val notDate = Date.safe((31), September, Year(2000, AD))
// notDate: Option[wen.datetime.Date] = None

val unsafeDate = Date.unsafe(Day(31), August, Year(2019, AD))
// unsafeDate: wen.datetime.Date = Date(Day(31),August,Year(2019,AD))

// This creates an invalid date
val unsafeNotDate = Date.unsafe(Day(31), February, Year(2018, AD))
// unsafeNotDate: wen.datetime.Date = Date(Day(31),February,Year(2018,AD))

import java.time.LocalDate

val date2 = Date(LocalDate.now)
// date2: wen.datetime.Date = Date(Day(2),May,Year(2019,AD))
```

Because instances of cats's `Eq`, `Order` and `Show` are available, we can also do the following:

```scala
import cats.implicits._ // for cats syntax
import wen.implicits._ // for wen's instances

 unsafeDate.show
// res0: String = 31 August 2019

val unsafeDate1 = Date.unsafe(Day(31), August, Year(2018, AD))
// unsafeDate1: wen.datetime.Date = Date(Day(31),August,Year(2018,AD))

unsafeDate < unsafeDate1
// res1: Boolean = false

unsafeDate === unsafeDate1
// res1: Boolean = false
```

#### DateTime

| Constructors |
| ------------ |
| DateTime(date: [Date](#Date), time: [Time](#Time)): DateTime |
| DateTime(localDateTime: LocalDateTime): DateTime |

| Instances |
| --------- |
| Order[DateTime] |
| Eq[DateTime] |
| Show[DateTime] |

**Usage**

```scala
import wen.types._
import eu.timepit.refined.auto._
import wen.datetime._

val date1 = Date.unsafe(Day(12), Month(8), Year(2016, AD))
// date1: wen.datetime.Date = Date(Day(12),August,Year(2016,AD))

val date2 = Date.unsafe(Day(22), Month(8), Year(1982, AD))
// date2: wen.datetime.Date = Date(Day(22),August,Year(1982,AD))

val time1 = Time(Hour(7), Minute(5))
// time1: wen.datetime.Time = Time(Hour(7),Minute(5),Second(0),Millisecond(0))

val time2 = Time(Hour(20), Minute(30))
// time2: wen.datetime.Time = Time(Hour(20),Minute(30),Second(0),Millisecond(0))

val dateTime1 = DateTime(date1, time1)
// dateTime1: wen.datetime.DateTime = DateTime(Date(Day(12),August,Year(2016,AD)),Time(Hour(7),Minute(5),Second(0),Millisecond(0)))

val dateTime2 = DateTime(date2, time2)
// dateTime2: wen.datetime.DateTime = DateTime(Date(Day(22),August,Year(1982,AD)),Time(Hour(20),Minute(30),Second(0),Millisecond(0)))

val dateTime3 = DateTime(date1, time2)
// dateTime3: wen.datetime.DateTime = DateTime(Date(Day(12),August,Year(2016,AD)),Time(Hour(20),Minute(30),Second(0),Millisecond(0)))

import java.time.LocalDateTime

val dateTime4 = DateTime(LocalDateTime.now)
// dateTime4: wen.datetime.DateTime = DateTime(Date(Day(2),May,Year(2019,AD)),Time(Hour(20),Minute(40),Second(16),Millisecond(612)))
```

Because instances of cats's `Eq`, `Order` and `Show` are available, we can also do the following:

```scala
import cats.implicits._ // for cats syntax
import wen.implicits._ // for wen's instances

dateTime1.show
// res0: String = 12 August 2016 07:05:00.0

dateTime1 > dateTime2
// res1: Boolean = true

dateTime3 > dateTime1
// res2: Boolean = true

dateTime1 =!= dateTime2
// res3: Boolean = true
```

#### ZoneDateTime

| Constructors |
| ------------ |
| ZoneDateTime(date: [Date](#Date), zoneTime: [ZoneTime](#ZoneTime)): ZoneDateTime |
| ZoneDateTime(offsetDateTime: OffsetDateTime): ZoneDateTime |
| ZoneDateTime(instant: Instant): ZoneDateTime |

| Instances |
| --------- |
| Order[ZoneDateTime] |
| Eq[ZoneDateTime] |
| Show[ZoneDateTime] |

**Usage**

```scala
import wen.types._
import eu.timepit.refined.auto._
import wen.datetime._

val date1 = Date.unsafe(Day(12), Month(8), Year(2016, AD))
// date1: wen.datetime.Date = Date(Day(12),August,Year(2016,AD))

val time1 = ZoneTime(Time(Hour(7), Minute(5)), Offset.UTC)
// time1: wen.datetime.ZoneTime = ZoneTime(Time(Hour(7),Minute(5),Second(0),Millisecond(0)),Offset(UTCPlus,Hour(0),Minute(0)))

val time2 = ZoneTime(Time(Hour(7), Minute(5)), Offset(Offset.UTCMinus, Hour(1), Minute(0)))
// time2: wen.datetime.ZoneTime = ZoneTime(Time(Hour(7),Minute(5),Second(0),Millisecond(0)),Offset(UTCMinus,Hour(1),Minute(0)))

val time3 = ZoneTime(Time(Hour(8), Minute(5)), Offset(Offset.UTCMinus, Hour(1), Minute(0)))
// time3: wen.datetime.ZoneTime = ZoneTime(Time(Hour(8),Minute(5),Second(0),Millisecond(0)),Offset(UTCMinus,Hour(1),Minute(0)))

val zoneDateTime1 = ZoneDateTime(date1, time1)
// zoneDateTime1: wen.datetime.ZoneDateTime = ZoneDateTime(Date(Day(12),August,Year(2016,AD)),ZoneTime(Time(Hour(7),Minute(5),Second(0),Millisecond(0)),Offset(UTCPlus,Hour(0),Minute(0))))

val zoneDateTime2 = ZoneDateTime(date1, time2)
// zoneDateTime2: wen.datetime.ZoneDateTime = ZoneDateTime(Date(Day(12),August,Year(2016,AD)),ZoneTime(Time(Hour(7),Minute(5),Second(0),Millisecond(0)),Offset(UTCMinus,Hour(1),Minute(0))))

val zoneDateTime3 = ZoneDateTime(date1, time3)
// zoneDateTime3: wen.datetime.ZoneDateTime = ZoneDateTime(Date(Day(12),August,Year(2016,AD)),ZoneTime(Time(Hour(8),Minute(5),Second(0),Millisecond(0)),Offset(UTCMinus,Hour(1),Minute(0))))

import java.time.OffsetDateTime
import java.time.Instant

val zoneDateTime4 = ZoneDateTime(OffsetDateTime.now)
// zoneDateTime4: wen.datetime.ZoneDateTime = ZoneDateTime(Date(Day(2),May,Year(2019,AD)),ZoneTime(Time(Hour(22),Minute(44),Second(34),Millisecond(833)),Offset(UTCPlus,Hour(1),Minute(0))))

val zoneDateTime5 = ZoneDateTime(Instant.now)
// zoneDateTime5: wen.datetime.ZoneDateTime = ZoneDateTime(Date(Day(2),May,Year(2019,AD)),ZoneTime(Time(Hour(21),Minute(45),Second(7),Millisecond(704)),Offset(UTCPlus,Hour(0),Minute(0))))
```

Because instances of cats's `Eq`, `Order` and `Show` are available, we can also do the following:

```scala
import cats.implicits._ // for cats syntax
import wen.implicits._ // for wen's instances

zoneDateTime1.show
// res0: String = 12 August 2016 07:05:00.0 +00:00

scala> zoneDateTime2.show
// res1: String = 12 August 2016 07:05:00.0 -01:00

scala> zoneDateTime3.show
// res2: String = 12 August 2016 08:05:00.0 -01:00

zoneDateTime1 > zoneDateTime2
// res3: Boolean = true

zoneDateTime1 === zoneDateTime3
// res4: Boolean = true
```

### Numeric types

Numeric types use [refined](https://github.com/fthomas/refined) for type safe representation of date/time components as integers.
These types are available under the `wen.types.NumericTypes` namespace.

#### NumericDay

```scala
type NumericDay = Int Refined Interval.Closed[W.`1`.T, W.`31`.T]
```

#### NumericMonth

```scala
type NumericMonth =  Int Refined Interval.Closed[W.`1`.T, W.`12`.T]
```

#### NumericYear

```scala
type NumericYear = Int Refined Positive
```

#### NumericHour

```scala
type NumericHour = Int Refined Interval.Closed[W.`0`.T, W.`23`.T]
```

#### NumericMinute

```scala
type NumericMinute = Int Refined Interval.Closed[W.`0`.T, W.`59`.T]
```

#### NumericSecond

```scala
type NumericSecond = Int Refined Interval.Closed[W.`0`.T, W.`59`.T]
```

#### NumericMillisecond

```scala
type NumericMillisecond = Int Refined Interval.Closed[W.`0`.T, W.`999`.T]
```

## Refinement Helpers

Refinement helpers are made available under the `wen.refine` namespace.

Their purpose is to make it easier to refine integer values into
[NumericTypes](#NumericTypes), without the user having to know about
un-specialised refinement types.

The following refinement helper functions are available:

| functions |
| --------- |
| def refineHour(hour: Int): Either[String, NumericHour] |
| def refineMinute(minute: Int): Either[String, NumericMinute] |
| def refineSecond(second: Int): Either[String, NumericSecond] |
| def refineMilliSecond(millisecond: Int): Either[String, NumericMillisecond] |
| def refineYear(year: Int): Either[String, NumericYear] |
| def refineMonth(month: Int): Either[String, NumericMonth] |
| def refineDay(day: Int): Either[String, NumericDay] |

**Usage**

```scala
def time(hour: Int, minute: Int, second: Int): Either[String, Time] =
  for {
    h <- refineHour(hour)
    m <- refineMinute(minute)
    s <- refineSecond(second)
    ms <- refineMilliSecond(millisecond)
  } yield new Time(Hour(h), Minute(m), Second(s), Millisecond(ms))
```

## ISO-8601 instances

ISO-8601 instances are made available under the `wen.instances.iso` namespace.

| ISO Instances |
| ------------- |
| Show[Date] |
| Show[Time] |
| Show[ZoneTime] |
| Show[DateTime] |
| Show[ZoneDateTime] |

ISO show instances are an alternative to the default ones provided in `wen.implicits`.
Unlike the default instances which return the dates/times in human readable format,
ISO instances return the dates in ISO-8601 format.

See [here for the wikipedia entry on ISO-8601](https://en.m.wikipedia.org/wiki/ISO_8601).

Note that when using the ISO instances you can't import the instances under `wen.implicits`
or `wen.instances` otherwise the compiler won't be able to infer which instances to use for
`Show`.

**Usage**

```scala
import cats.implicits._
import wen.instances.iso._
import wen.datetime._
import wen.types._
import eu.timepit.refined.auto._

val date1 = Date.unsafe(Day(25), July, Year(1975, AD))
// date1: wen.datetime.Date = Date(Day(25),July,Year(1975,AD))

val date2 = Date.unsafe(Day(1), January, Year(2131, BC))
// date2: wen.datetime.Date = Date(Day(1),January,Year(2131,BC))

date1.show
// res0: String = 1975-07-25

scala> date2.show
// res1: String = -2130-01-01

val date3 = Date.unsafe(Day(2), March, Year(1, BC))
// date3: wen.datetime.Date = Date(Day(2),March,Year(1,BC))

val time = Time(Hour(8), Minute(53), Second(23), Millisecond(900))
// time: wen.datetime.Time = Time(Hour(8),Minute(53),Second(23),Millisecond(900))

val dateTime1 = DateTime(date1, time)
// dateTime1: wen.datetime.DateTime = DateTime(Date(Day(25),July,Year(1975,AD)),Time(Hour(8),Minute(53),Second(23),Millisecond(900)))

val dateTime2 = DateTime(date2, time)
// dateTime2: wen.datetime.DateTime = DateTime(Date(Day(1),January,Year(2131,BC)),Time(Hour(8),Minute(53),Second(23),Millisecond(900)))

val dateTime3 = DateTime(date3, time)
// dateTime3: wen.datetime.DateTime = DateTime(Date(Day(2),March,Year(1,BC)),Time(Hour(8),Minute(53),Second(23),Millisecond(900)))

dateTime1.show
// res2: String = 1975-07-25T08:53:23

dateTime2.show
// res3: String = -2130-01-01T08:53:23

dateTime3.show
// res4: String = 0000-03-02T08:53:23

val zoneTime1 = ZoneTime(Time(Hour(8), Minute(15), Second(2), Millisecond(33)),
                         Offset.UTC)
// zoneTime1: wen.datetime.ZoneTime = ZoneTime(Time(Hour(8),Minute(15),Second(2),Millisecond(33)),Offset(UTCPlus,Hour(0),Minute(0)))

val zoneTime2 = ZoneTime(Time(Hour(10), Minute(31), Second(23), Millisecond(606)),
                         Offset(Offset.UTCMinus, Hour(1), Minute(0)))
// zoneTime2: wen.datetime.ZoneTime = ZoneTime(Time(Hour(10),Minute(31),Second(23),Millisecond(606)),Offset(UTCMinus,Hour(1),Minute(0)))

val zoneTime3 = ZoneTime(Time(Hour(7), Minute(53), Second(23), Millisecond(900)),
                         Offset(Offset.UTCPlus, Hour(0), Minute(30)))
// zoneTime3: wen.datetime.ZoneTime = ZoneTime(Time(Hour(7),Minute(53),Second(23),Millisecond(900)),Offset(UTCPlus,Hour(0),Minute(30)))

zoneTime1.show
// res5: String = 08:15:02Z

zoneTime2.show
// res6: String = 10:31:23-01:00

zoneTime3.show
// res7: String = 07:53:23+00:30

val zoneTime1 = ZoneTime(time, Offset.UTC)
// zoneTime1: wen.datetime.ZoneTime = ZoneTime(Time(Hour(8),Minute(53),Second(23),Millisecond(900)),Offset(UTCPlus,Hour(0),Minute(0)))

val zoneTime2 = ZoneTime(time, Offset(Offset.UTCMinus, Hour(1), Minute(0)))
// zoneTime2: wen.datetime.ZoneTime = ZoneTime(Time(Hour(8),Minute(53),Second(23),Millisecond(900)),Offset(UTCMinus,Hour(1),Minute(0)))

val zoneTime3 = ZoneTime(time, Offset(Offset.UTCPlus, Hour(2), Minute(45)))
// zoneTime3: wen.datetime.ZoneTime = ZoneTime(Time(Hour(8),Minute(53),Second(23),Millisecond(900)),Offset(UTCPlus,Hour(2),Minute(45)))

val zoneDateTime1 = ZoneDateTime(date1, zoneTime1)
// zoneDateTime1: wen.datetime.ZoneDateTime = ZoneDateTime(Date(Day(25),July,Year(1975,AD)),ZoneTime(Time(Hour(8),Minute(53),Second(23),Millisecond(900)),Offset(UTCPlus,Hour(0),Minute(0))))

val zoneDateTime2 = ZoneDateTime(date2, zoneTime2)
// zoneDateTime2: wen.datetime.ZoneDateTime = ZoneDateTime(Date(Day(1),January,Year(2131,BC)),ZoneTime(Time(Hour(8),Minute(53),Second(23),Millisecond(900)),Offset(UTCMinus,Hour(1),Minute(0))))

val zoneDateTime3 = ZoneDateTime(date3, zoneTime3)
// zoneDateTime3: wen.datetime.ZoneDateTime = ZoneDateTime(Date(Day(2),March,Year(1,BC)),ZoneTime(Time(Hour(8),Minute(53),Second(23),Millisecond(900)),Offset(UTCPlus,Hour(2),Minute(45))))

zoneDateTime1.show
// res9: String = 1975-07-25T08:53:23Z

zoneDateTime2.show
// res10: String = -2130-01-01T08:53:23-01:00

zoneDateTime3.show
// res11: String = 0000-03-02T08:53:23+02:45
```

## Circe Instances

Circe ISO-8601 encoders and decoders are provided for Date, Time, DateTime, ZoneTime, Offset, and ZoneDateTime.
Circe encoders and decoders are also provided for Day, WeekDay, Month, Year, Epoch, Hour, Minute, Second, and Millisecond.
