package com.automattic.myapplication.shared

import com.automattic.myapplication.shared.internal.EventComposer
import com.automattic.myapplication.shared.internal.EventScheduler
import kotlinx.coroutines.MainScope
import kotlin.jvm.JvmOverloads
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object Tracks {

    private lateinit var tracker: EventScheduler
    private lateinit var eventComposer: EventComposer

    fun initialize(userProvider: UserProvider) {
        tracker = EventScheduler(
            userProvider = userProvider,
            remoteRepository = TODO(),
            localRepository = TODO(),
            coroutineScope = MainScope(),
        )
    }

    @JvmOverloads
    fun track(
        eventName: String,
        customProperties: String = "",
        userId: String = "",
    ) {
        val event = eventComposer.compose(eventName)

        tracker.schedule(event)
    }
}
