object ThreeSumClosest {
  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (Array(-1, 2, 1, -4), 1, 2),
      (Array(0, 0, 0), 1, 0)
    )

    inOut.foreach { case (x, target, expected) =>
      val result = threeSumClosest(x, target)
      if (result == expected)
        println(s"Success for (${x.toSeq}, $target) => ${expected}")
      else
        System.err.println(
          s"FAIL for (${x.toSeq}, $target) : expected [${expected}] but found [${result}]"
        )
    }
  }

  /** Slightly adapated solution from ThreeSum.
    */
  def threeSumClosest(nums: Array[Int], target: Int): Int = {
    val numbers = nums.sorted
    var bestResult = Int.MaxValue
    var i = 1
    while (i < numbers.length) {
      var j = 0
      var k = numbers.length - 1
      while (j < i && k > i) {
        val currentSum = numbers(j) + numbers(i) + numbers(k)
        bestResult =
          if (math.abs(target - currentSum) < math.abs(target - bestResult))
            currentSum
          else bestResult
        if (currentSum == target) {
          j = j + 1
          k = k - 1
        } else if (currentSum < target)
          j = j + 1
        else
          k = k - 1
      }
      i = i + 1
    }
    bestResult
  }

}
