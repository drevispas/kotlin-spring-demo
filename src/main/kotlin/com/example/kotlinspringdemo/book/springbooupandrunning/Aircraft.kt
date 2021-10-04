package com.example.kotlinspringdemo.book.springbooupandrunning

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class Aircraft(
    // `id` values will be changed by database according to `@GeneratedValue`.
    @Id @GeneratedValue val id: Long = 0L,
    @JsonProperty("callsign") val callSign: String = "", // json(callsign), obj(callSign), db(call_sign)
    val squawk: String = "",
    val reg: String = "",
    @JsonProperty("flightno") val flightNo: String = "Unknown#", // If Json has no value, filled with the default.
    val route: String = "",
    val type: String = "",
    val category: String = "",
    val altitude: Int = 0,
    val heading: Int = 0,
    val speed: Int = 0,
    @JsonProperty("ver_rate") val vertRate: Int = 0,
    // Class property names converted to Table column name automatically.
    @JsonProperty("selected_altitude") val selectedAltitude: Int = 0,
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val barometer: Double = 0.0,
    @JsonProperty("polar_distance") val polarDistance: Double = 0.0,
    @JsonProperty("polar_bearing") val polarBearing: Double = 0.0,
    @JsonProperty("is_adsb") val isAdsb: Boolean = false,
    @JsonProperty("is_on_ground") val isOnGround: Boolean = false,
    @JsonProperty("last_seen_time") val lastSeenTime: String = Instant.ofEpochSecond(0).toString(),
    @JsonProperty("pos_update_time") val posUpdateTime: String = Instant.ofEpochSecond(0).toString(),
    @JsonProperty("bds40_seen_time") val bds40SeenTime: String = Instant.ofEpochSecond(0).toString()
)
