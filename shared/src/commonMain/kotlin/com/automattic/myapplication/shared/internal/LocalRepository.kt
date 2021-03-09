package com.automattic.myapplication.shared.internal

import com.automattic.myapplication.shared.TracksEvent
import kotlinx.coroutines.flow.Flow

internal interface LocalRepository {
    suspend fun schedule(event: TracksEvent)
    fun observe(): Flow<TracksEvent>
}
