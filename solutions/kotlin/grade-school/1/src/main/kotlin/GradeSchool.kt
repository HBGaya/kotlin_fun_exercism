class School {

    private val roster = mutableMapOf<Int, MutableSet<String>>()

    fun add(student: String, grade: Int) {
        require(student.isNotBlank()) { "Student name cannot be blank" }

        val students = roster.getOrPut(grade) { mutableSetOf() }
        if(!students.add(student)) {
            throw IllegalArgumentException("$student is already in grade $grade")
        }
    }

    fun grade(grade: Int): List<String> {
        return roster[grade]?.sorted() ?: emptyList()
    }

    fun roster(): List<String> {
        return roster.toSortedMap().flatMap {
            (_, students) -> students.sorted()
        }
    }
}

fun main() {
    val school = School()

    school.add("Jim", 2)
    school.add("Anna", 1)
    school.add("Barb", 1)
    school.add("Charlie", 1)
    school.add("Alex", 2)
    school.add("Peter", 2)
    school.add("Zoe", 2)
    school.add("Jim", 5) // different grade, allowed

    println("Grade 1: ${school.grade(1)}")  // [Anna, Barb, Charlie]
    println("Grade 2: ${school.grade(2)}")  // [Alex, Jim, Peter, Zoe]
    println("Grade 5: ${school.grade(5)}")  // [Jim]

    println("\nFull Roster:")
    println(school.roster())
}
