package com.automattic.myapplication.shared.internal

import com.automattic.myapplication.shared.Event
import com.automattic.myapplication.shared.UserProvider
import com.automattic.myapplication.shared.UserType
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

internal class EventComposer(private val userProvider: UserProvider) {

    fun compose(
        eventName: String,
    ): Event {
        return Event(
            id = 123,
            name = eventName,
            userId = "4321",
            userType = UserType.POCKETCASTS,
            userAgent = "Mozilla/5.0",
            creationTimestamp = 1234567890,
            userProperties = JsonObject(mapOf("key" to JsonPrimitive("value"))),
            deviceInfo = JsonObject(mapOf("key" to JsonPrimitive("value"))),
            customProperties = JsonObject(mapOf("key" to JsonPrimitive("value"))),
        )
    }
}