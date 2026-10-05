#!/usr/bin/env kotlin

import kotlin.random.Random

/*
    This problem was asked by Microsoft.

    Implement a URL shortener with the following methods:

    shorten(url), which shortens the url into a six-character alphanumeric string, such as zLg6wl.
    restore(short), which expands the shortened string into the original url. If no such shortened string exists, return null.
    Hint: What if we enter the same URL twice?
 */

class UrlShortener {
    private val CODE_LEN = 6
    private val CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

    private val codeToUrl = mutableMapOf<String, String>()
    private val urlToCode = mutableMapOf<String, String>()

    fun shorten(url: String): String {
        if (urlToCode.containsKey(url)) {
            return urlToCode[url]!!
        }

        var code: String

        do {
            code = generateCode()
        } while (codeToUrl.containsKey(code))

        codeToUrl[code] = url
        urlToCode[url] = code

        return code
    }

    fun restore(code: String) = codeToUrl[code]

    private fun generateCode(): String {
        return buildString(CODE_LEN) {
            repeat(CODE_LEN) {
                append(CHARSET[Random.nextInt(CHARSET.length)])
            }
        }
    }
}

val us = UrlShortener()
val shortStr = us.shorten("https://example.com")
println(shortStr)
println(us.restore(shortStr))