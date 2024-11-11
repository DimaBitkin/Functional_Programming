def splitByIndex(collection : Seq[Int]): (Seq[Int], Seq[Int])= {
    val evenIndexed = collection
            .zipWithIndex  
            .filter{ q=>q._2 % 2 == 0}
            .map(q=>q._1)

    val oddIndexed = collection
            .zipWithIndex
            .filter{ q=>q._2 % 2 != 0}
            .map(q=>q._1)
    (evenIndexed, oddIndexed)
}
