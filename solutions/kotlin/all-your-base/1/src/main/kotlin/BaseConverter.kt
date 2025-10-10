class BaseConverter(private val base: Int, private val digits: IntArray) {

    init {
        require(base >= 2) { "Bases must be at least 2." }
        require(digits.isNotEmpty()) { "You must supply at least one digit." }
        require(digits.all { it >= 0 }) { "Digits may not be negative." }
        require(digits.all { it < base }) { "All digits must be strictly less than the base." }

        // 💡 New check: no leading zeros allowed unless the number is zero itself
        if (digits.size > 1 && digits.first() == 0) {
            throw IllegalArgumentException("Digits may not contain leading zeros.")
        }
    }

    

    fun convertToBase(newBase: Int): IntArray {
        require(newBase >= 2) { "Bases must be at least 2." }

        var decimal = 0
        for(digit in digits) {
            decimal = decimal * base + digit
        }

        if(decimal == 0) return intArrayOf(0)

        val result = mutableListOf<Int>()
        var value = decimal


        while(value > 0) {
            val remainder = value % newBase
            result.add(remainder)
            value /= newBase
        }

        return result.reversed().toIntArray()
    }
}

fun main() {
     val base3 = BaseConverter(3, intArrayOf(1, 1, 2, 0))
    println(base3.convertToBase(10).joinToString()) // 4, 2

    val base2 = BaseConverter(2, intArrayOf(1, 0, 1, 0, 1, 0))
    println(base2.convertToBase(10).joinToString()) // 4, 2

    val base10 = BaseConverter(10, intArrayOf(4, 2))
    println(base10.convertToBase(2).joinToString()) // 1, 0, 1, 0, 1, 0
}
