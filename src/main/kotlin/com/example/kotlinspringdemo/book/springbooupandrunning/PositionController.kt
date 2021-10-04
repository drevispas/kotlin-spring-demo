package com.example.kotlinspringdemo.book.springbooupandrunning

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.reactive.function.client.WebClient

@Controller
class PositionController(val repository: AircraftRepository) {
    private val client: WebClient = WebClient.create("http://localhost:7634/aircraft")

    @GetMapping("/aircraft/webclient")
    fun getAircraftPositions(model: Model): String {
        repository.deleteAll()
        client.get().retrieve().bodyToFlux(Aircraft::class.java)
            .filter { !it.reg.isEmpty() }
            .toStream()
            .forEach(repository::save)
        model.addAttribute("positions", repository.findAll())
        return "positions"
    }

    @GetMapping("/aircraft/subscribe")
    fun subscribeAircraftPositions(model: Model): String {
        model.addAttribute("positions", repository.findAll())
        return "positions"
    }
}