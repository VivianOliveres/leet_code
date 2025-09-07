
# It starts from the left index then look for both (left and right) indexes if they formed a new palindrom. If not, it only looks with the next right index by comparing the possible new palindrom and it's reversed string. Finaly, the first loop move faster according to the last index checked.
# The perf is not the best (only 40% better of other python developers) but a better solution (and not intuitive) is [Manacher's_algorithm](https://en.wikipedia.org/wiki/Longest_palindromic_substring#Manacher's_algorithm).
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
