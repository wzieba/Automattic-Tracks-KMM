package com.automattic.myapplication.shared.internal

import com.automattic.myapplication.shared.TracksEvent
import com.automattic.myapplication.shared.UserProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class EventScheduler(
    private val coroutineScope: CoroutineScope,
    private val userProvider: UserProvider,
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) {

    init {
        coroutineScope.launch {
            localRepository.observe().collect { events ->
                if (events.isNotEmpty()) {
                    remoteRepository.send(events.first())
                }
            }
        }
    }

    fun schedule(event: TracksEvent) {
        coroutineScope.launch {
            localRepository.schedule(event)
        }
    }
}
