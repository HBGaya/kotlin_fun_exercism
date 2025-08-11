class Anagram(private val target: String) {

    private fun normalizeString(word: String): String {
        return word.lowercase().toCharArray().sorted().joinToString("")
    }

    private val normalizedTarget = normalizeString(target)

    fun match(anagrams: Collection<String>): Set<String> {
        return anagrams.filter {
            val candidate = it
            val normalizedCandidate = normalizeString(candidate)

            candidate.lowercase() != target.lowercase() && normalizedCandidate == normalizedTarget
        }.toSet()
    }
}

fun main() {
    val anagram = Anagram("Stone")
    val candidates = listOf("stone", "tones", "banana", "tons", "notes", "Seton")
    val result = anagram.match(candidates)

    print("Anagrams of Stone are: $result")
}
