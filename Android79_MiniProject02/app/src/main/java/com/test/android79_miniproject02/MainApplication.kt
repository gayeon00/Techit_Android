package com.test.android79_miniproject02

import android.app.Application

class MainApplication : Application() {
    class Constants {
        companion object {
            const val FILE_NAME = "password.dat"
        }
    }
}