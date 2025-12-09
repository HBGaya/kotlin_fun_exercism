class Forth {

    private val stack = ArrayDeque<Int>()
    private val words = mutableMapOf<String, List<String>>()

    fun evaluate(vararg line: String): List<Int> {
        val tokens = line
                     .joinToString(" ")
                     .lowercase()
                     .split(Regex("\\s+"))
                     .filter { it.isNotBlank() }
       
        parseTokens(tokens)
        return stack.toList()                      
    }

    private fun parseTokens(tokens: List<String>) {
        var i = 0
        while(i < tokens.size) {
            val token = tokens[i]

            when {
                token == ":" -> {
                    i = defineNewWord(tokens, i + 1)
                }

                token.toIntOrNull() != null -> {
                    stack.addLast(token.toInt())
                    i++
                }

                words.containsKey(token) -> {
                    val expansion = words[token]!!
                    parseTokens(expansion)
                    i++
                }

                else -> {
                    executeBuiltin(token)
                    i++
                }
            }   
        }
    }

    private fun defineNewWord(tokens: List<String>, startIndex: Int): Int {
        if(startIndex >= tokens.size) throw IllegalArgumentException("Invalid definition")

        val name = tokens[startIndex]
        if(name.toIntOrNull() != null) throw IllegalArgumentException("Cannot redefine numbers")

        val definition = mutableListOf<String>()
        var i = startIndex + 1

        while(i <= tokens.size && tokens[i] != ";") {
            val t = tokens[i]
            definition.addAll(
                words[t] ?: listOf(t)
            )
            i++
        }

        if(i == tokens.size) throw IllegalArgumentException("Missing semicolon")
        words[name] = definition
        return i + 1
    }

    private fun executeBuiltin(word: String) {
        when(word) {
            "+" -> binaryOp {a, b -> a + b}
            "-" -> binaryOp {a, b -> a - b}
            "*" -> binaryOp {a, b -> a * b}
            "/" -> {
                val (a, b) = popTwo()
                if(b == 0) throw IllegalArgumentException("Division by zero")
                stack.addLast(a / b)
            }

            "dup" -> {
                val v = stack.lastOrNull() ?: throw IllegalArgumentException("Stack underflow")
                stack.addLast(v)
            }

            "drop" -> {
                if(stack.isEmpty()) throw IllegalArgumentException("Stack underflow")
                stack.removeLast()
            }

            "swap" -> {
                val (a, b) = popTwo()
                stack.addLast(b)
                stack.addLast(a)
            }

            "over" -> {
                if(stack.size < 2) throw IllegalArgumentException("Stack underflow")
                val v = stack[stack.size - 2]
                stack.addLast(v)
            }

            else -> throw IllegalArgumentException("Unknown word: $word")
        }
    }

    private fun binaryOp(op: (Int, Int) -> Int) {
        val (a, b) = popTwo()
        stack.addLast(op(a, b))
    }

    private fun popTwo(): Pair<Int, Int> {
        if(stack.size < 2) throw IllegalArgumentException("Stack underflow")
        val b = stack.removeLast()
        val a = stack.removeLast()
        return a to b
    }
}

fun main() {
    val forth = Forth()
println(forth.evaluate("1 2 +"))        // [3]

println(forth.evaluate(
    ": square dup * ;",
    "4 square"
)) // [16]
}
