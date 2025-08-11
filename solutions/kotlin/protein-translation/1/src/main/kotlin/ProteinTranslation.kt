fun translate(rna: String?): List<String> {
   
    val codonMap = mapOf(
        "AUG" to "Methionine",
        "UUU" to "Phenylalanine",
        "UUC" to "Phenylalanine",
        "UUA" to "Leucine",
        "UUG" to "Leucine",
        "UCU" to "Serine", "UCC" to "Serine", "UCA" to "Serine", "UCG" to "Serine",
        "UAU" to "Tyrosine", "UAC" to "Tyrosine",
        "UGU" to "Cysteine", "UGC" to "Cysteine",
        "UGG" to "Tryptophan",
        "UAA" to "STOP", "UAG" to "STOP", "UGA" to "STOP"
    )

    if(rna.isNullOrBlank()) return emptyList()

    val proteins = mutableListOf<String>()
    var i = 0

    while(i + 3 <= rna.length) {
        val codon = rna.substring(i, i + 3)
        var checkForCodon = codonMap[codon] ?: throw IllegalArgumentException("Invalid Codon")

        if(checkForCodon == "STOP") break
        proteins.add(checkForCodon)
        i += 3
    }

    if(i < rna.length && (rna.length - i) < 3) {
        throw IllegalArgumentException("Invalid Codon")
    }

    return proteins
}

fun main() {
    try {
      val result = translate("AUGUUG")

       println(result)
    
    }
    catch(e: IllegalArgumentException) {
        println("error is : ${e.message}")
    }
  
}
