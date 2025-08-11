class Triangle<out T : Number>(val a: T, val b: T, val c: T) {

    private val aSide = a.toDouble() 
    private val bSide = b.toDouble() 
    private val cSide = c.toDouble()

    init {
        require(aSide > 0 && bSide > 0 && cSide > 0) {
            "All sides must be greater that zero"
        }

        require(aSide + bSide >= cSide && bSide + cSide >= aSide && cSide + aSide >= bSide) {
            "Triangle inequality violation: sum of any 2 sides must be >= 3rd side"
        }
    }

    val isEquilateral: Boolean get() =  aSide == bSide && bSide == cSide
    val isIsosceles: Boolean get() =  aSide == bSide || bSide == cSide 
                                                   || aSide == cSide
    val isScalene: Boolean get() = aSide != bSide && bSide != cSide && aSide != cSide
}

fun main() {
    try {
        val t1 = Triangle(5, 5, 5)
        println("The triangle 1 is equilateral: ${t1.isEquilateral}")
        println("The triangle 1 is isoceles: ${t1.isIsosceles}")
        println("The triangle 1 is scalene: ${t1.isScalene}\n") 
    }
    catch(e: IllegalArgumentException) {
        println("The error is : $e")
    }

    try {
       val t3 = Triangle(5, 6, 7)
       println("The triangle 3 is equilateral: ${t3.isEquilateral}")
       println("The triangle 3 is isoceles: ${t3.isIsosceles}")
       println("The triangle 3 is scalene: ${t3.isScalene}\n")
    }
    catch(e: IllegalArgumentException) {
        println("The error is : $e")
    }

    try {
        val t2 = Triangle(5, 5, 8)
        println("The triangle 2 is equilateral: ${t2.isEquilateral}")
        println("The triangle 2 is isoceles: ${t2.isIsosceles}")
        println("The triangle 2 is scalene: ${t2.isScalene}\n")
    }
    catch(e: IllegalArgumentException) {
        println("The error is : $e")
    }
 



}
