object RunLengthEncoding {

    fun encode(input: String): String {
        if(input.isEmpty()) {
            return ""
        }

        val substring = StringBuilder()
        var count = 1

        for(i in 1 until input.length) {
            if(input[i] == input[i - 1]) {
                count++
            }
            else {
                if(count > 1) substring.append(count)
                substring.append(input[i - 1])
                count = 1
            }
        }

        if(count > 1) substring.append(count)
        substring.append(input.last())

        return substring.toString()
    }

    fun decode(input: String): String {
        if(input.isEmpty()) return ""

        val substring = StringBuilder()
        var count = ""

        for(ch in input) {
            if(ch.isDigit()) {
                count += ch
            }
            else {
                val repeatCount = if(count.isNotEmpty()) count.toInt() else 1
                repeat(repeatCount) { substring.append(ch) }
                count = ""
            }
        }

        return substring.toString()
    }
}

fun main() {
    println(RunLengthEncoding.encode("AABCCCDEEEE")) // 2AB3CD4E
    println(RunLengthEncoding.decode("2AB3CD4E"))   // AABCCCDEEEE

    println(RunLengthEncoding.encode("WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB"))
    // 12WB12W3B24WB

    println(RunLengthEncoding.decode("12WB12W3B24WB"))
    // WWWWWWWWWWWWBWWWWWWWWWWWWBBBWWWWWWWWWWWWWWWWWWWWWWWWB
}
