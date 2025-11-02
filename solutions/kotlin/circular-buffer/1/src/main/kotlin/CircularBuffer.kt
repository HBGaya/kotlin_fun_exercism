import kotlin.collections.ArrayDeque

class EmptyBufferException: Exception("Circular buffer is empty.")

class BufferFullException: Exception("Circular buffer is full.")

class CircularBuffer<T>(private val capacity: Int) {
    private val buffer = ArrayDeque<T>(capacity)

    fun read() : T {
        if(buffer.isEmpty()) {
            throw EmptyBufferException()
        }
        return buffer.removeFirst()
    }

    fun write(value: T) {
        if(buffer.size == capacity) {
            throw BufferFullException()
        }
        
        buffer.addLast(value)
    }

    fun overwrite(value: T) {
        if(buffer.size == capacity) {
            buffer.removeFirst()
        }
        
        buffer.addLast(value)
    }

    fun clear() {
        buffer.clear()
    }
}

fun main() {
    val buffer = CircularBuffer<Int>(3)

    buffer.write(1)
    buffer.write(2)
    buffer.write(3)

    println(buffer.read()) // 1

    buffer.write(4) // [2, 3, 4]
    buffer.overwrite(5) // overwrites oldest -> [3, 4, 5]

    println(buffer.read()) // 3
    buffer.clear()
    println("Cleared buffer successfully ✅")
}