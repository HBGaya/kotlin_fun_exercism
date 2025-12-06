class NumberSpeller {

    private val units = listOf(
        "zero", "one", "two", "three", "four", "five",
        "six", "seven", "eight", "nine", "ten", "eleven",
        "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
        "seventeen", "eighteen", "nineteen"
    )

    private val tens = listOf(
        "", "", "twenty", "thirty", "forty",
        "fifty", "sixty", "seventy", "eighty", "ninety"
    )

    private val scales = listOf(
        "", "thousand", "million", "billion"
    )

    fun say(input: Long): String {
        if(input < 0 || input > 999_999_999_999) throw IllegalArgumentException("Number out of range")

        if(input == 0L) return "zero"

        val chunks = splitIntoThousands(input)
        val words = chunks.mapIndexed { index, chunk ->
            if(chunk == 0) ""
            else "${sayUnder1000(chunk)} ${scales[index]}"
        }.filter { it.isNotBlank() }

        return words.reversed().joinToString(" ").trim()
    }

    private fun splitIntoThousands(number: Long): List<Int> {
        val res = mutableListOf<Int>()
        var n = number

        while(n > 0) {
            res += (n % 1000).toInt()
            n /= 1000
        }

        return res
    }

    private fun sayUnder1000(n: Int): String {
        val parts = mutableListOf<String>()

        val hundred = n / 100
        val rest = n % 100

        if(hundred > 0) parts += "${units[hundred]} hundred"
        if(rest > 0) parts += sayUnder100(rest)

        return parts.joinToString(" ")
    }

    private fun sayUnder100(n: Int): String {
        return when {
            n < 20 -> units[n]
            n % 10 == 0 -> tens[n / 10]
            else -> "${tens[n / 10]}-${units[n % 10]}"
        }
    }
}

fun main() {
    println(NumberSpeller().say(0))          // zero
    println(NumberSpeller().say(14))         // fourteen
    println(NumberSpeller().say(50))         // fifty
    println(NumberSpeller().say(98))         // ninety-eight
    println(NumberSpeller().say(100))        // one hundred
    println(NumberSpeller().say(12345))      // twelve thousand three hundred forty-five
    println(NumberSpeller().say(1234567890))
}
