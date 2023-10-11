
# TODO: perf is not good enough
# >14%
# 114ms

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
