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
