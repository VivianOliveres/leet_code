import scala.annotation.tailrec

object GenerateParentheses {
  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (3, List("((()))", "(()())", "(())()", "()(())", "()()()")),
      (1, List("()"))
    )

    inOut.foreach { case (x, expected) =>
      val result = generateParenthesis(x)
      if (result == expected)
        println(s"Success for (${}) => ${expected}")
      else
        System.err.println(
          s"FAIL for (${x}) : expected [${expected}] but found [${result}]"
        )
    }
  }

  def generateParenthesis(n: Int): List[String] = {
    doGenerateParenthesis(n, 0, "")
  }

  private def doGenerateParenthesis(
      n: Int,
      toClose: Int,
      acc: String
  ): List[String] = {
    if (n == 0 && toClose == 0)
      List(acc)
    else if (n == 0)
      doGenerateParenthesis(n, toClose - 1, acc + ")")
    else if (toClose > 0)
      doGenerateParenthesis(
        n - 1,
        toClose + 1,
        acc + "("
      ) ++ doGenerateParenthesis(n, toClose - 1, acc + ")")
    else
      doGenerateParenthesis(n - 1, toClose + 1, acc + "(")
  }
}
