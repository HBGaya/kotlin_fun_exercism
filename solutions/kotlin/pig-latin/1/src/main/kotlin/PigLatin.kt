object PigLatin {

    private val vowelSet = setOf('a', 'e', 'i', 'o', 'u')

    fun translate(phrase: String): String {
        val wordRegex = Regex("([A-Za-z]+)")
        return wordRegex.replace(phrase) { match ->
            translatePreserveCase(match.value)
        }
    }

    private fun translatePreserveCase(word: String): String {
        val isAllUpper = word == word.uppercase()
        val isCapitalized = word.length > 0 && word[0].isUpperCase() && word.drop(1) == word.drop(1).lowercase()

        val lower = word.lowercase()
        val translatedLower = translateWord(lower)
        
        return when {
            isAllUpper -> translatedLower.uppercase()
            isCapitalized -> translatedLower.replaceFirstChar { 
    if (it.isLowerCase()) it.titlecase() else it.toString()
}
            else -> translatedLower
        }
    }

    private fun translateWord(word: String): String {
        if(word.isEmpty()) return word
        if(word.startsWith("xr") || word.startsWith("yt") || vowelSet.contains(word[0])) {
            return word + "ay"
        }

        var i = 0
        val n = word.length
        
        while(i < n) {
      // Handle 'qu' as part of the consonant cluster
        if (i < n - 1 && word.substring(i, i + 2) == "qu") {
            i += 2
            continue
        }
            val ch = word[i]

            val isVowelHere = vowelSet.contains(ch) || (ch == 'y' && i != 0)
            if(isVowelHere) break
            i++
        }

        if(i < n - 1 && word.substring(i, i + 2) == "qu") {
            val head = word.substring(0, i + 2)
            val tail = word.substring(i + 2)

            return tail + head + "ay"
        }

        if(i == 0) {
            return word + "ay"
        }
        else if(i >= n) {
            return word + "ay"
        }

        val head = word.substring(0, i)
        val tail = word.substring(i)

        return tail + head + "ay"
    }
}

fun main() {
    println(PigLatin.translate("apple"))           // appleay
    println(PigLatin.translate("xray"))            // xrayay
    println(PigLatin.translate("chair"))           // airchay
    println(PigLatin.translate("quick"))           // ickquay
    println(PigLatin.translate("square"))          // aresquay
    println(PigLatin.translate("my"))              // ymay
    println(PigLatin.translate("rhythm"))          // ythmrhay
    println(PigLatin.translate("Hello, world!"))   // Ellohay, orldway!
    println(PigLatin.translate("Yttria and Xray")) // yttriaay and xrayay
    println(PigLatin.translate("QUick"))           // ICKQUAY  (preserves all-caps)
    println(PigLatin.translate("Chair"))           // Airchay  (preserves capitalized)
}

