object Prime {

    fun nth(n: Int): Int {
        require(n > 0) { "There is no zeroth prime." }

        var count = 0
        var number = 1

        while(count < n) {
            number++
            if(isPrime(number)) {
                count++
            }
        }

        return number
    }

    private fun isPrime(num: Int): Boolean {
        if(num < 2) return false
        if(num == 2) return true
        if(num % 2 == 0) return false

        val limit = Math.sqrt(num.toDouble()).toInt()
        for(i in 3..limit step 2) {
            if(num % i == 0) return false
        }

        return true
    }
}

fun main() {
    println(Prime.nth(1)) // 2
    println(Prime.nth(2)) // 3
    println(Prime.nth(6)) // 13
}
