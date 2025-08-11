object SumOfMultiples {

    fun sum(factors: Set<Int>, limit: Int): Int {
        return factors
          .filter { it != 0 }
          .flatMap { factor -> (factor until limit step factor).toList() }
          .toSet()
          .sum()
    }
}

fun main() {
    val gameLevel = 20
    val magicalItems = setOf(3, 5)

    val totalEnergyPoints = SumOfMultiples.sum(magicalItems, gameLevel)

    println("The energy points for player is: $totalEnergyPoints")
}
