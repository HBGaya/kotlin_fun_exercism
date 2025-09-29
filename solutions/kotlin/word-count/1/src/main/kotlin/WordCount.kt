object WordCount {

    fun phrase(phrase: String): Map<String, Int> {
        val regex = Regex("[a-z0-9]+(?:'[a-z0-9]+)?")

        return regex.findAll(phrase.lowercase())
             .map { it.value }
             .groupingBy { it }
             .eachCount()
    }
}

fun main() {
    val subtitle = "That's the password: 'PASSWORD 123'!, cried the Special Agent.\nSo I fled."

    val wordCounts = WordCount.phrase(subtitle)

    println(wordCounts)
}
