package shared.internal.fakes

import com.automattic.myapplication.shared.Event
import com.automattic.myapplication.shared.internal.RemoteRepository

class FakeRemoteRepository : RemoteRepository {

    var eventsSent = 0

    override suspend fun send(event: Event) {
        eventsSent++
    }
}
