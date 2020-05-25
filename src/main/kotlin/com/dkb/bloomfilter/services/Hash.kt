package com.dkb.bloomfilter.services

import java.math.BigInteger
import java.security.MessageDigest

class Hash(private val filterSize: Int) {
    private val md5 = MessageDigest.getInstance("MD5")

    fun hash(value: ByteArray) =
            BigInteger(1, md5.digest(value + value.size.toByte()))
                    .remainder(BigInteger.valueOf(filterSize.toLong()))
                    .toInt()
}