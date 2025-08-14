import kotlin.math.pow

object ResistorColorTrio {

    fun text(vararg input: Color): String {
        require(input.size == 3) { "Exactly three colors are required" }

        val value = (input[0].ordinal * 10 + input[1].ordinal) * 10.0.pow(input[2].ordinal).toLong()

        return when {
            value >= 1_000_000 -> "${value / 1_000_000} megaohms"
            value >= 1_000 -> "${value / 1_000} kiloohms"
            else -> "$value ohms"
        }
    }
}

fun main() {
    println(ResistorColorTrio.text(Color.ORANGE, Color.ORANGE, Color.BLACK))
    println(ResistorColorTrio.text(Color.ORANGE, Color.ORANGE, Color.RED))
    println(ResistorColorTrio.text(Color.ORANGE, Color.ORANGE, Color.ORANGE))
}