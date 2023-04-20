package shared.internal.fakes

import com.automattic.myapplication.shared.Event
import com.automattic.myapplication.shared.internal.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach

class FakeLocalRepository : LocalRepository {

    private val emitter = MutableStateFlow<List<Event>>(emptyList())

    override suspend fun schedule(event: Event) {
        emitter.emit(emitter.value + event)
    }

    override fun observe(): Flow<List<Event>> = emitter.onEach {
        println(it)
    }
}
