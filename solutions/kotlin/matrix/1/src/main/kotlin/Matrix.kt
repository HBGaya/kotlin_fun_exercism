class Matrix(private val matrixAsString: String) {

    private val matrix: List<List<Int>> = matrixAsString.trim().lines().map { 
        line -> line.trim().split(Regex("\\s+")).map { it.toInt() }
    }

    fun column(colNr: Int): List<Int> {
        return matrix.map { it[colNr - 1] }
    }

    fun row(rowNr: Int): List<Int> {
        return matrix[rowNr - 1]
    }
}

fun main() {
    val matrixString = """
        9 8 7
        5 3 2
        6 6 7
    """.trimIndent()

    val matrix = Matrix(matrixString)

    println(matrix.row(1))    // [9, 8, 7]
    println(matrix.column(2)) // [8, 3, 6]
}
