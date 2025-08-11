// enum class Allergen(val score: Int) {
//     EGGS(1),
//     PEANUTS(2),
//     SHELLFISH(4),
//     STRAWBERRIES(8),
//     TOMATOES(16),
//     CHOCOLATE(32),
//     POLLEN(64),
//     CATS(128)
// }

/// The above enum class is already declared somewhere in exercism so just for an example
/// the enum class may look like this above.

class Allergies(private val score: Int) {
    // TODO: implement proper constructor to complete the task

    fun getList(): List<Allergen> {
        return Allergen.values().filter { (score and it.score) != 0 }
    }

    fun isAllergicTo(allergen: Allergen): Boolean {
        return (score and allergen.score) != 0
    }
}

fun main() {
    val allergies = Allergies(34)

    println("Is allergic to peanuts : ${allergies.isAllergicTo(Allergen.PEANUTS)}")
    println("Is allergic to chocolate : ${allergies.isAllergicTo(Allergen.CHOCOLATE)}")
    println("Is allergic to cats : ${allergies.isAllergicTo(Allergen.CATS)}")

    println("Full list of allergies:")

    allergies.getList().forEach { println(it.name.lowercase().replaceFirstChar() { it.uppercaseChar() }) }
}
