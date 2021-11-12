package com.faircorp.model

data class RoomDto(val id: Long,
                   val floor:Long,
                   val name: String,
                   val currentTemperature: Double?,
                   val targetTemperature: Double?,
                   val buildingId:Long
                   )