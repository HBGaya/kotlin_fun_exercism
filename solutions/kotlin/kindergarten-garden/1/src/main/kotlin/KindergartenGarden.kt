class KindergartenGarden(private val diagram: String) {

   private val students = listOf(
        "Alice", "Bob", "Charlie", "David", "Eve", "Fred",
        "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"
    )

   private val plantMap = mapOf(
       'G' to "grass",
       'C' to "clover",
       'R' to "radishes",
       'V' to "violets"
   )

    fun getPlantsOfStudent(student: String): List<String> {
        val rows = diagram.split("\n")
        val index = students.indexOf(student)
        if(index == -1) return emptyList()

        val start = index * 2
        val chars = listOf(
            rows[0][start], rows[0][start + 1],
            rows[1][start], rows[1][start + 1]
        )

        return chars.map { plantMap[it] ?: "Unknwon" }
    }
}

fun main() {
    val garden = KindergartenGarden(
        "VRCGVVRVCGGCCGVRGCVCGCGV\nVRCCCGCRRGVCGCRVVCVGCGCV"
    )

    println(garden.getPlantsOfStudent("Alice"))  // [Violets, Radishes, Violets, Radishes]
    println(garden.getPlantsOfStudent("Bob"))    // [Clover, Grass, Clover, Clover]
}
