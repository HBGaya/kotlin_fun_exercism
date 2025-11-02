class CustomSet(vararg elements: Int) {

   // allow constructing from a Collection<Int> as well
    constructor(elements: Collection<Int>) : this(*elements.toIntArray())

    internal val items = mutableListOf<Int>()

    init {
        for(element in elements) {
            add(element)
        }
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun isSubset(other: CustomSet): Boolean {
        return items.all { it in other.items }
    }

    fun isDisjoint(other: CustomSet): Boolean {
        return items.none { it in other.items }
    }

    fun contains(other: Int): Boolean {
        return other in items
    }

    fun intersection(other: CustomSet): CustomSet {
        val result = CustomSet()
        for(el in items) {
            if(el in other.items) {
                result.items.add(el)
            }
        }
        return result
    }

    fun add(other: Int) {
        if(!contains(other)) {
            items.add(other)
        }
    }

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(other !is CustomSet) return false
        return items.size == other.items.size && items.all { it in other.items } 
    }

    operator fun plus(other: CustomSet): CustomSet {
        val result = CustomSet()
        result.items.addAll(items)
        for (el in other.items) {
            if (el !in result.items) {
                result.items.add(el)
            }
        }
        return result
    }

    operator fun minus(other: CustomSet): CustomSet {
        val result = CustomSet()
        for (el in items) {
            if (el !in other.items) {
                result.items.add(el)
            }
        }
        return result
    }
    
    override fun toString(): String =
        "CustomSet(${items.joinToString(", ")})"
}

fun main() {
    val a = CustomSet(listOf(1, 2, 3))
    val b = CustomSet(listOf(3, 4))

    println(a + b) // CustomSet(1, 2, 3, 4)
    println(a - b) // CustomSet(1, 2)
    println(a.intersection(b)) // CustomSet(3)
    println(a.isSubset(b)) // false
    println(a.isDisjoint(b)) // false
}
