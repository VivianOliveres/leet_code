object StringToInteger {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      ("42", 42),
      ("   -42", -42),
      ("4193 with words", 4193),
      ("91283472332", 2147483647),
      ("-91283472332", -2147483648),
      ("3.14159", 3),
      ("words and 987", 0),
    )

    inOut.foreach { case (x, expected) =>
      val result = myAtoi(x)
      if (result == expected)
        println(s"Success for (${x}) => ${expected}")
      else
        System.err.println(s"FAIL for (${x}) : ${expected} => result found: ${result}")
    }
  }

  def myAtoi(s: String): Int = {
    val trimed = s.trim
    if (trimed.isEmpty)
      return 0

    val (isNegative, rest) =
      if (trimed(0) == '-') (true, trimed.tail)
      else if (trimed(0) == '+') (false, trimed.tail)
      else (false, trimed)

    var i = 0
    while (i < rest.length && rest(i).isDigit)
      i = i + 1

    if (i == 0)
      0
    else
      rest.substring(0, i)
        .toIntOption
        .map(v => if (isNegative) v * -1 else v)
        .getOrElse(if (isNegative) Int.MinValue else Int.MaxValue)
  }

}
