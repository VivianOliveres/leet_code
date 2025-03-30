import scala.collection.mutable

object LongestSubstringWithoutRepeatingCharacters {

  def main(args: Array[String]): Unit = {
    val inOut: Seq[(String, Int)] = Seq(
      ("abcabcbb", 3),
      ("bbbbb", 1),
      ("pwwkew", 3)
    )

    inOut.foreach { case (input, expected) =>
      val result = lengthOfLongestSubstring(input)
      if (result == expected)
        println(s"Success for input[$input], result[$result] => [$expected]")
      else
        System.err.println(
          s"FAIL for input[$input], result[$result] => [$expected]"
        )
    }
  }

  // Personal solution
  def lengthOfLongestSubstring(s: String): Int = {
    val chars = s.toCharArray
    var i = 0
    var maxLongestSubstring = 0
    while (i < chars.length) {
      val uniques = mutable.Set(chars(i))
      var j = i + 1
      while (j < chars.length && !uniques.contains(chars(j))){
        uniques.add(chars(j))
        j = j + 1
      }
      val currentLongestSubstring = j - i
      if (currentLongestSubstring > maxLongestSubstring)
        maxLongestSubstring = currentLongestSubstring

      i = i + 1
    }
    maxLongestSubstring
  }

  // Solution with scanLeft
  def lengthOfLongestSubstring2(s: String): Int = {
    s.scanLeft("")((currLongestStr: String, currChar: Char) => {
      // Last index of `currChar` (could be -1 if does not exist)
      val lastCharIndex = currLongestStr.indexOf(currChar)
      val newLongestStr = currLongestStr.substring(1 + lastCharIndex) + currChar
        newLongestStr
        }
      )
      // take max of length would get the answer
      .map(_.length)
      .reduce(Math.max)
  }
}
