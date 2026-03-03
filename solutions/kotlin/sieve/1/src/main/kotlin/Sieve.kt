object Sieve {

    fun primesUpTo(upperBound: Int): List<Int> {
        if(upperBound < 2) return emptyList()

        val isPrime = BooleanArray(upperBound + 1) { true }

        isPrime[0] = false
        isPrime[1] = false

        for(i in 2..Math.sqrt(upperBound.toDouble()).toInt()) {
            if(isPrime[i]) {
                for(j in i * i..upperBound step i) {
                    isPrime[j] = false
                }
            }
        }
        
        val primes = mutableListOf<Int>()
        for(i in 2..upperBound) {
            if(isPrime[i]) {
                primes.add(i)
            }
        }

        return primes
    }
}

fun main() {
    println(Sieve.primesUpTo(10)) // Output: [2, 3, 5, 7]
}
