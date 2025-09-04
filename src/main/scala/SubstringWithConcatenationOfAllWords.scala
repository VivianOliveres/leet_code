object SubstringWithConcatenationOfAllWords {

  def main(args: Array[String]): Unit = {
    val inOut: Seq[(String, Array[String], Array[Int])] = Seq(
      ("barfoothefoobarman", Seq("foo","bar").toArray, Seq(0, 9).toArray),
      ("wordgoodgoodgoodbestword", Seq("word","good","best","word").toArray, Seq.empty[Int].toArray),
      ("barfoofoobarthefoobarman", Seq("bar","foo","the").toArray, Seq(6,9,12).toArray),
      ("wordgoodgoodgoodbestword", Seq("word","good","best","good").toArray, Seq(8).toArray),
    )

    inOut.foreach { case (input, words, expected) =>
      val result = findSubstring(input, words).toArray
      if (result sameElements expected)
        println(s"Success for input[$input], words[${words.mkString("Array(", ", ", ")")}], result[${result.mkString("Array(", ", ", ")")}] => [${expected.mkString("Array(", ", ", ")")}]")
      else
        System.err.println(
          s"FAIL for input[$input], words[${words.mkString("Array(", ", ", ")")}], result[${result.mkString("Array(", ", ", ")")}] => [${expected.mkString("Array(", ", ", ")")}]"
        )
    }
  }

  def findSubstring(s: String, words: Array[String]): List[Int] = {
    findWithWordCounts(s, words)
  }

  private def findWithWordCounts(s: String, words: Array[String]): List[Int] = {
    val wordSize = words.head.length
    val totalSize = words.length * wordSize
    val wordsCount = words.groupBy(identity).view.mapValues(_.length).toMap

    s
      // sliding will generate substring
      .sliding(totalSize)
      //Group into a Map to not manage permutations
      .map(sub => sub.grouped(wordSize).toList.groupBy(identity).view.mapValues(_.length).toMap)
      .zipWithIndex
      .filter(pair => pair._1 == wordsCount)
      .map(_._2)
      .toList
  }


  // Solution with permutations
  // We generate all possible permutations and for each index, we search if the substring is one of them
  // Fail with "Memory Limit Exceed"
  private def findWithPermutations(s: String, words: Seq[String]): List[Int] = {
    val permutations = words.permutations.toSeq.map(_.mkString)
    var currentIndex = 0
    var results = Seq.empty[Int]
    while (currentIndex < s.length) {
      val maybe = permutations.find{perm =>
        currentIndex + perm.length <= s.length && s.substring(currentIndex, currentIndex + perm.length) == perm
      }
      if (maybe.nonEmpty)
        results = results :+ currentIndex
      currentIndex += 1
    }
    results.toList
  }

  // Solution with recursion for every words
  // For each index, we try each word, then recursively try other words
  // Fail with "Memory Limit Exceed"
  private def doFindSubstring(s: String, words: Seq[String]): List[Int] = {
    var currentIndex = 0
    var results = Seq.empty[Int]
    while (currentIndex < s.length) {
//      println(s"doFindSubstring - currentIndex[$currentIndex]")
      val maybeSolution = doFindAll(s, words, currentIndex, currentIndex)
//      println(s"doFindSubstring - maybeSolution[$maybeSolution] nextIndex[$nextIndex]")
      if (maybeSolution.nonEmpty) {
        results = results :+ maybeSolution.get
      }
      currentIndex += 1
    }
    results.toList
  }

  private def doFindAll(s: String, remainingWords: Seq[String], currentIndex: Int, initialIndex: Int): Option[Int] = {
    if (remainingWords.isEmpty)
      Some(initialIndex)
    else {
      val maybeSolution = remainingWords.zipWithIndex.map{case (word, index) =>
//        println(s"doFindAll - currentIndex[$currentIndex] word[$word] subStr[${s.substring(currentIndex)}]")
        val newRemaining = remainingWords.patch(index, Nil, 1)
        if (currentIndex + word.length <= s.length && s.substring(currentIndex, currentIndex + word.length) == word) {
          doFindAll(s, newRemaining, currentIndex + word.length, initialIndex)
        }else None
      }.find{_.nonEmpty}
      maybeSolution.flatten
    }
  }
}
