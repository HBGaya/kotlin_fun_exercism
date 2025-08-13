object ResistorColor {

    private val colors = listOf(
        "black", "brown", "red", "orange", "yellow", "green",
        "blue", "violet", "grey", "white"
    )

    fun colorCode(input: String): Int {
        return colors.indexOf(input.lowercase()) 
    }

    fun colors(): List<String> {
        return colors
    }

}

fun main() {
    println("List of all colors: ${ResistorColor.colors()}")
    println("The value of green is ${ResistorColor.colorCode("Green")}")
}
