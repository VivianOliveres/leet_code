object RemoveElement {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (Array(3, 2, 2, 3), 3, 2),
      (Array(0, 1, 2, 2, 3, 0, 4, 2), 2, 5)
    )

    inOut.foreach { case (x, value, expected) =>
      val result = removeElement(x, value)
      if (result == expected)
        println(s"Success for (${x.toSeq}) => ${expected}")
      else
        System.err.println(
          s"FAIL for (${x.toSeq}) : ${expected} => result found: ${result}"
        )
    }
  }

  def removeElement(nums: Array[Int], `val`: Int): Int = {
    var toInsert = 0
    var toCheck = 0
    var count = 0
    while (toCheck < nums.length) {
      if (nums(toCheck) != `val`) {
        nums(toInsert) = nums(toCheck)
        count = count + 1
        toInsert = toInsert + 1
      }
      toCheck = toCheck + 1
    }
    count
  }
}
