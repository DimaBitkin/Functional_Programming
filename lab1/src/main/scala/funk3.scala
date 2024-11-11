def findMax(collection: Seq[Int]): Int = {
    collection.reduce((a,b) => if(a >b) a else b)
}