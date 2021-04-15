package com.lain.colorlistroom

import android.app.Application
import com.lain.colorlistroom.database.ColorsDatabase
import com.lain.colorlistroom.repository.ColorRepository

class Application : Application() {

    val database by lazy { ColorsDatabase.getDatabase(this) }
    val repository by lazy { ColorRepository(database.colorDao()) }
}