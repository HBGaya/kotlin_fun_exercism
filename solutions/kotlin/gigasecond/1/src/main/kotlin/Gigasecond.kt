import java.time.LocalDateTime
import java.time.LocalDate

class Gigasecond {

    companion object {
        private const val GIGASECOND = 1_000_000_000L 
    }

    val date: LocalDateTime

    constructor(start: LocalDate) {
        date = start.atStartOfDay().plusSeconds(GIGASECOND)
    }

    constructor(start: LocalDateTime) {
        date = start.plusSeconds(GIGASECOND)
    }
}

fun main() {
    val input = LocalDate.of(2015, 1, 24)
    val gigaSecond = Gigasecond(input)
    println("Gigasecond anniversary: ${gigaSecond.date}")
}
