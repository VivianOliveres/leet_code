object PalindromeNumber {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (121, true),
      (-121, false),
      (10, false),
    )

    inOut.foreach { case (x, expected) =>
      val result = isPalindrome(x)
      if (result == expected)
        println(s"Success for (${x}) => ${expected}")
      else
        System.err.println(s"FAIL for (${x}) : ${expected} => result found: ${result}")
    }
  }

  def isPalindrome(x: Int): Boolean = {
    val str = x.toString
    var i = 0
    while (i < str.length) {
      if (str(i) != str.charAt(str.length - 1 - i))
        return false
      i = i + 1
    }
    true
  }
}
