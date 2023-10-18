object ReverseInteger {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (123, 321),
      (-123, -321),
      (120, 21),
      (1534236469, 0),
    )

    inOut.foreach { case (x, expected) =>
      val result = reverse(x)
      if (result == expected)
        println(s"Success for (${x}) => ${expected}")
      else
        System.err.println(s"FAIL for (${x}) : ${expected} => result found: ${result}")
    }
  }

  def reverse(x: Int): Int = {
    val isNegative = x < 0
    val resultAsStr = math.abs(x).toString.reverse
    resultAsStr.toIntOption.map{value =>
      val mult = if (isNegative) -1 else 1
      mult * value
    }.getOrElse(0)
  }

}
