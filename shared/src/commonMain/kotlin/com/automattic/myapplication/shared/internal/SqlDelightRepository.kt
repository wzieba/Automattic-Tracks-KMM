package com.automattic.myapplication.shared.internal

import com.automattic.myapplication.shared.TracksEvent
import com.automattic.myapplication.shared.internal.database.Database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class SqlDelightRepository(private val database: Database) : LocalRepository {

    override suspend fun schedule(event: TracksEvent) {
        database.insertEvent(event)
    }

    override fun observe(): Flow<List<TracksEvent>> = database.observeOldest().map { events ->
        events.map { event ->
            TracksEvent(event.event_name)
        }
    }
}
