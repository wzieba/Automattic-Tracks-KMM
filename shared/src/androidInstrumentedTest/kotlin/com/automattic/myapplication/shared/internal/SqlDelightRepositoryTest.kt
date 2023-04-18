package com.automattic.myapplication.shared.internal

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.automattic.myapplication.shared.internal.database.PlatformDriverFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import shared.internal.database.DatabaseTests

@RunWith(RobolectricTestRunner::class)
internal class SqlDelightRepositoryTest {

    val appContext = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun runTestSuite() {
        DatabaseTests().runTestSuite(PlatformDriverFactory(appContext))
    }
}
