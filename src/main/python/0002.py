
# `0(n)` in time and `0(n)` in space (as the result is a copy).
# It is important to note that, as soon as one of the `ListNode` has reached its end, then the algo returns the other ont without iterating over it (except if the previous value is greater than 10).
# Results are achieved in 70 ms.

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def sumNodes(self, l1: Optional[ListNode], l2: Optional[ListNode], rest: int) -> (int, int):
        nodes = list(filter(lambda x: x != None, [l1, l2]))
        values = list(map(lambda x: x.val, nodes))
        tmpValue = sum(values) + rest
        value = tmpValue % 10
        new_rest = 1 if tmpValue >= 10 else 0
        return value, new_rest

    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        (value, rest) = self.sumNodes(l1, l2, 0)
        result = ListNode(value)
        tmp = result
        tmp1 = l1.next
        tmp2 = l2.next
        while True:
            print("Iteration: result is " + str(result))
            if tmp1 == None and tmp2 == None and rest == 1:
                tmp.next = ListNode(1)
                return result
            elif tmp1 == None and tmp2 == None:
                return result
            elif (tmp1 == None and rest == 1):
                (value, rest) = self.sumNodes(tmp1, tmp2, rest)
                tmp.next = ListNode(value)
                tmp = tmp.next
                tmp2 = tmp2.next
            elif tmp1 == None:
                tmp.next = tmp2
                return result
            elif tmp2 == None and rest == 1:
                (value, rest) = self.sumNodes(tmp1, tmp2, rest)
                tmp.next = ListNode(value)
                tmp = tmp.next
                tmp1 = tmp1.next
            elif tmp2 == None:
                tmp.next = tmp1
                return result
            else:
                (value, rest) = self.sumNodes(tmp1, tmp2, rest)
                tmp.next = ListNode(value)
                tmp = tmp.next
                tmp1 = tmp1.next
                tmp2 = tmp2.next
