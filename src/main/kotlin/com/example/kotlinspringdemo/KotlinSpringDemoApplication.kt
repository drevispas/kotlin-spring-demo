package com.example.kotlinspringdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@SpringBootApplication
class KotlinSpringDemoApplication {
    @Bean
    // The Spring app injects `RedisConnectionFactory` bean automatically.
    fun redisOperations(redisConnectionFactory: RedisConnectionFactory): RedisOperations<String,Aircraft> {
        val template:RedisTemplate<String,Aircraft> = RedisTemplate()
        template.setConnectionFactory(redisConnectionFactory)
        template.setDefaultSerializer(Jackson2JsonRedisSerializer(Aircraft::class.java)) // Aircraft::class is a KClass.
        template.keySerializer = StringRedisSerializer() // keySerializer is a class property.
        return template
    }
}

fun main(args: Array<String>) {
    runApplication<KotlinSpringDemoApplication>(*args)
    // Do not declare @Bean here.
}
