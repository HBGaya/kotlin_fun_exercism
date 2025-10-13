class DiamondPrinter() {

    // Returns the diamond as a list of strings (for Exercism tests)
    fun printToList(ch: Char): List<String> {
        require(ch in 'A'..'Z') { "Input must be an uppercase letter A..Z" }

        val n = ch - 'A'
        val lines = mutableListOf<String>()

        fun makeLine(i: Int): String {
            val letter = ('A' + i)
            val leading = n - i
            return if (i == 0) {
                " ".repeat(leading) + letter + " ".repeat(leading)
            } else {
                val inner = 2 * i - 1
                " ".repeat(leading) + letter + " ".repeat(inner) + letter + " ".repeat(leading)
            }
        }

        // Top half including middle
        for (i in 0..n) lines += makeLine(i)
        // Bottom half (mirror)
        for (i in n - 1 downTo 0) lines += makeLine(i)

        return lines
    }
}

// Example usage
fun main() {
    val diamondC = DiamondPrinter()
    diamondC.printToList('C').forEach { println(it) }
}
