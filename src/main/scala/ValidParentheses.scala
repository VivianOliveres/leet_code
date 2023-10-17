import scala.collection.mutable.Stack

object ValidParentheses {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      ("()", true),
      ("()[]{}", true),
      ("(]", false),
      ("", true),
      ("([)]", false),
      ("[", false),
      ("]", false),
    )

    inOut.foreach { case (x, expected) =>
      val result = isValid(x)
      if (result == expected)
        println(s"Success for (${x.toSeq}) => ${expected}")
      else
        System.err.println(s"FAIL for (${x.toSeq}) : ${expected} => result found: ${result}")
    }
  }

  def isValid(s: String): Boolean = {
    val parentheses = Stack[Char]()
    var i = 0
    while (i < s.length) {
      val c = s(i)
      if (c == '(' || c == '{' || c == '[')
        parentheses.push(c)
      else if (parentheses.isEmpty)
        return false
      else if (c == ')' && parentheses.pop() != '(')
        return false
      else if (c == '}' && parentheses.pop() != '{')
        return false
      else if (c == ']' && parentheses.pop() != '[')
        return false

      i = i + 1
    }

    parentheses.isEmpty
  }

}
