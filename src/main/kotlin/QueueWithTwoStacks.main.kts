#!/usr/bin/env kotlin

/*
    This problem was asked by Apple.

    Implement a queue using two stacks. Recall that a queue is a FIFO (first-in, first-out)
    data structure with the following methods: enqueue, which inserts an element into the queue,
    and dequeue, which removes it.
*/
class QueueWithTwoStacks {
    private val stack1: ArrayDeque<Int> = ArrayDeque()
    private val stack2: ArrayDeque<Int> = ArrayDeque()

    fun enqueue(item: Int) {
        stack1.addLast(item)
    }

    fun dequeue(): Int? {
        if (stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.addLast(stack1.removeLast())
            }
        }

        return  stack2.removeLastOrNull()
    }

    override fun toString(): String =
        (stack2 + stack1.reversed()).joinToString(
            prefix = "[",
            postfix = "]"
        )
}

val q = QueueWithTwoStacks()

q.enqueue(10)
q.enqueue(20)
q.dequeue()
q.enqueue(30)
println(q)