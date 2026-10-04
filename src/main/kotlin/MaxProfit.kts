#!/usr/bin/env kotlin

/*
    Given an array of numbers representing the stock prices of a company in chronological order,
    write a function that calculates the maximum profit you could have made from buying and selling that stock once.
    You must buy before you can sell it.

    For example, given [9, 11, 8, 5, 7, 10], you should return 5,
    since you could buy the stock at 5 dollars and sell it at 10 dollars.
*/

fun maxProfit(prices: List<Int>): Int {
    var maxProfit = 0
    var minPrice = prices[0]

    for (currentPrice in prices) {
        val profit = currentPrice - minPrice

        if (profit > maxProfit) {
            maxProfit = profit
        }

        if (currentPrice < minPrice) {
            minPrice = currentPrice
        }
    }

    return maxProfit
}

println(maxProfit(listOf(9, 11, 8, 5, 7, 10)))
