object RemoveNthNodeFromEndOfList {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (ListNode(Seq(1, 2, 3, 4, 5)), 2, ListNode(Seq(1, 2, 3, 5))),
      (ListNode(Seq(1)), 1, null),
      (ListNode(Seq(1, 2)), 1, ListNode(Seq(1))),
      (ListNode(Seq(1, 2)), 2, ListNode(Seq(2)))
    )

    inOut.foreach { case (x, target, expected) =>
      val result = removeNthFromEnd(x, target)
      if (ListNode.toStr(result) == ListNode.toStr(expected))
        println(
          s"Success for (${ListNode.toStr(x)}, $target) => ${ListNode.toStr(expected)}"
        )
      else
        System.err.println(
          s"FAIL for (${ListNode.toStr(x)}, $target) : expected [${ListNode
            .toStr(expected)}] but found [${ListNode.toStr(result)}]"
        )
    }
  }

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }
  object ListNode {
    def apply(values: Seq[Int]): ListNode = {
      values match {
        case head :: Nil  => new ListNode(head, null)
        case head :: tail => new ListNode(head, apply(tail))
      }
    }

    def toStr(head: ListNode): Seq[Int] = {
      var values: Seq[Int] = Seq()
      var current = head
      while (current != null) {
        values = values :+ current.x
        current = current.next
      }
      values
    }
  }

  def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
    var toRemove = head
    var endOfList = toRemove
    var i = 0
    while (i < n) {
      endOfList = endOfList.next
      i = i + 1
    }

    if (endOfList == null) {
      return head.next
    }

    while (endOfList.next != null) {
      toRemove = toRemove.next
      endOfList = endOfList.next
    }

    toRemove.next = toRemove.next.next
    head
  }

}
