import scala.annotation.tailrec

object RegularExpressionMatching {
  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      ("aa", "a", false),
      ("aa", "a*", true),
      ("ab", ".*", true),
      ("aab", "c*a*b", true),
      ("mississippi", "mis*is*p*.", false),
      ("ab", ".*c", false),
      ("aaa", "a*a", true),
      ("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*", true)
    )

    inOut.foreach { case (s, p, expected) =>
      val result = isMatch(s, p)
      if (result == expected)
        println(s"Success for ($s, $p) => ${expected}")
      else
        System.err.println(
          s"FAIL for ($s, $p) : expected [${expected}] but found [${result}]"
        )
    }
  }

  def isFullOfStar(p: String): Boolean =
    p.nonEmpty && p.length % 2 == 0 && p.count(_ == '*') == p.length / 2

  def isMatch(s: String, p: String): Boolean = {
    if (s.isEmpty && p.isEmpty)
      return true
    else if (s.isEmpty && isFullOfStar(p))
      return true
    else if (s.isEmpty && p.nonEmpty || s.nonEmpty && p.isEmpty)
      return false

    val isStar = p.length > 1 && p(1) == '*'
    (s.head, p.head, isStar) match {
      case (_, '.', true) =>
        isMatch(s.tail, p) || isMatch(s, p.substring(2))
      case (_, '.', false) =>
        isMatch(s.tail, p.tail)
      case (sc, pc, true) if sc == pc =>
        isMatch(s.tail, p) || isMatch(s, p.substring(2))
      case (sc, pc, false) if sc == pc =>
        isMatch(s.tail, p.tail)
      case (_, _, true) =>
        isMatch(s, p.substring(2))
      case (_, _, false) =>
        false
    }
  }

}
