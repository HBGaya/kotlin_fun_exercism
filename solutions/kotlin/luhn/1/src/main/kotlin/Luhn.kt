object Luhn {

    fun isValid(candidate: String): Boolean {
        val cleanedString = candidate.replace(" ",  "")

        if(cleanedString.length <= 1 || !cleanedString.all { it.isDigit() }) return false

        var sum = cleanedString.reversed().mapIndexed {
            index, ch -> 
            var digit = ch.digitToInt()
            if(index % 2 == 1) {
                digit *= 2
                if(digit > 9) digit -= 9
            }

            digit
        }.sum()

        return sum % 10 == 0
    }
}
fun main() {
    println(Luhn.isValid("4539 3195 0343 6467")) // true
    println(Luhn.isValid("8273 1232 7352 0569")) // false
    println(Luhn.isValid("055 444 285"))         // true
    println(Luhn.isValid("055a 444 285"))        // false
}
