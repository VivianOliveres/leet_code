import ListNode.toStr

object MergeKSortedLists {

  def main(args: Array[String]): Unit = {
    val inOut: Seq[(Array[ListNode], ListNode)] = Seq(
      (
        Array(
          ListNode(Seq(1, 4, 5)),
          ListNode(Seq(1, 3, 4)),
          ListNode(Seq(2, 6))
        ),
        ListNode(Seq(1, 1, 2, 3, 4, 4, 5, 6))
      ),
      (Array(), null),
      (Array(null), null)
    )

    inOut.foreach { case (lists, expected) =>
      val result = mergeKLists(lists)
      if (toStr(result) == toStr(expected))
        println(
          s"Success for (${lists.map(toStr)}) => ${toStr(expected)}"
        )
      else
        System.err.println(
          s"FAIL for (${lists.map(toStr)}) : expected [${toStr(expected)}] but found [${toStr(result)}]"
        )
    }
  }

  def mergeKLists(lists: Array[ListNode]): ListNode = {
    val sanitizedLists = lists.filterNot(_ == null).toBuffer
    if (sanitizedLists.isEmpty)
      return null

    var head: ListNode = null
    var current: ListNode = null
    var i = 0
    while (sanitizedLists.nonEmpty) {
      i = 0
      var j = -1
      var min = Int.MaxValue
      while (i < sanitizedLists.length) {
        if (sanitizedLists(i).x < min) {
          j = i
          min = sanitizedLists(i).x
        }
        i = i + 1
      }

      val newCurrent = new ListNode(min)
      if (head == null) {
        head = newCurrent
        current = newCurrent
      } else {
        current.next = newCurrent
        current = current.next
      }

      if (sanitizedLists(j).next == null) {
        sanitizedLists.remove(j)
      } else
        sanitizedLists(j) = sanitizedLists(j).next
    }

    head
  }
}
