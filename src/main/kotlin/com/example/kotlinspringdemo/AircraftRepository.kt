package com.example.kotlinspringdemo

import org.springframework.data.repository.CrudRepository

interface AircraftRepository : CrudRepository<Aircraft, Long>