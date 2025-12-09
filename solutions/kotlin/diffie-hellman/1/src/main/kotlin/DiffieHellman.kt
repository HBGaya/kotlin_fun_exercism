import java.math.BigInteger
import java.security.SecureRandom

object DiffieHellman {

    private val random = SecureRandom()

    fun privateKey(prime: BigInteger): BigInteger {
        var key: BigInteger
        do {
            key = BigInteger(prime.bitLength(), random)
        } while(key <= BigInteger.ONE || key >= prime)

        return key
    }

    fun publicKey(p: BigInteger, g: BigInteger, privKey: BigInteger): BigInteger {
        return g.modPow(privKey, p)
    }

    fun secret(prime: BigInteger, publicKey: BigInteger, privateKey: BigInteger): BigInteger {
        return publicKey.modPow(privateKey, prime)
    }
}

fun main() {
    val p = BigInteger("23")
    val g = BigInteger("5")

    val a = DiffieHellman.privateKey(p)
    val b = DiffieHellman.privateKey(p)

    val A = DiffieHellman.publicKey(p, g, a)
    val B = DiffieHellman.publicKey(p, g, b)

    val s1 = DiffieHellman.secret(p, B, a)
    val s2 = DiffieHellman.secret(p, A, b)

    println("Alice Secret: $s1")
    println("Bob Secret: $s2")
}
