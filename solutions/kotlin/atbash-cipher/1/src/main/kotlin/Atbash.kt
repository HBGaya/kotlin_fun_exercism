object Atbash {

    private val plain = ('a'..'z').toList()
    private val cipher = plain.reversed()

    private val encodedMap = plain.zip(cipher).toMap()
    private val decodedMap = cipher.zip(plain).toMap()
    
    fun encode(s: String): String {
        val normalizedString = s.lowercase().filter { it.isLetterOrDigit() }
        val encoded = normalizedString.map {
            encodedMap[it] ?: it
        }.joinToString("")


        return encoded.chunked(5).joinToString(" ")
    }

    fun decode(s: String): String {
        val normalizedString = s.lowercase().filter { it.isLetterOrDigit() }
        
        return normalizedString.map {
            decodedMap[it] ?: it
        }.joinToString("")
    }
}

fun main() {
    println(Atbash.encode("test")) // gvhg
    println(Atbash.encode("x123 yes")) // c123b vh
    println(Atbash.decode("gvhg")) // test
    println(Atbash.decode("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt"))
    // thequickbrownfoxjumpsoverthelazydog
}
