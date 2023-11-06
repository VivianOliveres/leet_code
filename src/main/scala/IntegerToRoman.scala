import scala.annotation.tailrec

object IntegerToRoman {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (1, "I"),
      (5, "V"),
      (10, "X"),
      (50, "L"),
      (100, "C"),
      (500, "D"),
      (1000, "M"),
      (3, "III"),
      (58, "LVIII"),
      (1994, "MCMXCIV"),
    )

    inOut.foreach { case (x, expected) =>
      val result = intToRoman(x)
      if (result == expected)
        println(s"Success for (${x}) => ${expected}")
      else
        System.err.println(s"FAIL for (${x}) : expected [${expected}] but found [${result}]")
    }
  }

  // Almost hardcoded solution to improve perf
  // Key of map is the digit value, and the position in the list is the value for (<10, 10-100, 100-1000)
  // Some values are not specified because the input number has the constraint: 1 <= num <= 3999
  private val dict = Map(
    0 -> Seq("", "", "", ""),
    1 -> Seq("I", "X", "C", "M"),
    2 -> Seq("II", "XX", "CC", "MM"),
    3 -> Seq("III", "XXX", "CCC", "MMM"),
    4 -> Seq("IV", "XL", "CD", "4?"),
    5 -> Seq("V", "L", "D", "5?"),
    6 -> Seq("VI", "LX", "DC", "6?"),
    7 -> Seq("VII", "LXX", "DCC", "7?"),
    8 -> Seq("VIII", "LXXX", "DCCC", "8?"),
    9 -> Seq("IX", "XC", "CM", "9?"),
  )

  def intToRoman(num: Int): String = {
    intToRoman(num, 0, "")
  }

  @tailrec
  private def intToRoman(num: Int, pos: Int, agg: String): String = {
    if (num <= 0)
      agg
    else {
      val last = num % 10
      val rest = num / 10
      val currentNumber = dict(last)(pos)
      intToRoman(rest, pos + 1, currentNumber + agg)
    }
  }


}
