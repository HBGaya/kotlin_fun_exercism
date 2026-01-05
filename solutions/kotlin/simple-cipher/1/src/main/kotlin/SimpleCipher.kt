import kotlin.random.Random

data class Cipher(val key: String = generateRandomKey()) {

    init {
        require(key.isNotEmpty()) { "Key must not be empty" }
        require(key.all { it in 'a'..'z' }) { "Key must contain only lowercase letters" }  
    }

    companion object {
        private fun generateRandomKey(): String =
           (1..100)
             .map { ('a'.code + Random.nextInt(26)).toChar() }
             .joinToString("")
    }

    fun encode(s: String): String {
        val result = StringBuilder()

        for(i in s.indices) {
            val plain = s[i] - 'a'
            val shift = key[i % key.length] - 'a'
            val encoded = (plain + shift) % 26
            result.append(('a'.code + encoded).toChar())
        }

        return result.toString()
    }

    fun decode(s: String): String {
        val result = StringBuilder()

        for(i in s.indices) {
            val cipher = s[i] - 'a'
            val shift = key[i % key.length] - 'a'
            val decoded = (cipher - shift + 26) % 26
            result.append(('a'.code + decoded).toChar())
        }

        return result.toString()
    }
}

fun main() {
    // Step 1: Fixed key like Caesar (key = "dddd")
    val caesar = Cipher("dddd")
    val plaintext1 = "iamapandabear"
    val encoded1 = caesar.encode(plaintext1)
    val decoded1 = caesar.decode(encoded1)
    
    println("=== Caesar Cipher ===")
    println("Plaintext: $plaintext1")
    println("Encoded:   $encoded1")  // should be "ldpdsdqgdehdu"
    println("Decoded:   $decoded1")  // should be "iamapandabear"
    
    // Step 2: Key "aaaa" (no shift)
    val identityCipher = Cipher("aaaa")
    val plaintext2 = "iamapandabear"
    println("\n=== Identity Cipher ===")
    println("Encoded: ${identityCipher.encode(plaintext2)}") // should be same as plaintext
    println("Decoded: ${identityCipher.decode(plaintext2)}") // should be same as plaintext
    
    // Step 3: Random key
    val randomCipher = Cipher()
    val plaintext3 = "hellokotlin"
    val encoded3 = randomCipher.encode(plaintext3)
    val decoded3 = randomCipher.decode(encoded3)
    
    println("\n=== Random Key Cipher ===")
    println("Random key: ${randomCipher.key}")
    println("Plaintext: $plaintext3")
    println("Encoded:   $encoded3")
    println("Decoded:   $decoded3")  // should match plaintext3
}


