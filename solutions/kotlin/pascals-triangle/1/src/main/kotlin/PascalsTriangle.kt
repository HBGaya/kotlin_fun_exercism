object PascalsTriangle {

    fun computeTriangle(rows: Int): List<List<Int>> {
        if(rows <= 0) return emptyList()

        val triangle = mutableListOf<List<Int>>()

        for(i in 0 until rows) {
            if(i == 0) {
                triangle.add(listOf(1))
            }
            else {
                val prev = triangle[i - 1]
                val curr = mutableListOf<Int>()
                curr.add(1)

                for(j in 1 until prev.size) {
                    curr.add(prev[j - 1] + prev[j])
                }

                curr.add(1)
                triangle.add(curr)
            }
        }

        return triangle
    }
}

fun main() {
    val result = PascalsTriangle.computeTriangle(5)
    result.forEach { println(it) }
}
