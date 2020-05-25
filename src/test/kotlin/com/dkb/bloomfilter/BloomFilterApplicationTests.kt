package com.dkb.bloomfilter

import com.dkb.bloomfilter.controllers.BloomFilterController
import com.dkb.bloomfilter.models.ResultResponse
import com.dkb.bloomfilter.services.BloomFilter
import com.dkb.bloomfilter.services.Hash
import com.dkb.bloomfilter.services.WordsLoad
import com.dkb.bloomfilter.utils.GenerateRandomString
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@ExtendWith(SpringExtension::class)
@WebMvcTest(BloomFilterApplication::class)
class BloomFilterApplicationTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private lateinit var wordsLoad: WordsLoad

    private lateinit var bloomFilter: BloomFilter

    private lateinit var hash: Hash

    private lateinit var generateRandomString: GenerateRandomString

    @Test
    fun getBloomFilterWord() {
        wordsLoad = WordsLoad()
        wordsLoad.init()
        bloomFilter = BloomFilter(wordsLoad)
        bloomFilter.init()
        bloomFilter.add("Test".toByteArray())
        Assertions.assertEquals(true, bloomFilter.search("Test".toByteArray()))
        Assertions.assertEquals(false, bloomFilter.search("Evandro".toByteArray()))
    }

    @Test
    fun getBloomFilterController() {
        val mockBloomFilter: BloomFilterController = mock()
        val mockBloomFilterService: BloomFilter = mock()
        whenever(mockBloomFilterService.search("Carolina".toByteArray())).thenReturn(true)
        whenever(mockBloomFilter.getWord("Carolina")).thenReturn(ResultResponse(true, "Found"))
        Assertions.assertEquals(true, mockBloomFilterService.search("Carolina".toByteArray()))
        Assertions.assertEquals(ResultResponse(true, "Found"), mockBloomFilter.getWord("Carolina"))
    }


    @Test
    fun hasFunction() {
        hash = Hash(100)
        val result = hash.hash("Evandro".toByteArray())
        Assertions.assertEquals(48, result)
    }

    @Test
    fun loadWords() {
        wordsLoad = WordsLoad()
        Assertions.assertEquals(emptyList<String>(), wordsLoad.getWordList())
        Assertions.assertEquals(0, wordsLoad.getSize())
    }

    @Test
    fun generateRandomWordTest() {
        generateRandomString = GenerateRandomString()
        val randomWord = generateRandomString.generateRandomString()
        Assertions.assertEquals(5, randomWord.length)
    }




}
