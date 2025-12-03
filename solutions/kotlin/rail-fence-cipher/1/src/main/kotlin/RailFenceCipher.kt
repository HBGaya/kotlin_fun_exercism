class RailFenceCipher(private val rails: Int) {

    fun getEncryptedData(input: String): String {
        if(rails == 1) return input

        val fence = Array(rails) { StringBuilder() }
        var row = 0
        var direction = 1

        for(ch in input) {
            fence[row].append(ch)

            if(row == 0) direction = 1
            else if(row == rails -1) direction = -1

            row += direction
        }

        return fence.joinToString("") { it.toString() }
    }

    fun getDecryptedData(input: String): String {
        if(rails == 1) return input

        val len = input.length
        val pattern = Array(rails) { CharArray(len) { '\u0000' } }

        var row = 0
        var direction = 1
        for (i in 0 until len) {
            pattern[row][i] = '*'

            if(row == 0) direction = 1
            else if(row == rails - 1) direction = -1

            row += direction
        }

        var index = 0
        for (r in 0 until rails) {
            for (c in 0 until len) {
                if(pattern[r][c] == '*' && index < len) {
                    pattern[r][c] = input[index++]
                }
            }
        }

        val result = StringBuilder()
        row = 0
        direction = 1

        for(i in 0 until len) {
            result.append(pattern[row][i])

            if(row == 0) direction = 1
            else if(row == rails - 1) direction = -1

            row += direction
            
        }
        
        return result.toString()
     }
}

fun main() {
        val cipher = RailFenceCipher(3)

    val encrypted = cipher.getEncryptedData("WEAREDISCOVEREDFLEEATONCE".replace(" ", ""))
    println(encrypted)  // WECRLTEERDSOEEFEAOCAIVDEN

    val decrypted = cipher.getDecryptedData(encrypted)
    println(decrypted)  // WEAREDISCOVEREDFLEEATONCE
}
