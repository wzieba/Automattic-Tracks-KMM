package com.automattic.myapplication.shared.internal.database

import android.content.Context
import com.automattic.myapplication.shared.EventsDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

internal actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = EventsDatabase.Schema,
            context = context,
            "events.db"
        )
    }
}
