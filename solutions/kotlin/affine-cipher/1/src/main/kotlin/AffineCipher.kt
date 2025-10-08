object AffineCipher {
    // for English language the M is 26 as it has 26 alphabets 
    private const val M = 26

    fun encode(input: String, a: Int, b: Int): String {
        // check that a key is a prime number to M
        require(isCoprime(a, M)) { "Error: a is not co-prime to 26." }

        val normalizedString = input.lowercase().filter { it.isLetterOrDigit() }

        val encodedString = normalizedString.map { ch ->
            if(ch.isDigit()) ch
            else {
                val i = ch - 'a'
                val encryptedIndex = (a * i + b) % M
                ('a' + encryptedIndex)
            }
        }.joinToString("")

        return encodedString.chunked(5).joinToString(" ")
    }

    fun decode(input: String, a: Int, b: Int): String {
        require(isCoprime(a, M)) { "Error: a is not co-prime to 26." }

        val mmi = modularInverse(a, M)
        val normalizedString = input.lowercase().filter { it.isLetterOrDigit() }

         return normalizedString.map { ch ->
            if(ch.isDigit()) ch
            else {
                val y = ch - 'a'
                val adjusted = ((y - b ) % M + M) % M
                val decrypted = (mmi * adjusted) % M
                ('a' + decrypted)
            }
        }.joinToString("")
    }

    private fun isCoprime(a: Int, m: Int): Boolean {
        fun gcd(x: Int, y: Int): Int = if(y == 0) x else gcd(y, x % y)
        return gcd(a, m) == 1
    }

    private fun modularInverse(a: Int, m:Int): Int {
        for(x in 1 until m) {
            if((a * x) % m == 1) return x
        }

        throw IllegalArgumentException("No modular inverse for a=$a under mod=$m")
    }
}

fun main() {
    println(AffineCipher.encode("test", 5, 7)) 
    // -> ybty

    println(AffineCipher.decode("ybty", 5, 7)) 
    // -> test

    println(AffineCipher.decode("kqlfd jzvgy tpaet icdhm rtwly kqlon ubstx", 19, 13))
    // -> thequickbrownfoxjumpsoverthelazydog

    // This will throw error since 18 is not coprime with 26
    // println(AffineCipher.encode("test", 18, 13))
}
