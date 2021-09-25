package com.example.kotlinspringdemo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id

//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
data class Aircraft(
    // We got an erros "Argument #0 of constructor has no property name annotation" without @JsonProperty for a property.
    @JsonProperty("id") @Id val id: Long=0L,
    // We got an error "Parameter specified as non-null is null" for String prop with just `:String`. Instead use `:String?`.
    @JsonProperty("callsign") val callSign: String? = "",
    @JsonProperty("squawk") val squawk: String = "",
    @JsonProperty("reg") val reg: String = "",
    @JsonProperty("flightno") val flightNo: String? = "",
    @JsonProperty("route") val route: String = "",
    @JsonProperty("type") val type: String = "",
    @JsonProperty("category") val category: String = "",
    @JsonProperty("vert_rate") val vertRate: Int=0,
    @JsonProperty("selected_altitude") val selectedAltitude: Int=0,
    @JsonProperty("polar_distance") val polarDistance:Double=0.0,
    @JsonProperty("polar_bearing") val polarBearing:Double=0.0,
    @JsonProperty("is_adsb") val isADSB:Boolean=false,
    @JsonProperty("is_on_ground") val isOnGround:Boolean=false,
    @JsonProperty("last_seen_time") val lastSeenTime:String?=Instant.ofEpochSecond(0).toString(),
    @JsonProperty("pos_update_time") val posUpdateTime:String?=Instant.ofEpochSecond(0).toString(),
    @JsonProperty("bds40_seen_time") val bds40SeenTime:String?=Instant.ofEpochSecond(0).toString()
)
