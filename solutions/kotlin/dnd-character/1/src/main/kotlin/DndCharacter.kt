import kotlin.random.Random
import kotlin.math.floor

class DndCharacter {

    val strength: Int = ability()
    val dexterity: Int = ability()
    val constitution: Int = ability()
    val intelligence: Int = ability()
    val wisdom: Int = ability()
    val charisma: Int = ability()

    var hitpoints: Int = 10 + modifier(constitution) 

    companion object {

        fun ability(): Int {
            val diceRolls = List(4) { Random.nextInt(1, 7) }
            return diceRolls.sortedDescending().take(3).sum()
        }

        fun modifier(score: Int): Int {
            return floor((score - 10) / 2.0).toInt()
        }
    }
}

fun main() {
    val character = DndCharacter()
    println("Strenght: ${character.strength}")
    println("Dexterity: ${character.dexterity}")
    println("Constitution: ${character.constitution}")
    println("Intelligence: ${character.intelligence}")
    println("Wisdom: ${character.wisdom}")
    println("Charisma: ${character.charisma}")
    println("Hit points: ${character.hitpoints}")
}
