package com.dkb.bloomfilter.services

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.io.File
import javax.annotation.PostConstruct

const val WORDS_FILE_PATH = "src/main/resources/static/wordlist.txt"

@Component
@Scope("singleton")
class WordsLoad {
    private var size: Int = 0
    private var wordsList: List<String> = emptyList()

    @PostConstruct
    fun init() {
        wordsList = File(WORDS_FILE_PATH).bufferedReader().use { it.readLines() }
        size = wordsList.size
    }

    fun getWordList() = wordsList

    fun getSize() = size
}