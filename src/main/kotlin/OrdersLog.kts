#!/usr/bin/env kotlin

/*
    This problem was asked by Twitter.

    You run an e-commerce website and want to record the last N order ids in a log. Implement a data structure to accomplish this, with the following API:

    record(order_id): adds the order_id to the log
    get_last(i): gets the ith last element from the log. i is guaranteed to be smaller than or equal to N.
    You should be as efficient with time and space as possible.
*/

class OrderLog(private val n: Int) {
    private val buffer = IntArray(n)
    private var count = 0
    private var newestIdx = -1

    fun record(orderId: Int) {
        newestIdx = (newestIdx + 1) % n
        buffer[newestIdx] = orderId
        count = (count + 1).coerceAtMost(n)
    }

    fun getLast(i: Int): Int {
        if (i > count) {
            throw IndexOutOfBoundsException()
        }

        val idx = (newestIdx - (i - 1)) % n

        return buffer[idx]
    }

    override fun toString(): String {
        return buffer.joinToString(prefix = "[", postfix = "]")
    }
}

val log = OrderLog(3)

log.record(10)
log.record(20)
println(log)
println(log.getLast(1))
log.record(30)
println(log.getLast(1))
log.record(40)
println(log.getLast(1))
println(log)
