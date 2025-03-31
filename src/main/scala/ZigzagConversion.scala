object ZigzagConversion {

  def main(args: Array[String]): Unit = {
    val inOut: Seq[(String, Int, String)] = Seq(
      ("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR"),
      ("PAYPALISHIRING", 4, "PINALSIGYAHRPI"),
      ("A", 1, "A"),
      ("A", 2, "A"),
      ("AB", 1, "AB"),
    )

    inOut.foreach { case (input, numRows, expected) =>
      val result = convert(input, numRows)
      if (result == expected)
        println(s"Success for input[$input], numRows[$numRows], result[$result] => [$expected]")
      else
        System.err.println(
          s"FAIL for input[$input], numRows[$numRows], result[$result] => [$expected]"
        )
    }
  }

  /**
   * Better than 92%.
   * Using a `val result = new Array[Char](s.length)` to store result makes solution better than 97,5%.
   * Algo explanations: for each row, get all char according to the step and row number.
   * Step is a way to jump between indexes of String input.
   */
  def convert(s: String, numRows: Int): String = {
    // Special cases
    if (s.isEmpty || s.length == 1 || numRows == 1)
      return s

    // Each step is: add numbers into a column and add into diagonal
    val maxElementsByStep = 2 * numRows - 2
    val maxSteps = s.length / numRows

    // results are stored into an array for efficiency
    var resultIndex = 0
    val result = new Array[Char](s.length)

//    println(s"maxElementsByStep[$maxElementsByStep] maxSteps[$maxSteps]")
    var currentRow = 0
    while (currentRow < numRows) {
      var step = 0
      while(step <= maxSteps) {
//        println(s"Step [$step] ...")
        val index = step * maxElementsByStep + currentRow
        if (index < s.length) {
          val c1 = s(index)
          result(resultIndex) = c1
          resultIndex += 1
//          println(s"currentRow[$currentRow] index[$index] c1[$c1]")

          val index2 = (step + 1) * maxElementsByStep - currentRow
          if (currentRow != 0 && index2 > index && index2 < s.length) { // Skip first and last rows. Also skip when index 2 is too far away
            val c2 = s(index2)
            result(resultIndex) = c2
            resultIndex += 1
//            println(s"currentRow[$currentRow] index[$index2] c2[$c2]")
          }
        }
        step = step + 1
      }
      currentRow = currentRow + 1
      step = 0
    }
    new String(result)
  }
}
