package com.automattic.myapplication.shared.internal.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.automattic.EventsDatabase

internal actual class PlatformDriverFactory : DriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(EventsDatabase.Schema, "events.db")
    }
}
