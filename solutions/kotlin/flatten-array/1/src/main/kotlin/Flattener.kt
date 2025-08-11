object Flattener {
    fun flatten(source: Collection<Any?>): List<Any> {
        return source.flatMap { item -> 
            when(item) {
               null -> emptyList()
               is Collection<*> -> flatten(item)
               else -> listOf(item)
            }
        }
    }
}

fun main() {
    val input = listOf(1, listOf(2, 6, null), listOf(listOf(null, 5), 5))
    val result = Flattener.flatten(input)
    println(result)
}
