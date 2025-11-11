class BowlingGame {

    private val rolls = mutableListOf<Int>()

    fun roll(pins: Int) {
        require(pins in 0..10) { "Invalid pin count: $pins" }
        rolls.add(pins)
    }

    fun score(): Int {
        var totalScore = 0
        var rollIndex = 0

        for (frame in 1..10) {
            val first = rolls.getOrElse(rollIndex) { 0 }
            val second = rolls.getOrElse(rollIndex + 1) { 0 }

            when {
                first == 10 -> {
                    totalScore += 10 + strikeBonus(rollIndex)
                    rollIndex += 1
                }

                first + second == 10 -> {
                    totalScore += 10 + spareBonus(rollIndex)
                    rollIndex += 2
                }

                else -> {
                    totalScore += first + second
                    rollIndex += 2
                }
            }
        }

        return totalScore
    }

    private fun strikeBonus(rollIndex: Int): Int =
        rolls.getOrElse(rollIndex + 1) { 0 } + rolls.getOrElse(rollIndex + 2) { 0 }


    private fun spareBonus(rollIndex: Int): Int =
        rolls.getOrElse(rollIndex + 2) { 0 }

}

fun main() {
    val game = BowlingGame()

    // Example 1: strike, spare, open frame
    listOf(10, 5, 5, 9, 0).forEach { game.roll(it) }

    println("Score: ${game.score()}")
}
