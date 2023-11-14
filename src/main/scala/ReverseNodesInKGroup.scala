import ListNode.toStr

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

/** Time perf can be improved as it is in the avg.
  * Current improvements are:
  *  - BufferList to reuse data structures
  *  - hasEnoughElements method to avoid object instantiations
  *  - tailrec
  */
object ReverseNodesInKGroup {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (ListNode(Seq(1, 2, 3, 4, 5)), 2, ListNode(Seq(2, 1, 4, 3, 5))),
      (ListNode(Seq(1, 2, 3, 4, 5)), 3, ListNode(Seq(3, 2, 1, 4, 5))),
      (ListNode(Seq(1, 2)), 2, ListNode(Seq(2, 1)))
    )

    inOut.foreach { case (x, target, expected) =>
      val result = reverseKGroup(x, target)
      if (toStr(result) == toStr(expected))
        println(
          s"Success for (${toStr(x)}, $target) => ${toStr(expected)}"
        )
      else
        System.err.println(
          s"FAIL for (${toStr(x)}, $target) : expected [${toStr(expected)}] but found [${toStr(result)}]"
        )
    }
  }

  def reverseKGroup(head: ListNode, k: Int): ListNode = {
    if (!hasEnoughElements(head, k))
      return head

    val results: ListBuffer[ListNode] =
      ListBuffer.empty ++ (0 until k).map(new ListNode(_, null))
    selectNodes(head, k, results)

    val (newHead, end) = reverse(results)
    doReverse(k, end, results)
    newHead
  }

  private def selectNodes(
      current: ListNode,
      k: Int,
      results: ListBuffer[ListNode]
  ): Boolean = {
    var i = 0
    var tmp = current
    while (i < k && tmp != null) {
      results(i) = tmp
      tmp = tmp.next
      i = i + 1
    }
    i == k
  }

  private def reverse(seq: ListBuffer[ListNode]): (ListNode, ListNode) = {
    var i = 0
    var tmp = seq.last.next
    while (i < seq.size) {
      seq(i).next = tmp
      tmp = seq(i)
      i = i + 1
    }
    (seq.last, seq.head)
  }

  @tailrec
  private def doReverse(
      k: Int,
      previous: ListNode,
      results: ListBuffer[ListNode]
  ): Unit = {
    if (!hasEnoughElements(previous.next, k))
      return

    selectNodes(previous.next, k, results)
    val (head, end) = reverse(results)
    previous.next = head
    doReverse(k, end, results)
  }

  @tailrec
  private def hasEnoughElements(head: ListNode, n: Int): Boolean =
    n == 0 || head != null && hasEnoughElements(head.next, n - 1)

}
