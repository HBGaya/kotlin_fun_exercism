class RotationalCipher(private val shiftKey: Int) {

    fun encode(text: String): String {
        val shift = shiftKey % 26
        return text.map {
            ch -> when {
                ch.isUpperCase() -> shiftChar(ch, 'A', shift)
                ch.isLowerCase() -> shiftChar(ch, 'a', shift)
                else -> ch
            }
        }.joinToString("")
    }

    private fun shiftChar(c: Char, base: Char, shift: Int): Char {
        val offset = c - base
        val shifted = (offset + shift) % 26

        return (base + shifted)
    }
}

fun main() {
    val rot13 = RotationalCipher(13)
    print(rot13.encode("The quick brown fox jumps over the lazy dog."))
}
