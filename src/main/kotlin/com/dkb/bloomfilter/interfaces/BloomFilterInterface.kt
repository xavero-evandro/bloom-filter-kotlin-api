package com.dkb.bloomfilter.interfaces

import com.dkb.bloomfilter.services.Hash
import java.util.*
import java.util.concurrent.locks.ReentrantReadWriteLock

interface BloomFilterInterface {
    val bits: BitSet
    val hash: Hash
    val lock: ReentrantReadWriteLock
}