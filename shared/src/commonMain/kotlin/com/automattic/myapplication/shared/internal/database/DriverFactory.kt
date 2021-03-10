package com.automattic.myapplication.shared.internal.database

import com.squareup.sqldelight.db.SqlDriver

internal expect class DriverFactory {
    fun createDriver(): SqlDriver
}
