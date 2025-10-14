class IsbnVerifier {

    fun isValid(number: String): Boolean {
        val clean = number.replace("-", "")

        if(clean.length != 10) return false

        for(i in 0 until 9) {
            if(!clean[i].isDigit()) return false
        }

        val lastChar = clean.last()
        if(!(lastChar.isDigit() || lastChar == 'X')) return false

        var sum = 0
        for(i in 0 until 10) {
            val value = when (clean[i]) {
                'X' -> 10
                else -> clean[i].digitToInt()
            }

            sum += value * (10 - i)
        }

        return sum % 11 == 0
    }
}

fun main() {
    val verifier = IsbnVerifier()
    println(verifier.isValid("3-598-21508-8")) // true
    println(verifier.isValid("3-598-21507-X")) // true
    println(verifier.isValid("3-598-21507-A")) // false
    println(verifier.isValid("3598215088"))    // true
    println(verifier.isValid("359821507X"))    // true
    println(verifier.isValid("359821507"))     // false
}
