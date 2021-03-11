package shared.internal.fakes

import com.automattic.myapplication.shared.TracksEvent
import com.automattic.myapplication.shared.internal.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach

class FakeLocalRepository : LocalRepository {

    private val emitter = MutableStateFlow<List<TracksEvent>>(emptyList())

    override suspend fun schedule(event: TracksEvent) {
        emitter.emit(emitter.value + event)
    }

    override fun observe(): Flow<List<TracksEvent>> = emitter.onEach {
        println(it)
    }
}
