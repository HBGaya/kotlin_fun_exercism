object Hamming {

    fun compute(leftStrand: String, rightStrand: String): Int {

        require(leftStrand.length == rightStrand.length) {
            "left and right strands must be of equal length"
        }

        return leftStrand.zip(rightStrand).count { (char1, char2) -> char1 != char2 }
    }
}

fun main() {
    val dna1 = "GAGCCTACTAACGGGAT"
    val dna2 = "CATCGTAATGACGGCCT"

    try {
       val distance = Hamming.compute(dna1, dna2)  
       println("The hamming distance is ${distance}")
    }
    catch (e: IllegalArgumentException){
      println("Error message: ${e.message}")   
    }
 }
