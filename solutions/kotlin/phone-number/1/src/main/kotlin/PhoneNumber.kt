class PhoneNumber(input: String) {

    // TODO: Implement proper constructor

    val number: String? = cleanNumber(input)

    private fun cleanNumber(enteredPhoneNum: String): String? {
        var filterOutDigits = enteredPhoneNum.filter { it.isDigit() }

        if(filterOutDigits.length == 11 && filterOutDigits.startsWith("1")) {
            filterOutDigits = filterOutDigits.drop(1)
        }

        require(filterOutDigits.length == 10) {
            "Invalid phone number length."
        }

        require(filterOutDigits[0] in '2'..'9') {
            "Invalid Area Code."
        }
        
        require(filterOutDigits[3] in '2'..'9') {
            "Invalid Exchange Code."
        }

        return filterOutDigits
    }
}

fun main() {
      
        
        try {
            val phoneNumber = PhoneNumber("+1 (613)-995-0253")
            println(phoneNumber.number)
        }
        catch(e: IllegalArgumentException) {
            println("Error is : ${e.message}")
            
        }
    
}
