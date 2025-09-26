object HandshakeCalculator {
    
    fun calculateHandshake(number: Int): List<Signal> {
        val listOfActions = mutableListOf<Signal>()

        if(number and 0b00001 != 0) listOfActions += Signal.WINK
        if(number and 0b00010 != 0) listOfActions += Signal.DOUBLE_BLINK
        if(number and 0b00100 != 0) listOfActions += Signal.CLOSE_YOUR_EYES
        if(number and 0b01000 != 0) listOfActions += Signal.JUMP
        
        if(number and 0b10000 != 0) listOfActions.reverse()

        return listOfActions
    }
}

fun main() {
    println(HandshakeCalculator.calculateHandshake(9))

    println(HandshakeCalculator.calculateHandshake(26))
}
