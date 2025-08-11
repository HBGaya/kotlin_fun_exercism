fun transcribeToRna(dna: String): String = dna.map { neucleotide -> when(neucleotide) {
        'G' -> 'C'
        'C' -> 'G'
        'T' -> 'A'
        'A' -> 'U'
        else -> throw IllegalArgumentException("Invalid DNA nucleotide: $neucleotide")
   }
}.joinToString("")

fun main() {
    val dnaStrand = "GCTA"
    val rnaStrand = transcribeToRna(dnaStrand)

    println("DNA: $dnaStrand")
    println("RNA: $rnaStrand")
}
