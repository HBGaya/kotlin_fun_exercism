import kotlin.math.pow

object Wordy {

    fun answer(input: String): Int {
        val question = input.removeSuffix("?").trim()

        if(!question.startsWith("What is ")) throw IllegalArgumentException("Unknown question")

        var expression = question.removePrefix("What is ").trim()

        if(expression.isBlank()) throw IllegalArgumentException("Invalid Syntax")

        val tokens = expression
                     .replace("multiplied by", "multiplied_by")
                     .replace("divided by", "divided_by")
                     .replace("raised to the", "power_of")
                     .split(" ")
                     .map { it.trim() }
        
        var index = 0
        var result = tokens.getOrNull(index)?.toIntOrNull() ?: throw IllegalArgumentException("Invalid Syntax")
        index++

        while(index < tokens.size) {
            val op = tokens[index]
            index++

            if(index >= tokens.size) throw IllegalArgumentException("Invalid Syntax")
            val nextToken = tokens[index]
            index++

            val number: Int = when(op) {
                "power_of" -> {
                    val ord = parseOrdinal(nextToken)

                    if(index < tokens.size && tokens[index] == "power") {
                        index ++
                    }

                    ord
                }    
                else -> nextToken.toIntOrNull() ?: throw IllegalArgumentException("Invalid Syntax")
            } 

            result = when(op) {
                "plus" -> result + number
                "minus" -> result - number
                "multiplied_by" -> result * number
                "divided_by" -> result / number
                "power_of" -> result.toDouble().pow(number).toInt()
                else -> throw IllegalArgumentException("Unsupported operation")
            }
        }

        return result             
    }

    // Extract number from ordinal words ("5th" → 5)
    private fun parseOrdinal(ordinal: String): Int {
        return ordinal.dropLast(2).toIntOrNull()
            ?: throw IllegalArgumentException("Invalid ordinal")
    }
}

fun main() {
        println(Wordy.answer("What is 5?"))                       // 5
    println(Wordy.answer("What is 5 plus 13?"))               // 18
    println(Wordy.answer("What is 7 minus 5?"))               // 2
    println(Wordy.answer("What is 6 multiplied by 4?"))       // 24
    println(Wordy.answer("What is 25 divided by 5?"))         // 5
    println(Wordy.answer("What is 5 plus 13 plus 6?"))        // 24
    println(Wordy.answer("What is 3 plus 2 multiplied by 3?"))// 15

    // Testing errors
    try {
        println(Wordy.answer("What is 52 cubed?"))
    } catch (e: Exception) {
        println("Error: ${e.message}")                       // Unsupported operation
    }

    try {
        println(Wordy.answer("Who is the President?"))
    } catch (e: Exception) {
        println("Error: ${e.message}")                        // Unknown question
    }

    try {
        println(Wordy.answer("What is 1 plus plus 2?"))
    } catch (e: Exception) {
        println("Error: ${e.message}")                        // Invalid syntax
    }
}
