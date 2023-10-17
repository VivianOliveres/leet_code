object MergeTwoSortedLists {

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }


  def mergeTwoLists(list1: ListNode, list2: ListNode): ListNode = {
    if (list1 == null)
      list2
    else if (list2 == null)
      list1
    else if (list1.x <= list2.x)
      new ListNode(list1.x, mergeTwoLists(list1.next, list2))
    else
      new ListNode(list2.x, mergeTwoLists(list1, list2.next))
  }

}
