package com.dkb.bloomfilter

import com.dkb.bloomfilter.models.ResultResponse
import com.fasterxml.jackson.annotation.JsonCreator
import org.json.JSONObject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.function.Executable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.web.server.ResponseStatusException
import kotlin.reflect.typeOf

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BloomFilterIntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    fun bloomFilterIntegrationWordNotFound() {
        val mockresult = ResultResponse(false, "Word evandro Not Found")
        val response = restTemplate.getForEntity("/evandro", ResultResponse::class.java)
        Assertions.assertEquals(HttpStatus.OK, response.statusCode)
        Assertions.assertEquals(mockresult, response.body)
    }

    @Test
    fun bloomFilterIntegrationWordFound() {
        val mockresult = ResultResponse(true, "Word carolina Found")
        val response = restTemplate.getForEntity("/carolina", ResultResponse::class.java)
        Assertions.assertEquals(HttpStatus.OK, response.statusCode)
        Assertions.assertEquals(mockresult, response.body)
    }

    @Test
    fun bloomFilterRandomWord() {
        val response = restTemplate.postForEntity("/", RequestEntity::class.java, ResultResponse::class.java)
        Assertions.assertEquals(HttpStatus.OK, response.statusCode)
    }
}