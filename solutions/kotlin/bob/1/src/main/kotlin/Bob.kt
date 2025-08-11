object Bob {
    fun hey(input: String): String {
        val trimmedString = input.trim()
        
        return when {
           trimmedString.isEmpty() -> "Fine. Be that way!"
           trimmedString.endsWith("?") && trimmedString == trimmedString.uppercase() && trimmedString.any {it.isLetter()} -> "Calm down, I know what I'm doing!"
           trimmedString.endsWith("?") -> "Sure."
           trimmedString == trimmedString.uppercase() && trimmedString.any {it.isLetter()} -> "Whoa, chill out!"
           else -> "Whatever."
        }
        
    }
}

fun main() {
    println("Let's ask bob: ${Bob.hey("How are you?")}")
    println("I am yelling at Bob: ${Bob.hey("HELLO BOB")}")
    println("I am yelling at Bob: ${Bob.hey("WHAT ARE YOU DOING?")}")
    println("I am angry and will not answer to Bob: ${Bob.hey(" ")}")
    println("Pissded at Bob: ${Bob.hey("Whatever you say.")}")
}
