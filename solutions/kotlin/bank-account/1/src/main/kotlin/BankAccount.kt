class BankAccount {

    private var _balance: Long = 0
    private var isOpen = true
    private val lock = Any()

    val balance: Long
        get() = synchronized(lock) {
            check(isOpen) { "Account closed" }
            _balance
        }

    fun adjustBalance(amount: Long){
        synchronized(lock) {
            check(isOpen) { "Account closed" }
            _balance += amount
        }
    }

    fun close() {
        synchronized(lock) {
            check(isOpen) { "Account already closed" }
            isOpen = false
        }
    }
}

fun main() {
    val account = BankAccount()

    account.adjustBalance(1000)
    println("Balance after deposit: ${account.balance}")

    account.adjustBalance(-200)
    println("Balance after withdrawal: ${account.balance}")

    account.close()
    try {
        account.adjustBalance(50)
    } catch (e: IllegalStateException) {
        println("Error after closing: ${e.message}")
    }
}
