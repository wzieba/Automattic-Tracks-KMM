package com.automattic.myapplication.shared

import com.automattic.myapplication.shared.internal.EventScheduler
import kotlinx.coroutines.MainScope
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object Tracks {

    private var tracker: EventScheduler? = null

    fun initialize(userProvider: UserProvider) {
        tracker = EventScheduler(
            userProvider = userProvider,
            remoteRepository = TODO(),
            localRepository = TODO(),
            coroutineScope = MainScope(),
        )
    }

    fun track(tracksEvent: TracksEvent) {
        tracker?.schedule(tracksEvent)
    }
}
