import scala.annotation.tailrec

/**
 * TODO: very difficult
 */
object MedianOfTwoSortedArrays {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (Array(1, 3), Array(2), 2.0),
      (Array(1, 2), Array(3, 4), 2.5),
      (Array(1, 3, 5, 7, 9, 11), Array(2, 4, 6, 8, 9), 6.0),
      (Array(1, 3), Array(2, 7), 2.5)
    )

    inOut.foreach { case (nums1, nums2, expected) =>
      val result = findMedianSortedArrays(nums1, nums2)
      if (result == expected)
        println(s"Success for (${nums1.toSeq}, ${nums2.toSeq}) => ${expected}")
      else
        System.err.println(
          s"FAIL for (${nums1.toSeq}, ${nums2.toSeq}) : ${expected} => result found: ${result}"
        )
    }
  }

  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    val totalLength = nums1.length + nums2.length
    val isEven = totalLength % 2 == 0

    if (isEven) {
      val mid1 = totalLength / 2 - 1
      val mid2 = totalLength / 2
      (findKthElement(mid1, nums1, 0, nums2, 0) + findKthElement(mid2, nums1, 0, nums2, 0)) / 2.0
    } else {
      val mid = totalLength / 2
      findKthElement(mid, nums1, 0, nums2, 0)
    }
  }

  @tailrec
  def findKthElement(k: Int, arr1: Array[Int], start1: Int, arr2: Array[Int], start2: Int): Double = {
    if (start1 >= arr1.length) return arr2(start2 + k)
    if (start2 >= arr2.length) return arr1(start1 + k)
    if (k == 0) return Math.min(arr1(start1), arr2(start2))

    val mid1 = Math.min(start1 + k / 2, arr1.length - 1)
    val mid2 = Math.min(start2 + k - (mid1 - start1 + 1), arr2.length - 1)

    if (arr1(mid1) <= arr2(mid2)) {
      findKthElement(k - (mid1 - start1 + 1), arr1, mid1 + 1, arr2, start2)
    } else {
      findKthElement(k - (mid2 - start2 + 1), arr1, start1, arr2, mid2 + 1)
    }
  }

}
