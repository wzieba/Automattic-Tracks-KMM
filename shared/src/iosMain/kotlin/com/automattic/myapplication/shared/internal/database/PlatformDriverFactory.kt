package com.automattic.myapplication.shared.internal.database

import com.automattic.myapplication.shared.EventsDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

internal actual class PlatformDriverFactory : DriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(EventsDatabase.Schema, "events.db")
    }
}
