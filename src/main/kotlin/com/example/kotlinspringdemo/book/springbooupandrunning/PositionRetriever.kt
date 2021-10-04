package com.example.kotlinspringdemo.book.springbooupandrunning

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.TextMessage
import java.io.IOException
import java.util.function.Consumer

@Configuration
class PositionRetriever(val repository: AircraftRepository, val wsHandler: WebSocketHandler) {
    @Bean
    fun retrieveAircraftPositions() = Consumer<List<Aircraft>> { // Implement SAM
        repository.deleteAll()
        repository.saveAll(it)
        repository.findAll().forEach { println(it) }
        sendPositions()
    }

    private fun sendPositions() {
        if (repository.count() <= 0) return
        wsHandler.sessionList.forEach {
            try {
                it.sendMessage(TextMessage(repository.findAll().toString()))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}