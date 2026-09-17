package com.vpe

/*
    This problem was asked by Google.

    Implement an LRU (Least Recently Used) cache. It should be able to be initialized with a cache size n,
    and contain the following methods:
        - set(key, value): sets key to value. If there are already n items in the cache and we are adding
          a new item, then it should also remove the least recently used item.
        - get(key): gets the value at key. If no such key exists, return null.

    Each operation should run in O(1) time.
*/

class LRUCache<K, V>(private val capacity: Int) {
    private class Node<K, V>(
        val key: K?,
        var value: V?
    ) {
        var prev: Node<K, V>? = null
        var next: Node<K, V>? = null
    }

    private val cache = HashMap<K, Node<K, V>>()
    private val head = Node<K, V>(null, null)
    private val tail = Node<K, V>(null, null)

    init {
        head.next = tail
        tail.prev = head
    }

    operator fun get(key: K): V? {
        val node = cache[key] ?: return null
        moveToFront(node)
        return  node.value
    }

    operator fun set(key: K, value: V) {
        cache[key]?.let { node ->
            node.value = value
            moveToFront(node)
            return
        }

        val node = Node(key, value)
        cache[key] = node
        addToFront(node)

        if (cache.size > capacity) {
            val lru = tail.prev!!
            cache.remove(lru.key)
            remove(lru)
        }
    }

    private fun moveToFront(node: Node<K, V>) {
        remove(node)
        addToFront(node)
    }

    private fun remove(node: Node<K, V>) {
        node.prev!!.next = node.next
        node.next!!.prev = node.prev
    }

    private fun addToFront(node: Node<K, V>) {
        node.prev = head
        node.next = head.next

        head.next!!.prev = node
        head.next = node
    }
}