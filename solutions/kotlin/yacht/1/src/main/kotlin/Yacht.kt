object Yacht {

    fun solve(category: YachtCategory, vararg dices: Int): Int {
        // first convert those random integers in a list 
        val dice = dices.toList()

        // then arrange it in key pair values like {3:2, 4:1} 
        // to count each occurence of integer value in a list
        val countOccurences = dice.groupingBy { it }.eachCount()

        return when (category) {
            YachtCategory.ONES -> dice.count { it == 1 } * 1
            YachtCategory.TWOS -> dice.count { it == 2 } * 2
            YachtCategory.THREES -> dice.count { it == 3 } * 3
            YachtCategory.FOURS -> dice.count { it == 4 } * 4
            YachtCategory.FIVES -> dice.count { it == 5 } * 5
            YachtCategory.SIXES -> dice.count { it == 6 } * 6
            
            YachtCategory.FULL_HOUSE -> {
                if(countOccurences.values.sorted() == listOf(2,3)) dice.sum() else 0
            }

            YachtCategory.FOUR_OF_A_KIND -> {
                val face = countOccurences.entries.find { it.value >= 4 }?.key
                if(face != null) face * 4 else 0 
            }

            YachtCategory.LITTLE_STRAIGHT -> {
                if(dice.toSet() == setOf(1, 2, 3, 4, 5)) 30 else 0
            }

            YachtCategory.BIG_STRAIGHT -> {
                if(dice.toSet() == setOf(2, 3, 4, 5, 6)) 30 else 0
            }

            YachtCategory.CHOICE -> dice.sum() 

            YachtCategory.YACHT -> {
                if(countOccurences.size == 1) 50 else 0
            }
        }
     }
}

fun main() {
    println(Yacht.solve(YachtCategory.FULL_HOUSE, 3, 3, 3, 5, 5)) // 19
    println(Yacht.solve(YachtCategory.YACHT, 4, 4, 4, 4, 4))     // 50
    println(Yacht.solve(YachtCategory.BIG_STRAIGHT, 2, 3, 4, 5, 6)) // 30
    println(Yacht.solve(YachtCategory.CHOICE, 2, 3, 3, 4, 6))   
}