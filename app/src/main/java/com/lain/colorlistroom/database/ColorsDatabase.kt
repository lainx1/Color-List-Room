package com.lain.colorlistroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lain.colorlistroom.dao.ColorDao
import com.lain.colorlistroom.entity.Color

@Database(entities = [Color::class], version = 1, exportSchema = false)
abstract class ColorsDatabase : RoomDatabase(){

    abstract fun colorDao() : ColorDao

    companion object{

        // Singleton prevents multiple instances of database opening at the
        // same time.

        @Volatile
        private var INSTANCE : ColorsDatabase? = null

        // if the INSTANCE is not null, then return it,
        // if it is, then create the database
        fun getDatabase(context: Context) : ColorsDatabase = INSTANCE ?: synchronized(this){

            val instance = Room.databaseBuilder(
                context.applicationContext,
                ColorsDatabase::class.java,
                "colors_database"
                ).build()

            INSTANCE = instance

            instance
        }

    }

}