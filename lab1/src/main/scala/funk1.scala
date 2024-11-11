def printIterator(n: Int): Unit = {
    var x=0
    for (i <- 0 until n){
        if (i % 2 == 0 ) x = i
        else x = n - i
        println( s"hello $x")

    }
}
