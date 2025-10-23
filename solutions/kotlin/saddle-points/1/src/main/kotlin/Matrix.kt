data class MatrixCoordinate(val row: Int, val col: Int)

class Matrix(private val grid: List<List<Int>>) {
    
    val saddlePoints: Set<MatrixCoordinate>
       get() {
           if(grid.isEmpty() || grid.any { it.isEmpty() }) return emptySet()

           val rowCount = grid.size
           val colCount = grid[0].size

           val rowMaxValues = grid.map { row -> row.maxOrNull()!! }
           val colMinValues = (0 until colCount).map { col ->
                              (0 until rowCount).minOf { row -> grid[row][col] } } 
           
           val result = mutableSetOf<MatrixCoordinate>()

           for(r in 0 until rowCount) {
              for(c in 0 until colCount) {
                 val value = grid[r][c]
                 if(value == rowMaxValues[r] && value == colMinValues[c]) {
                      result.add(MatrixCoordinate(r + 1, c + 1))
                 }
             }
          }

         return result                   
           
       }
}


fun main() {
    val matrix = Matrix(
        listOf(
            listOf(9, 8, 7),
            listOf(5, 3, 2),
            listOf(6, 6, 7)
        )
    )
    println(matrix.saddlePoints)
}