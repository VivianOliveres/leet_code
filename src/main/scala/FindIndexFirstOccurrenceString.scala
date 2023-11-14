object FindIndexFirstOccurrenceString {
  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      ("sadbutsad", "sad", 0),
      ("leetcode", "leeto", -1),
      ("aaaaasa", "aaasa", 2),
      ("aaa", "aaaa", -1)
    )

    inOut.foreach { case (haystack, needle, expected) =>
      val result = strStr(haystack, needle)
      if (result == expected)
        println(s"Success for ($haystack, $needle) => $expected")
      else
        System.err.println(
          s"FAIL for ($haystack, $needle => expected[$expected] but result was: $result"
        )
    }
  }

  def strStr(haystack: String, needle: String): Int = {
    var i = 0
    while (i < haystack.length) {
      if (
        haystack(i) == needle.head &&
        i + needle.length <= haystack.length &&
        haystack.substring(i, i + needle.length) == needle
      ) {
        return i
      } else
        i = i + 1
    }
    -1
  }

}
