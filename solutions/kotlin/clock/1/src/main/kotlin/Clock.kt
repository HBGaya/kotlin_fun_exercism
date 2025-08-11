class Clock(private var hours: Int, private var minutes: Int) {

    init {
        normalizeTime()
    }

    private fun normalizeTime() {
        val totalMinutes = (hours * 60 + minutes).floorMod(24 * 60)
        hours = totalMinutes / 60
        minutes = totalMinutes % 60
    }

    private fun Int.floorMod(mod: Int): Int {
        var result = this % mod
        return if(result < 0) {
            result + mod
        }
        else {
            result
        } 
    }

    fun subtract(minutes: Int) {
        this.minutes -= minutes
        normalizeTime()
    }

    fun add(minutes: Int) {
        this.minutes += minutes
        normalizeTime()
    }

    override fun toString(): String = "%02d:%02d".format(hours, minutes)

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(other !is Clock) return false
        return this.hours == other.hours && this.minutes == other.minutes
    }

    override fun hashCode(): Int = 31 * hours + minutes
}

fun main() {
    val clock1 = Clock(10, 30)
    clock1.add(90)

    println("The time is $clock1")

    val clock2 =  Clock(10, 30)
    clock2.subtract(90)

    println("The time is $clock2")

    println(clock1 == clock2)
}
