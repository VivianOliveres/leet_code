class Solution:
    def isPalindrome(self, x: int) -> bool:
        if (x >= 0):
            return x == int(str(x)[::-1])
        else:
            return False


if __name__ == "__main__":
    sol = Solution()
    result = sol.isPalindrome(121)
    print(result)
    print("****")
    result = sol.isPalindrome(-121)
    print(result)
    print("****")
    result = sol.isPalindrome(10)
    print(result)
    print("****")