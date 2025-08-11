object ETL {
    fun transform(source: Map<Int, Collection<Char>>): Map<Char, Int> {
        val letterScores: Map<Char, Int> = source.flatMap { (score, chars) -> chars.map {
                char -> char.lowercaseChar() to score
            }
        }.toMap()

        return letterScores
    }
}

fun main() {
    val scoreGroups: Map<Int, Collection<Char>> = mapOf(
         1 to listOf('A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'),
         2 to listOf('D', 'G'),
         3 to listOf('B', 'C', 'M', 'P'),
         4 to listOf('F', 'H', 'V', 'W', 'Y'),
         5 to listOf('K'),
         8 to listOf('J', 'X'),
         10 to listOf('Q', 'Z')
         
    )
    
    println("The scores are : ${ETL.transform(scoreGroups)}")
}
