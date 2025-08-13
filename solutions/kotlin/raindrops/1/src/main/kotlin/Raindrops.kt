object Raindrops {

    fun convert(n: Int): String =
        buildString {
            if(n % 3 == 0) append("Pling")
            if(n % 5 == 0) append("Plang")
            if(n % 7 == 0) append("Plong")
        }.ifEmpty {
            n.toString()
        }
}

fun main() {
    println(Raindrops.convert(28))
    println(Raindrops.convert(30))
    println(Raindrops.convert(34))
}
