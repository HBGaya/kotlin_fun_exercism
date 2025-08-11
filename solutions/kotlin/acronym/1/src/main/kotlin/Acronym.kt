object Acronym {
    fun generate(phrase: String) : String {
        val genAcronym = phrase.replace("-", " ")
                .replace(Regex("[^A-Za-z ]"), "")
                .split(" ")
                .filter { it.isNotBlank() }

        return genAcronym.map { it[0].uppercaseChar() }.joinToString("")
    }
}

fun main() {
    println(Acronym.generate("As Soon As Possible"))
    println(Acronym.generate("Liquid-crystal display"))
    println(Acronym.generate("Thank George It's Friday!"))
}
