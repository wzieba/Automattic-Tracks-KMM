package shared.internal.database

import app.cash.turbine.test
import com.automattic.myapplication.shared.Event
import com.automattic.myapplication.shared.internal.SqlDelightRepository
import com.automattic.myapplication.shared.internal.database.Database
import com.automattic.myapplication.shared.internal.database.DriverFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import shared.internal.fakes.fakeEvent
import kotlin.test.assertTrue

internal class DatabaseTests {

    private lateinit var database: Database
    private lateinit var sut: SqlDelightRepository

    val testSuite = arrayOf(
        { `should emit all items that are stored in database`() },
        { `should emit empty list if there's no items`() },
        { `should emit items that are stored in database and the new one that will come`() },
    )

    fun runTestSuite(driverFactory: DriverFactory) =
        testSuite.onEach { test ->
            setUp(driverFactory)
            test.invoke()
        }

    fun setUp(driverFactory: DriverFactory) {
        database = Database(driverFactory, Dispatchers.Unconfined)
        sut = SqlDelightRepository(database)
        database.clear()
    }

    fun `should emit empty list if there's no items`(): Unit = runBlocking {
        sut.observe().test {
            assertTrue(expectMostRecentItem().isEmpty())
        }
    }

    fun `should emit all items that are stored in database`(): Unit =
        runBlocking {
            database.apply {
                insertEvent(eventA)
                insertEvent(eventB)
            }

            sut.observe().test {
                assertTrue(expectMostRecentItem().containsAll(listOf(eventA, eventB)))
            }
        }

    fun `should emit items that are stored in database and the new one that will come`(): Unit =
        runBlocking {
            var items = emptyList<Event>()

            database.apply {
                insertEvent(eventA)
                insertEvent(eventB)
            }

            val job = sut.observe().onEach { result ->
                items = result
            }.launchIn(CoroutineScope(Dispatchers.Unconfined))

            sut.schedule(eventC)

            job.cancel()
            assertTrue(
                items.size == 3 &&
                    items.containsAll(listOf(eventA, eventB, eventC))
            )
        }

    companion object {
        fun event(id: Long = 0, name: String = "") = fakeEvent.copy(id = id, name = name)

        val eventA = event(1, "A")
        val eventB = event(2, "B")
        val eventC = event(3, "C")
    }
}
