fun twofer(name: String? = null): String {
    val person = name ?: "you"

    return "One for $person, one for me."
}

fun main() {
    println(twofer("Alice"))
    println(twofer(""))
}