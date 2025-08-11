class Squares(private val n: Int) {
    //TODO: implement proper constructor

    fun sumOfSquares(): Int {
        return n * ( n + 1) * (2 * n + 1) / 6 
    }

    fun squareOfSum(): Int {
        val sum = n * (n + 1) / 2
        return sum * sum
    }

    fun difference(): Int {
        return squareOfSum() - sumOfSquares()
    }
}

fun main() {
    val squares = Squares(10)
    println("Square of sums: ${squares.squareOfSum()}")
    println("Sum of squares: ${squares.sumOfSquares()}")
    println("The difference is : ${squares.difference()}")
}
