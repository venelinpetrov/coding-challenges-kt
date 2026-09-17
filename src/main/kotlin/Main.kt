package com.vpe

fun main() {
    val cache = LRUCache<Int, String>(2)

    cache[1] = "one"
    cache[2] = "two"

    println(cache[1]) // one

    cache[3] = "three"

    println(cache[2]) // null, because it was evicted
    println(cache[3]) // three

}