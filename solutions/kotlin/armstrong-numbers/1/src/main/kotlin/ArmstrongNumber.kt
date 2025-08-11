object ArmstrongNumber {

    fun check(input: Int): Boolean {
        val digits = input.toString().map { it.toString().toInt() }
        val power = digits.size
        val sum = digits.sumOf { Math.pow(it.toDouble(), power.toDouble()).toInt() }

        return sum == input
    }

}

fun main() {
    val testNumber = listOf(9, 10, 153, 154)

    for(i in testNumber) {
        println("$i is${if (!ArmstrongNumber.check(i)) " not" else ""} an armstrong number")
    }
}
