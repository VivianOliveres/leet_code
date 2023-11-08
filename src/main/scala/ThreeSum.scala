import scala.collection.mutable

object ThreeSum {
  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (Array(-1, 0, 1, 2, -1, -4), List(List(-1, -1, 2), List(-1, 0, 1))),
      (Array(0, 1, 1), List()),
      (Array(0, 0, 0), List(List(0, 0, 0))),
      (
        Array(3, 0, -2, -1, 1, 2),
        List(List(-2, -1, 3), List(-2, 0, 2), List(-1, 0, 1))
      )
    )

    inOut.foreach { case (x, expected) =>
      val result = threeSum(x)
      if (result == expected)
        println(s"Success for (${x.toSeq}) => ${expected}")
      else
        System.err.println(
          s"FAIL for (${x.toSeq}) : expected [${expected}] but found [${result}]"
        )
    }
  }

  /** 1. Sort the numbers
    * 2. Loop over the numbers (index i)
    * 3. Loop with two indexes : one at the beginning (k) and one at the end (k)
    * Then check the sum and move j or according to the sum result.
    */
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    val elements = nums.sorted
    var i = 1
    var results: Set[List[Int]] = Set()
    while (i < elements.length) {
      var j = 0
      var k = elements.length - 1
      while (j < i && k > i) {
        val currentValue = elements(j) + elements(i) + elements(k)
        if (currentValue == 0) {
          results = results + List(elements(j), elements(i), elements(k))
          k = k - 1
          j = j + 1
        } else if (currentValue > 0)
          k = k - 1
        else
          j = j + 1
      }
      i = i + 1
    }
    results.toList
  }
}
