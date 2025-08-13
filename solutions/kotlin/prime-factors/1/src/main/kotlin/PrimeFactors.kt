object PrimeFactorCalculator {

    fun primeFactors(int: Int): List<Int> {
        var number = int
        val factors = mutableListOf<Int>()
        var divisor = 2

        while(number > 1) {
            while(number % divisor == 0) {
                factors.add(divisor)
                number /= divisor
            }
            divisor++
        }

        return factors
    }

    fun primeFactors(long: Long): List<Long> {
        var number = long
        val factors = mutableListOf<Long>()
        var divisor = 2L

        while(number > 1) {
            while(number % divisor == 0L) {
                factors.add(divisor)
                number /= divisor
            }
            divisor++
        }

        return factors
    }
}


fun main() {
    println(PrimeFactorCalculator.primeFactors(60))
    println(PrimeFactorCalculator.primeFactors(13195L))
}
