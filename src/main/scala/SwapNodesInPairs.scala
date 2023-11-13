import scala.annotation.tailrec

object SwapNodesInPairs {
  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (ListNode(Seq(1, 2, 3, 4)), ListNode(Seq(2, 1, 4, 3))),
      (null, null),
      (ListNode(Seq(1)), ListNode(Seq(1)))
    )

    inOut.foreach { case (input, expected) =>
      val result = swapPairs(input)
      if (ListNode.toStr(result) == ListNode.toStr(expected))
        println(
          s"Success for (${ListNode.toStr(input)}) => ${ListNode.toStr(expected)}"
        )
      else
        System.err.println(
          s"FAIL for (${ListNode.toStr(input)}) : expected [${ListNode
            .toStr(expected)}] but found [${ListNode.toStr(result)}]"
        )
    }
  }

  def swapPairs(head: ListNode): ListNode = {
    if (head == null)
      null
    else if (head.next == null)
      head
    else {
      val newHead = doSwap(head)
      doSwap(newHead, newHead.next)
    }
  }

  private def doSwap(h1: ListNode): ListNode = {
    val h2 = h1.next
    h1.next = h1.next.next
    h2.next = h1
    h2
  }

  @tailrec
  private def doSwap(head: ListNode, h0: ListNode): ListNode = {
    if (h0 == null || h0.next == null || h0.next.next == null)
      head
    else {
      val next = doSwap(h0.next)
      h0.next = next
      doSwap(head, next.next)
    }
  }
}
