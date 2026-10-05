#!/usr/bin/env kotlin

/*
    This problem was asked by Google.

    Implement integer exponentiation. That is, implement the pow(x, y) function, where x and y are positive integers
    and returns x^y. Do this faster than the naive method of repeated multiplication.

    For example, pow(2, 10) should return 1024.
*/

fun Int.pow(exponent: Int): Int {
    var result = 1
    var base = this
    var exp = exponent

    while (exp > 0) {
        if (exp and 1 == 1) {
            result *= base
        }

        base *= base
        exp = exp shr 1
    }

    return result
}

println(2.pow(10))