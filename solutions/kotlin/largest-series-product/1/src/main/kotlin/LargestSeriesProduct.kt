class Series(private val digits: String) {

    init {
        require(digits.all { it.isDigit() }) { "Input must contain only digits" }
    }

    fun getLargestProduct(span: Int): Long {
        require(span >= 0) { "span must be non-negative" }
        require(span <= digits.length) { "span must be equal or less than digits length" }

        if(span == 0) return 1

        var maxProduct = 0L
        for(i in 0..digits.length - span) {
            val product = digits.substring(i, i + span)
                                .map { it.digitToInt().toLong() }
                                .reduce { acc, n -> acc * n }
           if(product > maxProduct) {
               maxProduct = product
           }                       
        }

        return maxProduct
    }
}

fun main() {
    val series = Series("63915")
    println(series.getLargestProduct(3))  // Output: 162
}
