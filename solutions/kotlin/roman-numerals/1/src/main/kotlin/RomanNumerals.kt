object RomanNumerals {

    fun value(n: Int): String {
        require(n in 1..3999) { "Roman numerals can only represent 1-3999" }

        val numerals = listOf(
            1000 to "M",
            900 to "CM",
            500 to "D",
            400 to "CD",
            100 to "C",
            90 to "XC",
            50 to "L",
            40 to "XL",
            10 to "X",
            9 to "IX",
            5 to "V",
            4 to "IV",
            1 to "I"
         ) 

        var number = n
        val result = StringBuilder()

        for((value, symbol) in numerals) {
            while(number >= value) {
                result.append(symbol)
                number -= value
            }
        }

        return result.toString()
    }
}

fun main() {
    println(RomanNumerals.value(4))     // IV
    println(RomanNumerals.value(9))     // IX
    println(RomanNumerals.value(58))    // LVIII
    println(RomanNumerals.value(1996))  // MCMXCVI
    println(RomanNumerals.value(3999))  // MMMCMXCIX
}

