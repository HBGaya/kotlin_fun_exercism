class Deque<T> {

    private class Node<T>(
        var value: T,
        var prev: Node<T>? = null,
        var next: Node<T>? = null
    )

    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    fun push(value: T) {
        val newNode = Node(value)

        if(tail == null) {
            head = newNode
            tail = newNode
        }
        else {
            tail!!.next = newNode
            newNode.prev = tail
            tail = newNode
        }
    }

    fun pop(): T? {
        val oldTail = tail ?: return null
        val value = oldTail.value

        tail = oldTail.prev
        if(tail == null){
            head == null
        }
        else {
            tail!!.next = null
        }

        return value
    }

    fun unshift(value: T) {
        val newNode = Node(value)

        if(tail == null) {
            head = newNode
            tail = newNode
        }
        else {
            newNode.next = head
            head!!.prev = newNode
            head = newNode
        }
    }

    fun shift(): T? {
        val oldHead = head ?: return null
        val value = oldHead.value

        head = oldHead.next
        if(head == null){
            tail == null
        }
        else {
            head!!.prev = null
        }

        return value
    }
}

fun main() {
    val deque = Deque<Int>()
    deque.push(1)      // tail: 1
    deque.push(2)      // tail: 2
    deque.unshift(0)   // head: 0

    println(deque.shift()) // 0
    println(deque.pop())   // 2
    println(deque.pop()) 
}
