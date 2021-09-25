package com.example.kotlinspringdemo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import java.time.Instant

@JsonIgnoreProperties(ignoreUnknown = true)
data class Aircraft(
    // We got an erros "Argument #0 of constructor has no property name annotation" without @JsonProperty for a property.
    @JsonProperty("id") @Id val id: Long=0L,
    @JsonProperty("callsign") val callsign: String,
    @JsonProperty("squawk") val squawk: String,
    @JsonProperty("reg") val reg: String,
    @JsonProperty("flightno") val flightno: String,
    @JsonProperty("route") val route: String,
    @JsonProperty("type") val type: String,
    @JsonProperty("category") val category: String,
    @JsonProperty("vert_rate") val vertRate: Int,
    @JsonProperty("selected_altitude") val selectedAltitude: Int,
    @JsonProperty("polar_distance") val polarDistance:Double,
    @JsonProperty("polar_bearing") val polarBearing:Double,
    @JsonProperty("is_adsb") val isADSB:Boolean,
    @JsonProperty("is_on_ground") val isOnGround:Boolean,
    // We got an error "Parameter specified as non-null is null" if Json and class props not matched exactly. Use `String?` instead.
    @JsonProperty("last_seen_time") val lastSeenTime:String?=Instant.ofEpochSecond(0).toString(),
    @JsonProperty("pos_update_time") val posUpdateTime:String?=Instant.ofEpochSecond(0).toString(),
    @JsonProperty("bds40_seen_time") val bds40SeenTime:String?=Instant.ofEpochSecond(0).toString()
)
