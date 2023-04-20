package com.automattic.myapplication.shared.internal

import com.automattic.myapplication.shared.Event

internal interface RemoteRepository {
    suspend fun send(event: Event)
}
