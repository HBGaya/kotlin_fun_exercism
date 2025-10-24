class BinarySearchTree<T : Comparable<T>> {

    data class Node<T>(
        val data: T,
        var left: Node<T>? = null,
        var right: Node<T>? = null
    )

    var root: Node<T>? = null


    private fun insertRecursive(current: Node<T>?, value: T): Node<T> {
        current ?: return Node(value)

        if(value <= current.data) {
            current.left = insertRecursive(current.left, value)
        }
        else {
            current.right = insertRecursive(current.right, value)
        }

        return current
    }

    fun insert(value: T) {
        root = insertRecursive(root, value)
    }

    private fun inOrder(node: Node<T>?, result: MutableList<T>) {
        if(node == null) return
        inOrder(node.left, result)
        result.add(node.data)
        inOrder(node.right, result)
    }

    fun asSortedList(): List<T> {
        val sortedList = mutableListOf<T>()
        inOrder(root, sortedList)
        return sortedList
    }

    fun asLevelOrderList(): List<T> {
        val result = mutableListOf<T>()
        val queue: ArrayDeque<Node<T>> = ArrayDeque()

        root?.let { queue.add(it) }

        while(queue.isNotEmpty()) {
            val current = queue.removeFirst()
            result.add(current.data)

            current.left?.let { queue.add(it) }
            current.right?.let { queue.add(it) }
        }

        return result
    }

}

fun main() {
    val bst = BinarySearchTree<Int>()
    listOf(4, 2, 6, 3, 1, 5, 7).forEach { bst.insert(it) }

    println("Sorted (In-Order): ${bst.asSortedList()}")
    println("Level Order: ${bst.asLevelOrderList()}")
}
