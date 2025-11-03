object MatchingBrackets {

    fun isValid(input: String): Boolean {
        val stack = ArrayDeque<Char>()

        for(ch in input) {
            when(ch) {
                '(', '[', '{' -> stack.addLast(ch)
                ')', ']', '}' -> {
                    if(stack.isEmpty()) return false

                    val top = stack.removeLast()

                    if((ch == ')' && top != '(') || (ch == ']' && top != '[') || (ch == '}' && top != '{')) {
                        return false
                    }
                }
            }
        }

        return stack.isEmpty()
    }
}

fun main() {
    println(MatchingBrackets.isValid("{what is (42)}?"))  // true
    println(MatchingBrackets.isValid("[text}"))          // false
    println(MatchingBrackets.isValid("([]{})"))          // true
    println(MatchingBrackets.isValid("([)]"))            // false
}
