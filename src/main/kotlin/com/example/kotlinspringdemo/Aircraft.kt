package com.example.kotlinspringdemo

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class Aircraft(
    // `id` values will be changed by database according to `@GeneratedValue`.
    @Id @GeneratedValue val id: Long = 0L,
    val callSign: String = "",
    val squawk: String = "",
    val reg: String = "",
    val flightNo: String = "",
    val route: String = "",
    val type: String = "",
    val category: String = "",
    val altitude: Int = 0,
    val heading: Int = 0,
    val speed: Int = 0,
    val vertRate: Int = 0,
    // Class property names converted to Table column name automatically.
    val selectedAltitude: Int = 0,
    val polarDistance: Double = 0.0,
    val polarBearing: Double = 0.0,
    val isAdsb: Boolean = false,
    val isOnGround: Boolean = false,
    val lastSeenTime: String = Instant.ofEpochSecond(0).toString(),
    val posUpdateTime: String = Instant.ofEpochSecond(0).toString(),
    val bds40SeenTime: String = Instant.ofEpochSecond(0).toString()
)
