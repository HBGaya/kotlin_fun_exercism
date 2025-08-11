import java.math.BigInteger

object Board {

    fun getGrainCountForSquare(number: Int): BigInteger {
        require(number in 1..64) { "Square must be in between 1 and 64." }
        return BigInteger.TWO.pow(number - 1)
    }

    fun getTotalGrainCount(): BigInteger {
        return BigInteger.TWO.pow(64).subtract(BigInteger.ONE)
    }
}

fun main() {
    println("Grains on square 1 is : ${Board.getGrainCountForSquare(1)}")
    println("Grains on square 2 is : ${Board.getGrainCountForSquare(2)}")
    println("Grains on square 64 is : ${Board.getGrainCountForSquare(64)}")
    println("Grains on board in total : ${Board.getTotalGrainCount()}")
}
