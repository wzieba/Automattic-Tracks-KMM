package com.automattic.myapplication.shared.internal.database

import com.squareup.sqldelight.db.SqlDriver

interface DriverFactory {
    fun createDriver(): SqlDriver
}
