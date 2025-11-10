object Transpose {

    fun transpose(input: List<String>): List<String> {
        if (input.isEmpty()) return emptyList()

        val height = input.size
        val width = input.maxOf { it.length }

        // Pad all lines to the same length
        val padded = input.map { it.padEnd(width, ' ') }

        // Build the raw transposed matrix
        val transposed = mutableListOf<String>()
        for (col in 0 until width) {
            val sb = StringBuilder()
            for (row in 0 until height) {
                sb.append(padded[row][col])
            }
            transposed.add(sb.toString())
        }

        // --- Smart right-trim: remove only spaces that are truly beyond the last visible char
        // Determine the last non-space position for each transposed line
        var lastNonBlank = transposed.lastIndex
        while (lastNonBlank >= 0 && transposed[lastNonBlank].isBlank()) {
            transposed[lastNonBlank] = ""
            lastNonBlank--
        }

        // Now, for every line above, cut trailing spaces
        for (i in lastNonBlank downTo 0) {
            val line = transposed[i]
            var end = line.length
            while (end > 0) {
                // if any lower line has a non-space in this column, stop trimming
                val colHasCharBelow =
                    (i + 1..lastNonBlank).any { transposed[it].length > end - 1 && transposed[it][end - 1] != ' ' }
                if (colHasCharBelow || line[end - 1] != ' ') break
                end--
            }
            transposed[i] = line.substring(0, end)
        }

        return transposed
    }
}


fun main() {
    val input = listOf("AB", "DEF")
    val result = Transpose.transpose(input)
    println(result.joinToString("\n"))
}

