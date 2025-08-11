object Pangram {

    fun isPangram(input: String): Boolean {
        val letters = input.lowercase().filter { it in 'a'..'z' }.toSet()

        return letters.size == 26
    }
}

fun main() {
    val input1 = "The quick brown fox jumps over the lazy dog"
    val input2 = "Hello World!"

    print("Is Pangram? ${Pangram.isPangram(input1)}")
    print("Is Pangram? ${Pangram.isPangram(input2)}")
}
