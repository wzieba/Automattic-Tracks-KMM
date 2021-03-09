package com.automattic.myapplication.shared

abstract class UserProvider {
    abstract fun request(): TracksUser
}
