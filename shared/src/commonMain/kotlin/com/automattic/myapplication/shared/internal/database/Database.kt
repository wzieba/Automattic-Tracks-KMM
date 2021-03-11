package com.automattic.myapplication.shared.internal.database

import com.automattic.myapplication.shared.Event
import com.automattic.myapplication.shared.EventsDatabase
import com.automattic.myapplication.shared.TracksEvent
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

internal class Database(
    driverFactory: DriverFactory,
    private val coroutineContext: CoroutineContext,
) {

    private val database = EventsDatabase(driver = driverFactory.createDriver())
    private val dbQuery = database.eventQueries

    internal fun observeOldest(): Flow<List<Event>> =
        dbQuery.selectOldest().asFlow().mapToList(coroutineContext)

    internal fun insertEvent(tracksEvent: TracksEvent) {
        dbQuery.insertEvent(
            event_name = tracksEvent.name,
            user = "",
            user_agent = null,
            timestamp = null,
            retry_count = null,
            user_type = null,
            user_props = null,
            device_info = null,
            custom_props = null
        )
    }

    internal fun clear() {
        dbQuery.deleteAllEvents()
    }
}
