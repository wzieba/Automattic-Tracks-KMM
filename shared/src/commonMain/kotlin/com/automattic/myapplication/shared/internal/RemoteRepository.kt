package com.automattic.myapplication.shared.internal

import com.automattic.myapplication.shared.TracksEvent

internal interface RemoteRepository {
    suspend fun send(event: TracksEvent)
}
