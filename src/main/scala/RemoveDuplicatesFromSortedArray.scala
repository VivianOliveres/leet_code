object RemoveDuplicatesFromSortedArray {
  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (Array(1, 1, 2), 2),
      (Array(0, 0, 1, 1, 1, 2, 2, 3, 3, 4), 5),
      (Array(1), 1),
    )

    inOut.foreach { case (x, expected) =>
      val result = removeDuplicates(x)
      if (result == expected)
        println(s"Success for (${x.toSeq}) => ${expected}")
      else
        System.err.println(s"FAIL for (${x.toSeq}) : ${expected} => result found: ${result}")
    }
  }


  def removeDuplicates(nums: Array[Int]): Int = {
    var count = 1
    var toInsert = 0
    var toCheck = 1
    //Array(1, 1, 2)
    while (toCheck < nums.length) {
      if (nums(toInsert) < nums(toCheck)) {
        nums(toInsert + 1) = nums(toCheck)
        toInsert = toInsert + 1
        toCheck = toCheck + 1
        count = count + 1
      } else {
        toCheck = toCheck + 1
      }
    }

    count
  }
}
