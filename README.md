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
| December |(month: Int): Option[Month] |
| Month(month: Int): Option[Month] |
| Month(numericMonth: [NumericMonth](#NumericMonth)): Month |

| Instances |
| --------- |
| Order[Month] |
| Eq[Month] |
| Show[Month] |

`Month.toInt: Month => Int`

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

### Full Representations

### Numeric types

Numeric types use [refined](https://github.com/fthomas/refined) for type safe representation of date/time components as integers.

#### NumericMonth

```scala
type NumericMonth =  Int Refined Interval.Closed[W.`1`.T, W.`12`.T]
```

#### NumericYear

```scala
type NumericYear = Int Refined Positive
```

#### NumericDay

```scala
type NumericDay = Int Refined Interval.Closed[W.`1`.T, W.`31`.T]
```
