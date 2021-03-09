package shared.internal.fakes

import com.automattic.myapplication.shared.TracksEvent
import com.automattic.myapplication.shared.internal.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeLocalRepository : LocalRepository {

    private val emitter = MutableSharedFlow<TracksEvent>()

    override suspend fun schedule(event: TracksEvent) {
        emitter.emit(event)
    }

    override fun observe(): Flow<TracksEvent> = emitter
}
