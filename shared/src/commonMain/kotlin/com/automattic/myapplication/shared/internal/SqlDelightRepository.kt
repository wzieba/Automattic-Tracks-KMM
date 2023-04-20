package com.automattic.myapplication.shared.internal

import com.automattic.myapplication.shared.Event
import com.automattic.myapplication.shared.UserType
import com.automattic.myapplication.shared.internal.database.Database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.JsonObject

internal class SqlDelightRepository(private val database: Database) : LocalRepository {

    override suspend fun schedule(event: Event) {
        database.insertEvent(event)
    }

    override fun observe(): Flow<List<Event>> = database.observeOldest().map { events ->
        events.map { event ->
            Event(
                id = event.id,
                name = event.name,
                userId = event.userId,
                userType = event.userType,
                userAgent = event.userAgent,
                creationTimestamp = event.creationTimestamp,
                userProperties = event.userProperties,
                deviceInfo = event.deviceInfo,
                customProperties = event.customProperties,
            )
        }
    }
}
