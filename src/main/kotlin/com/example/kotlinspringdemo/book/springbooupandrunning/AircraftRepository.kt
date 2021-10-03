package com.example.kotlinspringdemo.book.springbooupandrunning

import org.springframework.data.repository.CrudRepository

interface AircraftRepository : CrudRepository<Aircraft, Long>