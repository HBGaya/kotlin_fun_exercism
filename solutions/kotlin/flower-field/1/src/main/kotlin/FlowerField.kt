data class FlowerFieldBoard(private val board: List<String>) {

    fun withNumbers(): List<String> {
        val height = board.size
        if(height == 0) return board
        val width = board[0].length

        val result = MutableList(height) { CharArray(width) }

        for(r in 0 until height) {
            for(c in 0 until width) {
                if(board[r][c] == '*') {
                    result[r][c] = '*'
                }
                else {
                    val count = countFlowersAround(r, c, board)
                    result[r][c] = if(count == 0) ' ' else ('0' + count)
                }
            }
        }

        return result.map { String(it) }
    }

    private fun countFlowersAround(r: Int, c: Int, board: List<String>): Int {
        var count = 0
        for(dr in -1..1) {
            for(dc in -1..1) {
                if(dr == 0 && dc == 0) continue
                val nr = r + dr
                val nc = c + dc
                if(nr in board.indices && nc in board[nr].indices && board[nr][nc] == '*') {
                    count++
                }
            }
        }

        return count
    }
}

fun main() {
    val garden = listOf(
        " * * ",
        "  *  ",
        "  *  ",
        "     "
    )

    val board = FlowerFieldBoard(garden)
    val result = board.withNumbers()

    result.forEach { println(it) }
}
