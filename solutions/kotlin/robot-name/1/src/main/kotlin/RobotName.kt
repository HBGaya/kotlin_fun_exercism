import kotlin.random.Random

class Robot {

    companion object {
        private val usedNames = mutableSetOf<String>()

        private fun generateUniqueNames(): String {
            var name: String
            do {
                name = buildString {
                    repeat(2) {
                        append(('A'..'Z').random())
                    }
                    repeat(3) {
                        append(('0'..'9').random())
                    }
                }  
            } while (!usedNames.add(name))
            return name
        }
    }

   private var _name: String? = null

    val name: String
        get() {
            if (_name == null) {
                _name = generateUniqueNames()
            }
            return _name!!
        }

    fun reset() {
        // Free up the current name (optional; Exercism doesn’t require reuse)
        _name?.let { usedNames.remove(it) }
        _name = null
    }
}

fun main() {
    val r1 = Robot()
    println(r1.name) // e.g. "RX837"
    println(r1.name) // same name again

    r1.reset()
    println(r1.name) // new name, e.g. "QZ492"

    val r2 = Robot()
    println(r2.name) // unique name, not same as r1
}
