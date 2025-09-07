
#First naive attempt in `0(n log n)` time and `0(1)` space:

 """
 class Solution:
     def twoSum(self, nums: List[int], target: int) -> List[int]:
         for i in range(len(nums)):
             for j in range(i + 1, len(nums)):
             	if nums[i] + nums[j] == target:
             		return [i, j]
         return [-1, -1]
 """

#Second attempt, it indexes values and their indexes. Then for each value, search in dictionary if there is the other value that the sum is equal to target.
#`0(n)` in time and `0(n)` in space. \
#Runs in 60ms.

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


#There is a third solution where the array has to be sorted (ie `0(n log(n))`) then with an index at beginning and another one at end, these elements are compared to the `target`.
# If sum is target then solution is find.
# If sum is below target, then start index is increased.
# If sum is greatest, then end index is decreased.

