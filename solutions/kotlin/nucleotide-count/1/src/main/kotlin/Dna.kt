class Dna(dna: String) {

    private val sequence: String

    init {
        val validNucleotides = setOf('A', 'C', 'G', 'T')
        if(!dna.all { it in validNucleotides }) {
            throw IllegalArgumentException("Invalid DNA Sequence: String consists of non DNA characters")
        }

        sequence = dna
    }

    val nucleotideCounts: Map<Char, Int>
        get() {
            val counts =  sequence.groupingBy { it }.eachCount() 
            return mapOf(
                'A' to counts.getOrDefault('A', 0),
                'C' to counts.getOrDefault('C', 0),
                'G' to counts.getOrDefault('G', 0),
                'T' to counts.getOrDefault('T', 0)
            )
        } 
    
    

}

fun main() {
    val inputDna = Dna("DUFFER")
    
    try {
        println("A: ${inputDna.nucleotideCounts.getValue('A')}")
        println("C: ${inputDna.nucleotideCounts.getValue('C')}")
        println("G: ${inputDna.nucleotideCounts.getValue('G')}")
        println("T: ${inputDna.nucleotideCounts.getValue('T')}")
    }
    catch(e: IllegalArgumentException) {
        println("The error is : ${e.message}")
    }

}
