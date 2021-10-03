package com.example.kotlinspringdemo.book.springbooupandrunning

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@EnableScheduling
@Component
// The app inject constructor arguments by generating beans.
class PlaneFinderPoller(
    private val aircraftRepository: AircraftRepository
) {
    private val webClient = WebClient.create("http://localhost:7634/aircraft")

    @Scheduled(fixedRate = 600000)
    private fun pollPlanes() {
        aircraftRepository.deleteAll()
        webClient.get().retrieve().bodyToFlux(Aircraft::class.java)
            .filter { !it.reg.isNullOrBlank() }
            .toStream()
            .forEach { aircraftRepository.save(it) }
        aircraftRepository.findAll().forEach { println(it) }
    }
}
