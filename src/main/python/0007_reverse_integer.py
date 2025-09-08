class Solution:
    def reverse(self, x: int) -> int:
        result = 0
        sign = -1 if x < 0 else 1
        x = abs(x)
        while x != 0:
            pop = x % 10
            x //= 10
            if result > (2**31 - 1) // 10 or (result == (2**31 - 1) // 10 and pop > 7):
                return 0
            result = result * 10 + pop
        return sign * result

if __name__ == "__main__":
    sol = Solution()
    result = sol.reverse(123)
    assert result == 321
    result = sol.reverse(-123)
    assert result == -321
    result = sol.reverse(120)
    assert result == 21
    result = sol.reverse(1534236469)
    assert result == 0, f"Find [{result}]"