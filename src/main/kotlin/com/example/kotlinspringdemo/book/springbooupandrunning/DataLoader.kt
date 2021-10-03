package com.example.kotlinspringdemo.book.springbooupandrunning

import org.springframework.stereotype.Component
import java.time.Instant
import javax.annotation.PostConstruct

@Component
class DataLoader(val aircraftRepository: AircraftRepository) {
    @PostConstruct
    fun loadData() {
        aircraftRepository.deleteAll()
        aircraftRepository.save(
            Aircraft(
                14L,
                "AAL608",
                "squawk",
                "N754UW",
                "AA608",
                "route",
                "IND-PHX",
                "A319",
                360000,
                255,
                423,
                0,
                36000,
                0.0,
                0.0,
                true,
                false,
                Instant.now().toString(),
                Instant.now().toString(),
                Instant.now().toString()
            )
        )
    }
}