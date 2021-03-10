package com.automattic.myapplication.shared.internal

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.automattic.myapplication.shared.TracksEvent
import com.automattic.myapplication.shared.internal.database.Database
import com.automattic.myapplication.shared.internal.database.DriverFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class SqlDelightRepositoryTest {

    lateinit var sut: SqlDelightRepository

    val appContext = ApplicationProvider.getApplicationContext<Context>()
    val database = Database(DriverFactory(appContext), Dispatchers.Unconfined)
    val items = mutableListOf<TracksEvent>()

    @Before
    fun setUp() {
        sut = SqlDelightRepository(database)
    }

    @Test
    fun `should emit empty list if there's no items`(): Unit = runBlocking {
        sut.observe().test {
            assertThat(expectItem()).isEmpty()
        }
    }

    @Test
    fun `should emit all items that are stored in database`(): Unit = runBlocking {
        database.apply {
            insertEvent(eventA)
            insertEvent(eventB)
        }

        sut.observe().test {
            assertThat(expectItem()).containsExactly(eventA, eventB)
        }
    }

    @Test
    fun `should emit items that are stored in database and the new one that will come`(): Unit =
        runBlocking {
            var items = emptyList<TracksEvent>()

            database.apply {
                insertEvent(eventA)
                insertEvent(eventB)
            }

            val job = sut.observe().onEach { result ->
                items = result
            }.launchIn(CoroutineScope(Dispatchers.Unconfined))

            sut.schedule(eventC)

            job.cancel()
            assertThat(items).containsExactly(eventA, eventB, eventC)
        }

    companion object {
        fun event(name: String = "") = TracksEvent(name)

        val eventA = event("A")
        val eventB = event("B")
        val eventC = event("C")
    }
}
