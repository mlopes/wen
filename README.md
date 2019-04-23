[![Build Status](https://travis-ci.org/mlopes/wen.svg?branch=master)](https://travis-ci.org/mlopes/wen) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# wen

> 'Wen considered the nature of time and understood that the universe is, instant by instant, recreated anew. Therefore, he understood, there is in truth no past, only a memory of the past. Blink your eyes, and the world you see next did not exist when you closed them. Therefore, he said, the only appropriate state of the mind is surprise. The only appropriate state of the heart is joy. The sky you see now, you have never seen before. The perfect moment is now. Be glad of it.'
>
> ― Terry Pratchett, Thief of Time

Date and time types and instances

## Status

Wen is in early development. There is no release yet.

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
| Day(day: Int): Option[Day] |
| Day(day: [NumericDay](#NumericDay)): Day |

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

val day = Day(31)
// day: Option[wen.types.Day] = Some(Day(31))

val notDay = Day(32)
// notDay: Option[wen.types.Day] = None

// You need to use refineV when refining non-literal values
val refinedDay = Day(refineMV[Interval.Closed[W.`1`.T, W.`31`.T]](22))
// refinedDay: wen.types.Day = Day(22)
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
| Month(month: Int): Option[Month] |
| Month(numericMonth: [NumericMonth](#NumericMonth)): Month |

| Instances |
| --------- |
| Order[Month] |
| Eq[Month] |
| Show[Month] |

**Members**

```scala
Month.toInt: Month => Int
```

**Usage**

```scala
import wen.types._
import eu.timepit.refined.{W, refineMV}
import eu.timepit.refined.numeric.Interval

val month = December
// month: wen.types.December.type = December

val monthFromInt = Month(7)
// monthFromInt: Option[wen.types.Month] = Some(July)

val notMonth = Month(24)
// notMonth: Option[wen.types.Month] = None

// You need to use refineV when refining non-literal values
val refinedMonth = Month(refineMV[Interval.Closed[W.`1`.T, W.`12`.T]](4))
// refinedMonth: wen.types.Month = April
```

Returns the ordinal number of a Month, starting at 1 for January and ending in 12 for December.

#### Year

| Constructors |
| ------------ |
| Year(year: Int, epoch: [Epoch](#Epoch)): Option[Year] |
| Year(year: [NumericYear](#NumericYear), val epoch: [Epoch](#Epoch)): Year |

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

val adYear = Year(2019, AD)
// adYear: Option[wen.types.Year] = Some(Year(2019,AD))

val bcYear = Year(19, BC)
// bcYear: Option[wen.types.Year] = Some(Year(19,BC))

val notYear = Year(-21, AD)
// notYear: Option[wen.types.Year] = None

// You need to use refineV when refining non-literal values
val refinedYear = Year(refineMV[Positive](2019), AD)
// refinedYear: wen.types.Year = Year(2019,AD)
```

#### Epoch

| Constructors |
| ------------ |
| BC |
| AD |

| Instances |
| --------- |
| Order[Epoch] |
| Eq[Epoch] |
| Show[Epoch] |

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

| Instances |
| --------- |
| Order[WeekDay] |
| Eq[WeekDay] |
| Show[WeekDay] |

The provided `Order` instance starts on `Sunday` and ends on `Saturday`.

#### Hour

| Constructors |
| ------------ |
| Hour(hour: Int): Option[Hour] |
| Hour(hour: [NumericHour](#NumericHour)): Hour |

| Instances |
| --------- |
| Order[Hour] |
| Eq[Hour] |
| Show[Hour] |

#### Minute

| Constructors |
| ------------ |
| Minute(minute: Int): Option[Minute] |
| Minute(minute: [NumericMinute](#NumericMinute)): Minute |

| Instances |
| --------- |
| Order[Minute] |
| Eq[Minute] |
| Show[Minute] |

#### Second

| Constructors |
| ------------ |
| Second(second: Int): Option[Second] |
| Second(second: [NumericSecond](#NumericSecond)): Second |

| Instances |
| --------- |
| Order[Second] |
| Eq[Second] |
| Show[Second] |

#### Millisecond

| Constructors |
| ------------ |
| Millisecond(millisecond: Int): Option[Millisecond] |
| Millisecond(millisecond: [NumericMillisecond](#NumericMillisecond)): Millisecond |

| Instances |
| --------- |
| Order[Millisecond] |
| Eq[Millisecond] |
| Show[Millisecond] |

### Full Representations

#### Time

| Constructors |
| ------------ |
| Time(hour: [Hour](#Hour), minute: [Minute](#Minute), second: [Second](#Second), millisecond: [Millisecond](#Millisecond)): Time |
| Time(hour: [Hour](#Hour), minute: [Minute](#Minute), second: [Second](#Second)): Time |
| Time(hour: [Hour](#Hour), minute: [Minute](#Minute)): Time |
| Time(hour: [Hour](#Hour)): Time |

| Instances |
| --------- |
| Order[Time] |
| Eq[Time] |
| Show[Time] |

Values for the properties that are not specified, will default to 0.

#### ZoneTime

| Constructors |
| ------------ |
| ZoneTime(time: [Time](#Time), offset: [Offset](#Offset)): ZoneTime |

| Instances |
| --------- |
| Order[ZoneTime] |
| Eq[ZoneTime] |
| Show[ZoneTime] |

##### Offset

| Constructors |
| ------------ |
| Offset(offsetType: [OffsetType](#OffsetType), hour: [Hour](#Hour), minute: [Minute](#Minute)): Offset |

**Members**

```scala
UTC: Offset = Offset([UTCPlus](#OffsetType), Hour(0), Minute(0))
```

##### OffsetType
 
| Constructors |
| ------------ |
| UTCMinus |
| UTCPlus |


#### Date

| Constructors |
| ------------ |
| Date(day: [Day](#Day), month: [Month](#Month), year: [Year](#Year)): Option[Date] |
| Date.unsafe(day: [Day](#Day), month: [Month](#Month), year: [Year](#Year)): Date |

| Instances |
| --------- |
| Order[Date] |
| Eq[Date] |
| Show[Date] |

The default constructor`Date(day: Day, month: Month, year: Year): Option[Date]` only allows the creation of valid dates, and will return `None` for invalid dates. The unsafe constructor `Date.unsafe(day: Day, month: Month, year: Year): Date` allows for creating invalid date combinations such as _30 February 2019_.

#### DateTime

| Constructors |
| ------------ |
| DateTime(date: [Date](#Date0, time: [Time](#Time)): DateTime |

| Instances |
| --------- |
| Order[DateTime] |
| Eq[DateTime] |
| Show[DateTime] |

#### ZoneDateTime

| Constructors |
| ------------ |
| ZoneDateTime(date: [Date](#Date), zoneTime: [ZoneTime](#ZoneTime)): ZoneDateTime |

| Instances |
| --------- |
| Order[ZoneDateTime] |
| Eq[ZoneDateTime] |
| Show[ZoneDateTime] |

### Numeric types

Numeric types use [refined](https://github.com/fthomas/refined) for type safe representation of date/time components as integers.

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
