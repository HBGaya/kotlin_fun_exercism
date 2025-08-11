import kotlin.math.*

object CryptoSquare {

    fun ciphertext(plaintext: String): String {
        val normalizedString = plaintext.filter { it.isLetterOrDigit() }.lowercase()

        if(normalizedString.isEmpty()) return ""
        
        val stringLength = normalizedString.length

        var r = 0
        var c = 0

        for(cols in 1..stringLength) {
            val rows = (stringLength + cols - 1) / cols
            if(cols >= rows && (cols - rows) <= 1) {
                r = rows
                c = cols
                break
            }
        }

        val rows = normalizedString.chunked(c)
        val columns = MutableList(c) { StringBuilder() }

        for(row in rows) {
            row.forEachIndexed { index, char -> 
                columns[index].append(char)                
            }
        }

        val padded = columns.map {
            it.padEnd(r, ' ')
        }

        return padded.joinToString(" ")
    }

}

fun main() {
    val textToBeCipher = "If man was meant to stay on the ground, god would have given us roots."
    val cipheredText = CryptoSquare.ciphertext(textToBeCipher)

    println(cipheredText)
}
