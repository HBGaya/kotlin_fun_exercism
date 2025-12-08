class ChangeCalculator(private val coins: List<Int>) {
    // TODO: implement proper constructor

    fun computeMostEfficientChange(grandTotal: Int): List<Int> {
          if (grandTotal < 0) throw IllegalArgumentException("Negative totals are not allowed.")
        if (grandTotal == 0) return emptyList()
        if (coins.isEmpty()) throw IllegalArgumentException("No coins available")

        val dp = IntArray(grandTotal + 1) { Int.MAX_VALUE } // min coins for amount i
        val lastCoin = IntArray(grandTotal + 1) { -1 }       // last coin used for amount i

        dp[0] = 0 // 0 coins needed for 0

        for (amount in 1..grandTotal) {
            for (coin in coins) {
                if (coin <= amount && dp[amount - coin] != Int.MAX_VALUE) {
                    val candidate = dp[amount - coin] + 1
                    if (candidate < dp[amount]) {
                        dp[amount] = candidate
                        lastCoin[amount] = coin
                    }
                }
            }
        }

        if (dp[grandTotal] == Int.MAX_VALUE) {
            throw IllegalArgumentException("The total $grandTotal cannot be represented in the given currency.")
        }

        // reconstruct coins
        val result = mutableListOf<Int>()
        var remaining = grandTotal
        while (remaining > 0) {
            val coin = lastCoin[remaining]
            result.add(coin)
            remaining -= coin
        }

        return result
    }
}

fun main() {
    val calculator = ChangeCalculator(listOf(1, 5, 10, 25, 100))

    println(calculator.computeMostEfficientChange(15)) // [10, 5]
    println(calculator.computeMostEfficientChange(40)) // [25, 10, 5]
    println(calculator.computeMostEfficientChange(12)) // [10, 2] if coins include 2
}
