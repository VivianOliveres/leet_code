import scala.annotation.tailrec

object DivideTwoIntegers {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (10, 3, 3),
      (7, -3, -2),
      (1, 1, 1),
      (-2147483648, -1, 2147483647),
      (-2147483648, 1, -2147483648),
      (-2147483648, 2, -1073741824),
      (-2147483648, -3, 715827882),
      (-1010369383, -2147483648, 0),
    )

    inOut.foreach { case (dividend, divisor, expected) =>
      val result = divide(dividend, divisor)
      if (result == expected)
        println(s"Success for ($dividend, $divisor) => ${expected}")
      else
        System.err.println(
          s"FAIL for ($dividend, $divisor) : expected [$expected] but found [$result]"
        )
    }
  }

  /**
   * TODO: explain why it works
   */
  def divide(dividend: Int, divisor: Int): Int = {
    // Special case to manage manually
    if (dividend == Int.MinValue && divisor == -1)
      Int.MaxValue
    else {
      // +1 if signs are different, -1 if same signs
      val sign = if ((dividend < 0) ^ (divisor < 0)) -1 else 1
      val absDividende = if (dividend == Int.MinValue) Int.MaxValue.toLong + 1 else Math.abs(dividend).toLong
      val absDiviseur = if (divisor == Int.MinValue) Int.MaxValue.toLong + 1 else Math.abs(divisor).toLong
      doDivide(absDividende, absDiviseur, sign)
    }
  }

  @tailrec
  def doDivide(
      absDividend: Long,
      absDivisor: Long,
      sign: Int,
      quotient: Int = 0,
      rest: Long = 0L,
      bitPosition: Int = 31
  ): Int = {
    if (bitPosition < 0)
      sign * quotient
    else {
      val rest2 = (rest << 1) | ((absDividend >>> bitPosition) & 1)
      val (rest3, quotient2) =
        if (rest2 >= absDivisor) {
          (rest2 - absDivisor, quotient | (1 << bitPosition))
        } else (rest2, quotient)
      doDivide(absDividend, absDivisor, sign, quotient2, rest3, bitPosition - 1)
    }
  }

}
