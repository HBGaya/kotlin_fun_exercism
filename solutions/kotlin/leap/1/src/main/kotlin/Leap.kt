data class Year(val value: Int) {


    val isLeap: Boolean get() = (value % 4 == 0 && value % 100 != 0) || (value % 400 == 0)  
}

fun main() {
    val years = listOf(Year(1997), Year(1900), Year(2000), Year(2024))

    for(year in years) {
        println("The ${year.value} is a leap year: ${year.isLeap}")
    }
}
