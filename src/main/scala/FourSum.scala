object FourSum {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (
        Array(1, 0, -1, 0, -2, 2),
        0,
        List(List(-2, -1, 1, 2), List(-2, 0, 0, 2), List(-1, 0, 0, 1))
      ),
      (Array(2, 2, 2, 2, 2), 8, List(List(2, 2, 2, 2))),
      (Array(-3, -1, 0, 2, 4, 5), 0, List(List(-3, -1, 0, 4))),
      (
        Array(1000000000, 1000000000, 1000000000, 1000000000),
        -294967296,
        List()
      )
    )

    inOut.foreach { case (x, target, expected) =>
      val result = fourSum(x, target)
      if (result == expected)
        println(s"Success for (${x.toSeq}, $target) => ${expected}")
      else
        System.err.println(
          s"FAIL for (${x.toSeq}, $target) : expected [${expected}] but found [${result}]"
        )
    }
  }

  def fourSum(nums: Array[Int], target: Int): List[List[Int]] = {
    val numbers = nums.sorted
    var a = 0
    var d = nums.length - 1
    var results: Set[List[Int]] = Set()
    while (d - a > 1) {
      while (d - a > 1) {
        var b = a + 1
        var c = d - 1
        while (b < c) {
          // Convert into a long to avoid negative sum
          val currentSum =
            numbers(a).toLong + numbers(b) + numbers(c) + numbers(d)
          if (currentSum == target) {
            results =
              results + List(numbers(a), numbers(b), numbers(c), numbers(d))
            b = b + 1
            c = c - 1
          } else if (currentSum < target)
            b = b + 1
          else
            c = c - 1
        }

        d = d - 1
      }
      d = nums.length - 1
      a = a + 1
    }
    results.toList
  }

}
