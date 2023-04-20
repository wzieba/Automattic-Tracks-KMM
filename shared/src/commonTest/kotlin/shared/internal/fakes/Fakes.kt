package shared.internal.fakes

import com.automattic.myapplication.shared.Event
import com.automattic.myapplication.shared.UserType
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

val fakeEvent = Event(
    id = 123,
    name = "test event",
    userId = "4321",
    userType = UserType.POCKETCASTS,
    userAgent = "Mozilla/5.0",
    creationTimestamp = 1234567890,
    userProperties = JsonObject(mapOf("key" to JsonPrimitive("value"))),
    deviceInfo = JsonObject(mapOf("key" to JsonPrimitive("value"))),
    customProperties = JsonObject(mapOf("key" to JsonPrimitive("value"))),
)