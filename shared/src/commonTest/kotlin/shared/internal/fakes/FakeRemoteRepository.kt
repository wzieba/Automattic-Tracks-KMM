package shared.internal.fakes

import com.automattic.myapplication.shared.TracksEvent
import com.automattic.myapplication.shared.internal.RemoteRepository

class FakeRemoteRepository : RemoteRepository {

    var eventsSent = 0

    override suspend fun send(event: TracksEvent) {
        eventsSent++
    }
}
