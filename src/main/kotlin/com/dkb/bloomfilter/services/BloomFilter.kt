package com.dkb.bloomfilter.services

import com.dkb.bloomfilter.interfaces.BloomFilterInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.annotation.PostConstruct
import kotlin.concurrent.read
import kotlin.concurrent.write

@Component
class BloomFilter(private val wordLoad: WordsLoad) : BloomFilterInterface {
    override val bits = BitSet(wordLoad.getSize())
    override val hash = Hash(wordLoad.getSize())
    override val lock = ReentrantReadWriteLock()

    @PostConstruct
    fun init() {
        wordLoad.getWordList().forEach { add(it.toByteArray()) }
    }

    fun add(value: ByteArray) {
        lock.write {
            if (!bits.get(hash.hash(value))) {
                bits.set(hash.hash(value))
            }
        }
    }

    fun search(value: ByteArray): Boolean {
        lock.read {
            if (!bits.get(hash.hash(value))) {
                return false
            }
        }
        return true
    }
}