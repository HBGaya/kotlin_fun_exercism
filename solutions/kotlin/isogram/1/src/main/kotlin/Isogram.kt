object Isogram {

    fun isIsogram(input: String): Boolean {
        val alreadyInCollection = mutableSetOf<Char>()

        for(char in input.lowercase()) {
            if(char.isLetter()) {
                if(!alreadyInCollection.add(char)) {
                    return false
                }
            }
        }

        return true
    }
}

fun main() {
    println("This word 'hello world' is an isogram is ${Isogram.isIsogram("hello world")}")
    println("This word 'lumberjacks' is an isogram is ${Isogram.isIsogram("lumberjacks")}")
    println("This word 'background' is an isogram is ${Isogram.isIsogram("background")}")
    println("This word 'downstream' is an isogram is ${Isogram.isIsogram("downstream")}")
    println("This word 'six-year-old' is an isogram is ${Isogram.isIsogram("six-year-old")}")
}
