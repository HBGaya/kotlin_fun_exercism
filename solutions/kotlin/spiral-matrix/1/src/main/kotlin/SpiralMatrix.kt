object SpiralMatrix {

    fun ofSize(size: Int): Array<IntArray> {
        if(size == 0) return emptyArray()

        val matrix = Array(size) { IntArray(size) }
        var num = 1
        var top = 0
        var bottom = size - 1
        var left = 0
        var right = size - 1

        while(top <= bottom && left <= right) {
            for(i in left..right) {
                matrix[top][i] = num++
            }
            top++

            for(i in top..bottom) {
                matrix[i][right] = num++
            }
            right--

            if(top <= bottom){
                for(i in right downTo left) {
                    matrix[bottom][i] = num++
                }
                bottom--
            }

            if(left <= right) {
                for(i in bottom downTo top) {
                    matrix[i][left] = num++
                }
               left++
            }
        }

        return matrix
    }
}

fun main() {
    val result = SpiralMatrix.ofSize(4)
    for (row in result) {
        println(row.joinToString(" "))
    }
}
