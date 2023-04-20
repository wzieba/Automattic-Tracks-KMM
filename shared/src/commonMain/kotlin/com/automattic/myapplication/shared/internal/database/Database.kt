package com.automattic.myapplication.shared.internal.database

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.automattic.EventsDatabase
import com.automattic.myapplication.shared.Event
import com.automattic.myapplication.shared.EventEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlin.coroutines.CoroutineContext

internal class Database(
    driverFactory: DriverFactory,
    private val coroutineContext: CoroutineContext,
) {

    object JsonObjectAdapter : ColumnAdapter<JsonObject, String> {
        override fun decode(databaseValue: String): JsonObject {
            return Json.decodeFromString(databaseValue)
        }

        override fun encode(value: JsonObject): String {
            return Json.encodeToString(value)
        }

    }

    private val database = EventsDatabase(
        driver = driverFactory.createDriver(),
        eventEntityAdapter = EventEntity.Adapter(
            userTypeAdapter = EnumColumnAdapter(),
            userPropertiesAdapter = JsonObjectAdapter,
            deviceInfoAdapter = JsonObjectAdapter,
            customPropertiesAdapter = JsonObjectAdapter,
        )
    )
    private val dbQuery = database.eventQueries

    internal fun observeOldest(): Flow<List<EventEntity>> =
        dbQuery.selectOldest().asFlow().mapToList(coroutineContext)

    internal fun insertEvent(tracksEvent: Event) {
        dbQuery.insertEvent(
            name = tracksEvent.name,
            userId = tracksEvent.userId,
            userType = tracksEvent.userType,
            userAgent = tracksEvent.userAgent,
            creationTimestamp = tracksEvent.creationTimestamp,
            userProperties = tracksEvent.userProperties,
            deviceInfo = tracksEvent.deviceInfo,
            customProperties = tracksEvent.customProperties,
        )
    }

    internal fun clear() {
        dbQuery.deleteAllEvents()
    }
}
