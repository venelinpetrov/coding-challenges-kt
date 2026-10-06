#!/usr/bin/env kotlin

/*
    This problem was asked by Facebook.

    Given a multiset of integers, return whether it can be partitioned into two subsets whose sums are the same.

    For example, given the multiset {15, 5, 20, 10, 35, 15, 10}, it would return true, since we can split it up into
    {15, 5, 10, 15, 10} and {20, 35}, which both add up to 55.

    Given the multiset {15, 5, 20, 10, 35}, it would return false, since we can't split it up into two subsets that add up to the same sum.
*/

/*
    My notes:

    If Sum(s1) = Sum(s2)
    then Sum(s1) = Sum(S) / 2

    Therefore, we are looking for just one subset that sums up to this Sum(S) / 2. This is the target value.
    This also implies that Sum(S) must be even number

    Then we iterate over all the numbers and construct the possible sums. If a sum is equal to target then return true, otherwise - false.
    So for example {15, 5, 20} possible sums are constructed like this

    {0}
    {0, 0+15} = {0, 15}
    {0, 15, 0+5, 15+5} = {0, 15, 5, 20} <-- We stop here as 20 = Sum(S) / 2

    We use Set DS to rule out repeating values e.g.:
    {0, 15, 5, 20, 0+20, 15+20, 5+20, 20+20} = {0, 15, 5, 20, (20), 35, 25, 40}

    Time complexity: If N is the size of S and T is the target then O(N*T)

    Space complexity: O(T)
*/

fun exists(arr: IntArray): Boolean {
    val sum = arr.sum()

    if (sum % 2 != 0) {
        return false
    }

    val target = sum / 2

    var possibleSums = hashSetOf(0)

    for (value in arr) {
        val newSums = HashSet<Int>(possibleSums)

        for (s in possibleSums) {
            newSums.add(s + value)
        }

        if (newSums.contains(target)) {
            return true
        }

        possibleSums = newSums
    }

    return false
}

println(exists(intArrayOf(15, 5, 20, 10, 35, 15, 10)))
println(exists(intArrayOf(20, 10, 35)))
