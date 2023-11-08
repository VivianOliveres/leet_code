object ContainerWithMostWater {

  def main(args: Array[String]): Unit = {
    val inOut = Seq(
      (Array(1, 8, 6, 2, 5, 4, 8, 3, 7), 49),
      (Array(1, 1), 1)
    )

    inOut.foreach { case (x, expected) =>
      val result = maxArea(x)
      if (result == expected)
        println(s"Success for (${x.toSeq}) => ${expected}")
      else
        System.err.println(
          s"FAIL for (${x.toSeq}) : ${expected} => result found: ${result}"
        )
    }
  }

  def maxArea(height: Array[Int]): Int = {
    var maxArea = 0
    var leftX = 0
    var rightX = height.length - 1
    while (leftX < rightX) {
      val leftY = height(leftX)
      val rightY = height(rightX)
      maxArea = math.max(maxArea, (rightX - leftX) * math.min(leftY, rightY))
      if (leftY < rightY)
        leftX = leftX + 1
      else
        rightX = rightX - 1
    }
    maxArea
  }
}
