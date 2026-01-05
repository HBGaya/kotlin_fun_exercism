fun <T> List<T>.customAppend(list: List<T>): List<T> {
    val result = mutableListOf<T>()

    for(item in this) {
        result.add(item)
    }

    for(item in list) {
        result.add(item)
    }

    return result
}

fun List<Any>.customConcat(): List<Any> {
    val result = mutableListOf<Any>()

     fun flatten(item: Any) {
        if (item is List<*>) {
            for (element in item) {
                if (element != null) {
                    flatten(element)
                }
            }
        } else {
            result.add(item)
        }
    }

    for (item in this) {
        flatten(item)
    }

    return result
}

fun <T> List<T>.customFilter(predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()

    for(item in this) {
        if(predicate(item)) {
            result.add(item)
        }
    }

    return result
}

val List<Any>.customSize: Int get() {
    var count = 0
    
    for(item in this) {
        count++
    }

    return count
}

fun <T, U> List<T>.customMap(transform: (T) -> U): List<U> {
    val result = mutableListOf<U>()

    for(item in this) {
        result.add(transform(item))
    }

    return result
}

fun <T, U> List<T>.customFoldLeft(initial: U, f: (U, T) -> U): U {
    var accumulator = initial

    for(item in this) {
        accumulator = f(accumulator, item)
    }

    return accumulator
}

fun <T, U> List<T>.customFoldRight(initial: U, f: (T, U) -> U): U {
    var accumulator = initial
    var index = this.size - 1

    while(index >= 0) {
        accumulator = f(this[index], accumulator)
        index--
    }

    return accumulator
}

fun <T> List<T>.customReverse(): List<T> {
    val result = mutableListOf<T>()
    var index = this.size - 1
    while(index >= 0) {
        result.add(this[index])
        index--
    }

    return result
}

fun main() {

    val list1 = listOf(1, 2, 3)
    val list2 = listOf(4, 5)

    // customAppend
    println(list1.customAppend(list2)) 
    // expected: [1, 2, 3, 4, 5]

    // customConcat
    val nested = listOf(
        listOf(1, 2),
        listOf(3),
        listOf(4, 5)
    )
    println(nested.customConcat())
    // expected: [1, 2, 3, 4, 5]

    // customFilter
    println(list1.customFilter { it % 2 != 0 })
    // expected: [1, 3]

    // customSize
    println(list1.customSize)
    // expected: 3

    // customMap
    println(list1.customMap { it * 2 })
    // expected: [2, 4, 6]

    // customFoldLeft
    println(list1.customFoldLeft(0) { acc, item -> acc + item })
    // expected: 6

    // customFoldRight
    println(list1.customFoldRight("") { item, acc -> "$item$acc" })
    // expected: "123"

    // customReverse
    println(list1.customReverse())
    // expected: [3, 2, 1]
}
