package shared.internal.fakes

import com.automattic.myapplication.shared.TracksUser
import com.automattic.myapplication.shared.UserProvider

class FakeUserProvider : UserProvider() {
    override fun request(): TracksUser {
        TODO("Not yet implemented")
    }
}
