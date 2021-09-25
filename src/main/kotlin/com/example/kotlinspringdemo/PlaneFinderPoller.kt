package com.example.kotlinspringdemo

import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisOperations
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux

@EnableScheduling
@Component
class PlaneFinderPollerwe(
    val redisConnectionFactory: RedisConnectionFactory, // We expect a bean injection without initialization.
    val redisOperations: RedisOperations<String, Aircraft>
) {
    val webClient = WebClient.create("http://localhost:7634/aircraft")

    @Scheduled(fixedRate = 5000)
    private fun pollPlanes() {
        redisConnectionFactory.connection.serverCommands().flushDb()
        webClient.get().retrieve().bodyToFlux(Aircraft::class.java)
            .filter { !it.reg.isNullOrBlank() }
            .toStream()
            .forEach { redisOperations.opsForValue()[it.reg!!]=it }
        redisOperations.opsForValue().operations.keys("*")?.forEach { println(redisOperations.opsForValue()[it]) }
    }
}
