package com.automattic.myapplication.shared

import kotlinx.serialization.json.JsonObject

data class Event(
    val id: Long,
    val name: String,
    val userId: String,
    val userType: UserType,
    val userAgent: String,
    val creationTimestamp: Long,

    val userProperties: JsonObject,
    val deviceInfo: JsonObject,
    val customProperties: JsonObject,
)
