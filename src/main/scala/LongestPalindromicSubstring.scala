import scala.annotation.tailrec

object LongestPalindromicSubstring {

  def main(args: Array[String]): Unit = {
    val inOut: Seq[(String, String)] = Seq(
      ("babad", "bab"),
      ("cbbd", "bb"),
      ("ccc", "ccc"),
      ("a", "a"),
      ("ac", "a"),
      ("aacabdkacaa", "aca")
    )

    inOut.foreach { case (input, expected) =>
      val result = longestPalindrome(input)
      if (result == expected)
        println(s"Success for input[$input], result[$result] => [$expected]")
      else
        System.err.println(
          s"FAIL for input[$input], result[$result] => [$expected]"
        )
    }
  }

  // Better than 41%
  def longestPalindrome(s: String): String = {
    if (s.length == 1)
      s
    else {
      val (start, end) = (0 until s.length)
        .flatMap { i =>
          if (i + 1 < s.length && s(i) == s(i + 1)) // Compute even and odd solutions
            Seq(computePalindrome(i, i, s), computePalindrome(i, i + 1, s))
          else // Compute odd only
            Seq(computePalindrome(i, i, s))
        }
        .maxBy { case (start, end) => end - start + 1 }
      s.substring(start, end + 1)
    }
  }

  @tailrec
  private def computePalindrome(i: Int, j: Int, s: String): (Int, Int) =
    if (i > 0 && j < s.length - 1 && s(i - 1) == s(j + 1))
      computePalindrome(i - 1, j + 1, s)
    else
      (i, j)

  // Better than 23%
  // Keep indexes instead of String palindrome to save memory
  // Compute palindrome from position i and i+1
  def longestPalindromeBad(s: String): String = {
    var longestPalindrome = s"${s.head}"
    var i = 0
    while (i < s.length) {
      longestPalindrome = computePalindromeBad(s, i, 0, longestPalindrome)
      if (i + 1 < s.length && s(i) == s(i + 1))
        longestPalindrome = computePalindromeBad(s, i, 1, longestPalindrome)
      i = i + 1
    }

    longestPalindrome
  }

  private def computePalindromeBad(
      reference: String,
      i: Int,
      carry: Int,
      longestPalindrome: String
  ): String = {
    var j = 1 - carry
    var result = longestPalindrome
    while (
      0 <= i - j && i + j + carry < reference.length && reference(
        i - j
      ) == reference(i + j + carry)
    ) {
      val subStr = reference.substring(i - j, i + j + 1 + carry)
      if (subStr.length > result.length)
        result = subStr
      j = j + 1
    }
    result
  }

  // Bad solution => Better than 6.85%
  // Compute all sliding substring and check if they are a palindrome (ie `str == str.reverse`
  def longestPalindromeVeryBad(s: String): String = {
    var i = s.length
    while (i > 0) {
      var j = 0
      while (i + j <= s.length) {
        val subStr = s.substring(j, i + j)
        if (subStr == subStr.reverse)
          return subStr
        j = j + 1
      }
      i = i - 1
    }
    ""
  }

  // Elegant but inefficient solution (with `sliding`) but get an `Memory Limit Exceeded`
  // Compute all sliding substring and check if they are a palindrome (ie `str == str.reverse`
  def longestPalindromeMemoryLimitExceeded(s: String): String = {
    (s.length to 1 by -1)
      .flatMap(size => s.sliding(size))
      .find(str => str == str.reverse)
      .get
  }
}
