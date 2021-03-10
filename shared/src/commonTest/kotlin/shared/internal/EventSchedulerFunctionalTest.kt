package shared.internal

import com.automattic.myapplication.shared.internal.EventScheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import shared.internal.fakes.FakeLocalRepository
import shared.internal.fakes.FakeRemoteRepository
import shared.internal.fakes.FakeUserProvider
import kotlin.test.BeforeTest

internal class EventSchedulerFunctionalTest {

    lateinit var sut: EventScheduler

    private val remoteRepository = FakeRemoteRepository()

    @BeforeTest
    fun setUp() {
        sut = EventScheduler(
            coroutineScope = CoroutineScope(Dispatchers.Unconfined),
            userProvider = FakeUserProvider(),
            localRepository = FakeLocalRepository(),
            remoteRepository = remoteRepository,
        )
    }

//    @Test
//    fun `should schedule and sent an event`() {
//
//        sut.schedule(TracksEvent("test"))
//
//        assertTrue(remoteRepository.eventsSent == 1, "Should send a scheduled event")
//    }
}
