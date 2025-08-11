object EliudsEggs {

    fun eggCount(number: Int): Int{
        var count = 0
        var n = number

        while(n > 0) {
            if(n % 2 == 1) {
                count++
            }
          n /= 2
        }
        
        return count
    }
}

fun main() {
    println("Eggs in display 89: ${EliudsEggs.eggCount(89)}")
    println("Eggs in display 89: ${EliudsEggs.eggCount(16)}")
    println("Eggs in display 89: ${EliudsEggs.eggCount(0)}")
}
