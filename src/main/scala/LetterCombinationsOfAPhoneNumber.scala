object LetterCombinationsOfAPhoneNumber {
  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      ("23", List("ad","ae","af","bd","be","bf","cd","ce","cf")),
      ("", List()),
      ("2", List("a","b","c")),
    )

    inOut.foreach { case (x, expected) =>
      val result = letterCombinations(x)
      if (result == expected)
        println(s"Success for (${x}) => ${expected}")
      else
        System.err.println(s"FAIL for (${x}) : expected [${expected}] but found [${result}]")
    }
  }

  private val mapping = Map(
    '2' -> List("a", "b", "c"),
    '3' -> List("d", "e", "f"),
    '4' -> List("g", "h", "i"),
    '5' -> List("j", "k", "l"),
    '6' -> List("m", "n", "o"),
    '7' -> List("p", "q", "r", "s"),
    '8' -> List("t", "u", "v"),
    '9' -> List("w", "x", "y", "z"),
  )

  def letterCombinations(digits: String): List[String] = {
    letterCombinations(digits, "", List())
  }

  private def letterCombinations(rest: String, current: String, acc: List[String]): List[String] = {
    if (rest.isEmpty && current.isEmpty)
      acc
    else if (rest.isEmpty)
      acc :+ current
    else {
      val newLetters = mapping(rest.head)
      newLetters.foldLeft(acc) { case (newAcc, letter) =>
        letterCombinations(rest.tail, current + letter, newAcc)
      }
    }
  }
}
