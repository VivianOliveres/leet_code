object TwoSum {

  def main(args: Array[String]): Unit = {
    val inOut: Seq[(Array[Int],Int,Array[Int])] = Seq(
      (Array(2,7,11,15), 9, Array(0,1)),
      (Array(3,2,4), 6, Array(1, 2)),
      (Array(3,3), 6, Array(0, 1)),
      (Array(6,5,7,8,9,3), 10, Array(2, 5)),
    )

    inOut.foreach{ case (nums, target, expected) =>
      val result = twoSum(nums, target)
      if (result.sameElements(expected))
        println(s"Success for (${nums.toSeq}, $target) => ${expected.toSeq}")
      else
        System.err.println(s"FAIL for (${nums.toSeq}, $target) : ${expected.toSeq} => result found: ${result.toSeq}")
    }
  }

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    var i = 0
    val numIndexes: Map[Int, Seq[Int]] = nums.zipWithIndex
      .groupMapReduce(_._1) {case (_, index) => Seq(index)} (_ ++ _)
    numIndexes.keys.foreach { key =>
      val other = target - key
      if (key == other && numIndexes(key).length > 1)
        return Array(numIndexes(key).head, numIndexes(key)(1))
      else if (key != other && numIndexes.contains(other))
        return Array(numIndexes(key).head, numIndexes(other).head)
    }

    Array(-1, -1)
  }
}
