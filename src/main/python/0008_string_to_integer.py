class Solution:
    def myAtoi(self, s: str) -> int:
        # Skip leading trail
        s = s.lstrip()

        # Return 0 for empty string
        if not s:
            return 0

        i = 0
        # Manage sign as it should be first char
        sign = 1
        if s[i] in ('+', '-'):
            sign = 1 if s[i] == '+' else -1
            i += 1

        # For each digit, add it to the result
        result = 0
        while i < len(s) and s[i].isdigit():
            result = result * 10 + int(s[i])
            i += 1

        # Prepare result and round it if needed
        result *= sign
        INT_MAX, INT_MIN = 2 ** 31 - 1, -2 ** 31
        if result > INT_MAX:
            return INT_MAX
        if result < INT_MIN:
            return INT_MIN
        return result

if __name__ == "__main__":
    sol = Solution()
    result = sol.myAtoi("42")
    assert result == 42
    result = sol.myAtoi(" -042")
    assert result == -42
    result = sol.myAtoi("1337c0d3")
    assert result == 1337
    result = sol.myAtoi("0-1")
    assert result == 0, f"[0-1] expected [0] but found [{result}]"
    result = sol.myAtoi("words and 987")
    assert result == 0
    result = sol.myAtoi("   +0 123")
    assert result == 0, f"[   +0 123] expected [0] but found [{result}]"
    result = sol.myAtoi("  +  413")
    assert result == 0, f"[  +  413] expected [0] but found [{result}]"