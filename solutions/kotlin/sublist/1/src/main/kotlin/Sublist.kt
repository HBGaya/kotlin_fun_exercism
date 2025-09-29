enum class Relationship {

    EQUAL, SUBLIST, SUPERLIST, UNEQUAL

}

fun <T> List<T>.relationshipTo(other: List<T>): Relationship {
        return when {
           this == other -> Relationship.EQUAL
           this.isSublistOf(other) -> Relationship.SUBLIST
           other.isSublistOf(this) -> Relationship.SUPERLIST
           else -> Relationship.UNEQUAL 
        }
    }

private fun <T> List<T>.isSublistOf(other: List<T>): Boolean {
        if(this.isEmpty()) return true
        if(this.size > other.size) return false
        return other.windowed(this.size).any { it == this}
    }

fun main() {
   val a = listOf(1, 2, 3)
   val b = listOf(1, 2, 3, 4, 5)
   val c = emptyList<Int>()

    println(a.relationshipTo(b))  // SUBLIST
    println(b.relationshipTo(a))  // SUPERLIST
    println(a.relationshipTo(a))  // EQUAL
    println(c.relationshipTo(b))  // SUBLIST
    println(b.relationshipTo(c))  // SUPERLIST
    println(a.relationshipTo(listOf(1, 3, 2))) // UNEQUAL
}
