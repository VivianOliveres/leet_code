import scala.annotation.tailrec

object AddTwoNumbers {


  def main(args: Array[String]): Unit = {
    val inOut: Seq[(ListNode, ListNode, ListNode)] = Seq(
      (ListNode(2,4,3), ListNode(5,6,4), ListNode(7,0,8)),
      (ListNode(0), ListNode(0), ListNode(0)),
      (ListNode(9,9,9,9,9,9,9), ListNode(9,9,9,9), ListNode(8,9,9,9,0,0,0,1))
    )

    inOut.foreach { case (ln1, ln2, expected) =>
      val result = addTwoNumbers(ln1, ln2)
      if (ListNode.print(result) == ListNode.print(expected))
        println(s"Success for (${ListNode.print(ln1)} + ${ListNode.print(ln2)}, ${ListNode.print(result)}) => ${ListNode.print(expected)}")
      else
        System.err.println(
          s"FAIL for (${ListNode.print(ln1)} + ${ListNode.print(ln2)}, ${ListNode.print(result)}) => ${ListNode.print(expected)}"
        )
    }
  }

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }
  object ListNode {
    def apply(numbers: Int*): ListNode = {
      numbers.foldRight[ListNode](null){(number, previous) =>
        new ListNode(number, previous)
      }
    }
    def print(ln: ListNode): String = ln.next match {
      case null => s"${ln.x}->null"
      case next => s"${ln.x}->${print(next)}"
    }
  }

  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    val (head, carry) = add(l1.x, l2.x, 0)
    doAddTwoNumbers(l1.next, l2.next, head, carry, head)
  }

  private def add(i1: Int, i2: Int, carry: Int): (ListNode, Int) = {
    val sum = i1 + i2 + carry
    val digitToAdd = sum % 10
    val nextToAdd = sum / 10
    val result = new ListNode(digitToAdd)
    (result, nextToAdd)
  }

  @tailrec
  private def doAddTwoNumbers(l1: ListNode, l2: ListNode, headResult: ListNode, carry: Int, result: ListNode): ListNode = {
    if (l1 == null && l2 == null && carry > 0) {
      headResult.next = new ListNode(carry)
      result
    } else if (l1 == null && l2 == null)
      result
    else if (l1 == null) {
      doAddTwoNumbers(l2, headResult, carry, result)
      result
    }else if (l2 == null) {
      doAddTwoNumbers(l1, headResult, carry, result)
      result
    } else {
      val (nextHead, nextCarry) = add(l1.x, l2.x, carry)
      headResult.next = nextHead
      doAddTwoNumbers(l1.next, l2.next, headResult.next, nextCarry, result)
    }
  }

  private def doAddTwoNumbers(ln: ListNode, headResult: ListNode, carry: Int, result: ListNode): ListNode = {
    if (carry > 0) {
      val sum = ln.x + carry
      val digitToAdd = sum % 10
      val nextToAdd = sum / 10
      headResult.next = new ListNode(digitToAdd)
      doAddTwoNumbers(null, ln.next, headResult.next, nextToAdd, result)
    } else {
      headResult.next = ln
    }
    headResult
  }

}
