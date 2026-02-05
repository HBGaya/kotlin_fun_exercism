class ZebraPuzzle {

    companion object {
        const val RED = 0
        const val GREEN = 1
        const val IVORY = 2
        const val YELLOW = 3
        const val BLUE = 4

        const val ENGLISHMAN = 0
        const val SPANIARD = 1
        const val UKRAINIAN = 2
        const val NORWEGIAN = 3
        const val JAPANESE = 4

        const val COFFEE = 0
        const val TEA = 1
        const val MILK = 2
        const val ORANGEJUICE = 3
        const val WATER = 4

        const val DOG = 0
        const val SNAILS = 1
        const val FOX = 2
        const val HORSE = 3
        const val ZEBRA = 4

        const val DANCING = 0
        const val PAINTING = 1
        const val READING = 2
        const val FOOTBALL = 3
        const val CHESS = 4
    }

    private fun permutations(): List<IntArray> {
            val result = mutableListOf<IntArray>()
            val items = intArrayOf(1, 2, 3, 4, 5)

            fun permute(a: IntArray, l: Int) {
                if (l == a.size) {
                    result.add(a.clone())
                } else {
                    for (i in l until a.size) {
                        val tmp = a[l]
                        a[l] = a[i]
                        a[i] = tmp

                        permute(a, l + 1)

                        a[i] = a[l]
                        a[l] = tmp
                    }
                }
            }

            permute(items, 0)
            return result
        }

    private fun rightOf(a: Int, b: Int) = a == b + 1
    private fun nextTo(a: Int, b: Int) =
        kotlin.math.abs(a - b) == 1

    private val solution by lazy {

        for (color in permutations()) {
            if (!rightOf(color[GREEN], color[IVORY])) continue

            for (nation in permutations()) {
                if (nation[NORWEGIAN] != 1) continue
                if (nation[ENGLISHMAN] != color[RED]) continue
                if (!nextTo(nation[NORWEGIAN], color[BLUE])) continue

                for (drink in permutations()) {
                    if (drink[MILK] != 3) continue
                    if (drink[COFFEE] != color[GREEN]) continue
                    if (drink[TEA] != nation[UKRAINIAN]) continue

                    for (pet in permutations()) {
                        if (pet[DOG] != nation[SPANIARD]) continue

                        for (hobby in permutations()) {
                            if (hobby[DANCING] != pet[SNAILS]) continue
                            if (hobby[PAINTING] != color[YELLOW]) continue
                            if (!nextTo(hobby[READING], pet[FOX])) continue
                            if (!nextTo(hobby[PAINTING], pet[HORSE])) continue
                            if (hobby[CHESS] != nation[JAPANESE]) continue

                            return@lazy Triple(nation, drink, pet)
                        }
                    }
                }
            }
        }
        error("No solution")
    }

    fun drinksWater(): String {
        val (nation, drink, _) = solution
        return when (nation.indexOf(drink[WATER])) {
            NORWEGIAN -> "Norwegian"
            ENGLISHMAN -> "Englishman"
            SPANIARD -> "Spaniard"
            UKRAINIAN -> "Ukrainian"
            JAPANESE -> "Japanese"
            else -> error("Impossible")
        }
    }

    fun ownsZebra(): String {
        val (nation, _, pet) = solution
        return when (nation.indexOf(pet[ZEBRA])) {
            NORWEGIAN -> "Norwegian"
            ENGLISHMAN -> "Englishman"
            SPANIARD -> "Spaniard"
            UKRAINIAN -> "Ukrainian"
            JAPANESE -> "Japanese"
            else -> error("Impossible")
        }
    }
}

fun main() {
    val puzzle = ZebraPuzzle()
    println("Drinks water: ${puzzle.drinksWater()}")
    println("Owns zebra: ${puzzle.ownsZebra()}")
}