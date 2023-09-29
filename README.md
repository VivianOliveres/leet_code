# leet_code

This my personal solutions (and comments) of exercices from [Leet Code](https://leetcode.com).

## 1 Two sum

[Two sum](https://leetcode.com/problems/two-sum/)

First naive attempt in `0(n log n)` time and `0(1)` space:

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        for i in range(len(nums)):
            for j in range(i + 1, len(nums)):
            	if nums[i] + nums[j] == target:
            		return [i, j]
        return [-1, -1]
```


Second attempt, it indexes values and their indexes. Then for each value, search in dictionary if there is the other value that the sum is equal to target.\
`0(n)` in time and `0(n)` in space \
Runs in 60ms.

```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        indexes = {}
        for i in range(len(nums)):
            current_value = nums[i]
            if (current_value in indexes):
                indexes[current_value] = indexes[current_value] + [i]
            else:
                indexes[current_value] = [i]
        for (key, values) in indexes.items():
            if (key * 2 == target and len(values) >= 2):
                return values[:2]
            elif key * 2 != target:
                possible_value = target - key
                if possible_value in indexes.keys():
                    return [values[0], indexes[possible_value][0]]
        return [-1, -1]
```

There is a third solution where the array has to be sorted (ie `0(n log(n))`) then with an index at begining and another one at end, these elements are compared to the `target`.\
If sum is target then solution is find. \
If sum is below target, then start index is increased. \
If sum is greatest, then end index is decreased.

## 2 Add two numbers

[Add two numbers](https://leetcode.com/problems/add-two-numbers/)

`0(n)` in time and `0(n)` in space (as the result is a copy).\
It is important to note that, as soon as one of the `ListNode` has reached its end, then the algo returns the other ont without iterating over it (except if the previous value is greater than 10). \
Results are achieved in 70 ms.

```python
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
```
