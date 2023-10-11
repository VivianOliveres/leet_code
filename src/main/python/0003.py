
#It keeps in cache the longest substring without repeating characters. This a sliding window algorithm. \
#It is `0(n)` in time and `0(1)` in space (because we can only have letters, digits and special characters so around 256 possibilities).

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

# Cleaner solution from [Redquark](https://redquark.org/leetcode/0003-longest-substring-without-repeating-characters/)
