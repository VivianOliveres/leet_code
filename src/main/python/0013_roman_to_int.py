class Solution:
    values = {
        "I": 1,
        "V": 5,
        "X": 10,
        "L": 50,
        "C": 100,
        "D": 500,
        "M": 1000,
    }
    specials = {
        "IV": 4,
        "IX": 9,
        "XL": 40,
        "XC": 90,
        "CD": 400,
        "CM": 900,
    }
    def romanToInt(self, s: str) -> int:
        sum = 0
        i = 0
        while i < len(s):
            if i + 1 < len(s):
                key = s[i] + s[i+1]
                if key in self.specials:
                    sum += self.specials[key]
                    i += 2
                    continue

            sum += self.values[s[i]]
            i += 1
        return sum



if __name__ == "__main__":
    sol = Solution()
    result = sol.romanToInt("III")
    assert result == 3
    result = sol.romanToInt("LVIII")
    assert result == 58
    result = sol.romanToInt("MCMXCIV")
    assert result == 1994
    print("done")