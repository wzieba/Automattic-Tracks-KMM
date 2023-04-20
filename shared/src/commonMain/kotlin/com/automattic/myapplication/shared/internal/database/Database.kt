package com.automattic.myapplication.shared.internal.database

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.automattic.EventsDatabase
import com.automattic.myapplication.shared.Event
import com.automattic.myapplication.shared.EventEntity
import io.ktor.http.ContentType.Application.Json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import kotlin.coroutines.CoroutineContext

internal class Database(
    driverFactory: DriverFactory,
    private val coroutineContext: CoroutineContext,
) {

    private val database = EventsDatabase(
        driver = driverFactory.createDriver(),
        eventEntityAdapter = EventEntity.Adapter(
            userTypeAdapter = EnumColumnAdapter(),
            user_propertiesAdapter = object : ColumnAdapter<JsonObject, String> {
                override fun decode(databaseValue: String): JsonObject {
                    return Json{}.encodeToJsonElement(databaseValue).jsonObject
                }

                override fun encode(value: JsonObject): String {
                    return value.toString()
                }

            }
        )
    )
    private val dbQuery = database.eventQueries

    internal fun observeOldest(): Flow<List<Event>> =
        dbQuery.selectOldest().asFlow().mapToList(coroutineContext).map { emptyList() }

    internal fun insertEvent(tracksEvent: Event) {
        dbQuery.insertEvent(
            name = "test event",
            userId = null,
            userType = null,
            creationTimestamp = null,
            user_properties = null,
            device_info = null,
            custom_props = null,
        )
    }

    internal fun clear() {
        dbQuery.deleteAllEvents()
    }
}
