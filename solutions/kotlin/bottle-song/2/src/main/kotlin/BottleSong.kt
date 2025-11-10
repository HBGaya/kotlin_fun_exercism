object BottleSong {

    private val numbers = listOf<String>(
        "no", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"
    )

    private fun numberWord(n: Int): String = numbers[n].replaceFirstChar { it.uppercaseChar() }

    private fun bottleWord(n: Int): String = if(n == 1) "bottle" else "bottles"

    fun recite(startBottles : Int, takeDown : Int) : String {
        return (startBottles downTo (startBottles - takeDown + 1))
            .joinToString("\n\n") { n ->
                val next = n - 1
                """
                    ${numberWord(n)} green ${bottleWord(n)} hanging on the wall,
                    ${numberWord(n)} green ${bottleWord(n)} hanging on the wall,
                    And if one green bottle should accidentally fall,
                    There'll be ${numbers.getOrElse(next) { "no" }} green 
                    ${bottleWord(next)} hanging on the wall.
                """.trimIndent()
            }
    }
}

fun main() {
    println(BottleSong.recite(10, 2))
}
