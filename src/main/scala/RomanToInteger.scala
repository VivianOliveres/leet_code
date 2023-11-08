import scala.annotation.tailrec

object RomanToInteger {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      ("III", 3),
      ("LVIII", 58),
      ("MCMXCIV", 1994)
    )

    inOut.foreach { case (x, expected) =>
      val result = romanToInt(x)
      if (result == expected)
        println(s"Success for (${x}) => ${expected}")
      else
        System.err.println(
          s"FAIL for (${x}) : ${expected} => result found: ${result}"
        )
    }
  }

  private val numbers = Map(
    'I' -> 1,
    'V' -> 5,
    'X' -> 10,
    'L' -> 50,
    'C' -> 100,
    'D' -> 500,
    'M' -> 1000
  )

  /** Naive solution that looks for the two next solution if they are in cache. If yes, increase the accumulator to the given value, else increase of the first letter value.
    */
  //  private val cache = Map(
  //    "IV" -> 4,
  //    "IX" -> 9,
  //    "XL" -> 40,
  //    "XC" -> 90,
  //    "CD" -> 400,
  //    "CM" -> 900)
  //  def romanToInt(s: String): Int = {
  //    var count = 0
  //    var i = 0
  //    while (i < s.length) {
  //      if (i + 1 < s.length && cache.contains(s.substring(i, i + 2))) {
  //        count = count + cache(s.substring(i, i + 2))
  //        i = i + 2
  //      } else {
  //        count = count + numbers(s.charAt(i))
  //        i = i +1
  //      }
  //    }
  //    count
  //  }

  /** Better solution that is recursive.
    * It keeps in memory the last letter and check if it is a subtraction or an addition.
    */

  def romanToInt(s: String): Int = {
    romanToInt(0, numbers(s.head), s.tail)
  }

  @tailrec
  def romanToInt(acc: Int, lastValue: Int, rest: String): Int = {
    if (rest.isEmpty)
      acc + lastValue
    else if (lastValue >= numbers(rest.head)) {
      //      println(s"acc[$acc] lastValue[$lastValue] restHead[${rest.head}][${numbers(rest.head)}] ${rest}")
      //      println(s"Add     ${lastValue}")
      romanToInt(acc + lastValue, numbers(rest.head), rest.tail)
    } else {
      //      println(s"acc[$acc] lastValue[$lastValue] restHead[${rest.head}][${numbers(rest.head)}] ${rest}")
      //      println(s"Add sub ${numbers(rest.head) - lastValue}")
      if (rest.tail.isEmpty)
        acc + numbers(rest.head) - lastValue
      else
        romanToInt(
          acc + numbers(rest.head) - lastValue,
          numbers(rest.tail.head),
          rest.tail.tail
        )
    }
  }

}
