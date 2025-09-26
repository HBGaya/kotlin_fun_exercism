object Series {

    fun slices(n: Int, s: String): List<List<Int>> {
        require(n > 0) { "Slice length must be positive" }
        require(n <= s.length) { "Slice length $n is too big for string of length ${s.length}" }

        val digits = s.map { it.digitToInt() } 

        return (0..digits.size - n).map { i -> digits.subList(i, i + n) }
    }
}

fun main() {
    println(Series.slices(3, "49142"))

    println(Series.slices(4, "49142"))
}
 