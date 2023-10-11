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
`0(n)` in time and `0(n)` in space. \
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


## 3 Longest substring without repeating characters

[Longest substring without repeating characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

It keeps in cache the longest substring without repeating characters. This a sliding window algorithm. \
It is `0(n)` in time and `0(1)` in space (because we can only have letters, digits and special characters so around 256 possibilities).

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        maxSize = 0
        currentSubstring = []
        for i in range(len(s)):
            c = s[i]
            if c in currentSubstring:
                tmp = currentSubstring.pop(0)
                while tmp != c :
                    tmp = currentSubstring.pop(0)
                currentSubstring += c
            else:
                currentSubstring += c
                if len(currentSubstring) > maxSize:
                    maxSize = len(currentSubstring)
        return maxSize
```

Cleaner solution from [Redquark](https://redquark.org/leetcode/0003-longest-substring-without-repeating-characters/)

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if (len(s) == 0):
            return 0
        maxSize = 1
        start = 0
        end = 1
        distincts = set(s[start])
        while end < len(s):
            c = s[end]
            if c in distincts:
                distincts.remove(s[start])
                start += 1
            else:
                distincts.add(c)
                maxSize = max(maxSize, len(distincts))
                end += 1
        return maxSize
```

## 4 Median of two sorted arrays

[Median of two sorted arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/description/)

TODO!

```python

```

## 5 Longest Palindromic Substring

[Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)

It starts from the left index then look for both (left and right) indexes if they formed a new palindrom. If not, it only looks with the next right index by comparing the possible new palindrom and it's reversed string. Finaly, the first loop move faster according to the last index checked. \
The perf is not the best (only 40% better of other python developers) but a better solution (and not intuitive) is [Manacher's_algorithm](https://en.wikipedia.org/wiki/Longest_palindromic_substring#Manacher's_algorithm).


```python
class Solution:
    def longestPalindrome(self, s: str) -> str:
        i = 0
        n = len(s)
        longest = s[0]
        while i < n:
            minIndex = i - 1
            maxIndex = i + 1
            current = s[i]
            while maxIndex < n:
                if minIndex >= 0 and maxIndex < n and s[minIndex] == s[maxIndex]:
                    current = s[minIndex:maxIndex+1]
                    minIndex -= 1
                    maxIndex += 1
                else:
                    sub = current + s[maxIndex]
                    if sub == sub[::-1]:
                        current = sub
                        maxIndex += 1
                    else:
                        maxIndex = n
                
                if len(current) > len(longest):
                    longest = current
            
            i += min(1, maxIndex - i)
        return longest
```

## 6 Zigzag Conversion

[Zigzag Conversion](https://leetcode.com/problems/zigzag-conversion/description/)

TODO: perf is not good enough
>14%
114ms

```python
class Solution:

    def cell(self, i: int, numRows: int) -> (int, int):
        if numRows == 1:
            return (0, i)
        else:
            part = i // max(1, 2 * numRows - 2)
            remainder = i % max(1, 2 * numRows - 2)
            col = -1
            row = -1
            if remainder < numRows:
                col = part * (numRows-1)
                row = remainder % numRows
            else:
                col = part * (numRows-1) + (remainder - numRows + 1)
                row = numRows - (remainder - numRows + 2)
            return (row, col)

    def convert(self, s: str, numRows: int) -> str:
        dict = {}
        for i in range(len(s)):
            key = self.cell(i, numRows)
            dict[key] = i
        
        
        result = ""
        keys = list(dict.keys())
        keys.sort()
        for key in keys:
            result += s[dict[key]]
        return result

```
