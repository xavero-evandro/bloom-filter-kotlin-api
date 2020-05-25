package com.dkb.bloomfilter.utils

class GenerateRandomString {
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z')

    fun generateRandomString(size: Int = 5): String {
        return (1..size)
                .map { kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
    }
}