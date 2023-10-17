object LongestCommonPrefix {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (Array("flower","flow","flight"), "fl"),
      (Array("dog","racecar","car"), ""),
      (Array("a"), "a"),
      (Array("ab", "a"), "a"),
      (Array("reflower","flow","flight"), ""),
    )

    inOut.foreach { case (x, expected) =>
      val result = longestCommonPrefix(x)
      if (result == expected)
        println(s"Success for (${x.toSeq}) => ${expected}")
      else
        System.err.println(s"FAIL for (${x.toSeq}) : ${expected} => result found: ${result}")
    }
  }

  def longestCommonPrefix(inputs: Array[String]): String = {
    if (inputs.isEmpty)
      return ""
    else if (inputs.length == 1)
      return inputs.head

    val smallest = inputs.minBy(_.length)
    val others = inputs.filterNot(_ == smallest)

    var bestResult = ""
    var i = 0
    while (i < smallest.length) {
      val newChar = smallest(i)
      val isContainedInAll = others.forall(input => newChar == input(i))
//        println(s"   startIndex[$startIndex] i[$i] tmpSubString[$tmpSubString] isContainedInAll[$isContainedInAll]")
      if (isContainedInAll) {
        bestResult = bestResult + newChar
        i = i + 1
      } else {
        return bestResult
      }
    }
    bestResult
  }

}
