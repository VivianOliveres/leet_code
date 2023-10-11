import scala.collection.mutable

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

  /**
   * Working Solution but not the most efficient.
   * For each value, store its index in a map.
   * Then check if there is a couple of (key, target-key) values in the map.
   * Be careful of the key==target-key where we need to check if there is at least 2 index for this key.
   *
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
  */

  /**
   * Better solution as it checks, during the indexing phase, if we already have found a solution.
   */
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val numIndexes: mutable.Map[Int, Seq[Int]] = mutable.Map()
    var i = 0
    while (i < nums.length) {
      val value = nums(i)
      val other = target - value
      if (value == other && numIndexes.contains(value))
        return Array(numIndexes(value).head, i)
      else if (value != other && numIndexes.contains(other))
        return Array(numIndexes(other).head, i)
      else if (!numIndexes.contains(value))
        numIndexes(value) = Seq(i)

      i = i + 1
    }

    Array(-1, -1)
  }
}
