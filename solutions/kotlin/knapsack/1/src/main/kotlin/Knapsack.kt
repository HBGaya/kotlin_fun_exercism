data class Item(val weight: Int, val value: Int)

fun knapsack(maximumWeight: Int, items: List<Item>): Int {
    
    val dp = IntArray(maximumWeight + 1)

    for(item in items) {
        for(w in maximumWeight downTo item.weight) {
            dp[w] = maxOf(dp[w], dp[w - item.weight] + item.value)
        }
    }


    return dp[maximumWeight]
}

fun main() {
    val items = listOf(
        Item(5, 10),
        Item(4, 40),
        Item(6, 30),
        Item(4, 50)
    )

    val maxWeight = 10
    val result = knapsack(maxWeight, items)

    println("Maximum value: $result") // ✅ Output: 90
}

