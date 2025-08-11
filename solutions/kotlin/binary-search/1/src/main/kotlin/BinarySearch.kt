object BinarySearch {
    fun search(list: List<Int>, item: Int): Int? {
       val index = list.binarySearch(item)
       return if(index >= 0) {
           index
       } else {
           throw NoSuchElementException("Item $item not found in play list")
       }
    }
}

fun main() {
    val playList = listOf(4, 8, 12, 16, 23, 28, 32)

    val target = 33
    try { 
      val searchResult = BinarySearch.search(playList, target)
      println("The target $target found at index $searchResult in play list")
    }
    catch(e: NoSuchElementException) {
        println("No target found in play list")
    }
}
