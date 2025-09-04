from typing import List


class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        if not strs:
            return ""
        # zip: concatenate each char
        # ["flower", "flow", "flight"] becomes
        #     [ ('f', 'f', 'f'),
        #       ('l', 'l', 'l'),
        #       ('o', 'o', 'i'),
        #       ('w', 'w', 'g'),
        #     ]
        # Note: `*` is an unpackaging operator that extract a list into multiple elements
        for i, chars in enumerate(zip(*strs)):
            # set: used to remove duplicate
            # if len(set) == 1 then they are all the same char
            if len(set(chars)) > 1:
                # Return substring from 0 to i-1
                return strs[0][:i]
        return min(strs, key=len)


if __name__ == "__main__":
    sol = Solution()
    result = sol.longestCommonPrefix(["flower","flow","flight"])
    assert result == "fl", f"found [{result}]"
    result = sol.longestCommonPrefix(["dog","racecar","car"])
    assert result == "", f"found [{result}]"
    print("done")