package com.example.kotlinspringdemo.book.springbooupandrunning

import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.time.Instant

@Component
class WebSocketHandler(var sessionList: List<WebSocketSession> = listOf(), val repository: AircraftRepository) :
    TextWebSocketHandler() {
    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessionList += session
        println("Connection established from $session @ ${Instant.now()}")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        try {
            println("Message received: '$message' from $session")
            sessionList.asSequence()
                .filter { it != session }
                .forEach {
                    it.sendMessage(message)
                    println("--> Sending message '$message' to $it")
                }
        } catch (e: Exception) {
            println("Exception handling message: ${e.getLocalizedMessage()}")
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessionList -= session
        println("Connection closed by $session @ ${Instant.now()}")
    }
}