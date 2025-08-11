fun reverse(input: String): String {
    var reversedString = ""
    for(i in input.length - 1 downTo 0) {
        reversedString += input[i]
    }

    return reversedString
}

fun main() {
    println("The reverse of desserts is: ${reverse("desserts")}")
    println("The reverse of strops is: ${reverse("strops")}")
    println("The reverse of racecar is: ${reverse("racecar")}")
}
