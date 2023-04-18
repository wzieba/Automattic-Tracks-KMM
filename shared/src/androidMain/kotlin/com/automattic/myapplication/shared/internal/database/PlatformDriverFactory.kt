package com.automattic.myapplication.shared.internal.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.automattic.EventsDatabase

internal actual class PlatformDriverFactory(private val context: Context) : DriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = EventsDatabase.Schema,
            context = context,
            "events.db"
        )
    }
}
