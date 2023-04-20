package com.automattic.myapplication.shared.internal

import com.automattic.myapplication.shared.Event
import kotlinx.coroutines.flow.Flow

internal interface LocalRepository {
    suspend fun schedule(event: Event)
    fun observe(): Flow<List<Event>>
}
