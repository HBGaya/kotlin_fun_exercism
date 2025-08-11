
enum class Classification {
    DEFICIENT, PERFECT, ABUNDANT
}

fun classify(naturalNumber: Int): Classification {

    require(naturalNumber > 0) {
        "Number must be a positive integer"
    }

    val aliquotSum = (1 until naturalNumber).filter { naturalNumber % it == 0 }.sum()

    return when {
        aliquotSum == naturalNumber -> Classification.PERFECT
        aliquotSum > naturalNumber -> Classification.ABUNDANT
        else -> Classification.DEFICIENT
    }
}

fun main() {
    val numbers = listOf(6, 12, 8, 28, 24, 13)

    for(num in numbers) {
        println("$num is ${classify(num)}")
    }
}
