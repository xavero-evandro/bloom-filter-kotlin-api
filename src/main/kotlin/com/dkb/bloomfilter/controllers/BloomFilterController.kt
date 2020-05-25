package com.dkb.bloomfilter.controllers

import com.dkb.bloomfilter.models.ResultResponse
import com.dkb.bloomfilter.services.BloomFilter
import com.dkb.bloomfilter.utils.GenerateRandomString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping(value = ["/"])
class BloomFilterController {

    @Autowired
    private lateinit var bloomFilter: BloomFilter

    @GetMapping(value = ["/{word}"])
    @ResponseBody
    fun getWord(@PathVariable("word") word: String): ResultResponse {
        if (word.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")

        val wordFound = bloomFilter.search(word.toByteArray())
        val message = if (wordFound) "Word ${word} Found" else "Word ${word} Not Found"

        return ResultResponse(wordFound, message)
    }

    @PostMapping
    @ResponseBody
    fun generateWords(): ResultResponse {
        val randomString = GenerateRandomString().generateRandomString()
        val wordFound = bloomFilter.search(randomString.toByteArray())
        if (!wordFound) {
            bloomFilter.add(randomString.toByteArray())
        }
        val message = if (wordFound)
            "Word $randomString 'Found' Probably a False Positive"
        else
            "Word $randomString Not Found And Added to The Dictionary"
        return ResultResponse(wordFound, message)
    }
}