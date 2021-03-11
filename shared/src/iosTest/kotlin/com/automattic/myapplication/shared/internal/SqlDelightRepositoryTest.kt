package com.automattic.myapplication.shared.internal

import com.automattic.myapplication.shared.internal.database.PlatformDriverFactory
import shared.internal.database.DatabaseTests
import kotlin.test.Test

internal class SqlDelightRepositoryTest {

    @Test
    fun runTestSuite() {
        DatabaseTests().runTestSuite(PlatformDriverFactory())
    }
}
