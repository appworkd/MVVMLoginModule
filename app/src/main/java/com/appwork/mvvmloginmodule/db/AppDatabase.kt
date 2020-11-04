package com.appwork.mvvmloginmodule.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appwork.mvvmloginmodule.db.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gerUserDao(): UserDao

    companion object {
        //Volatile keyword used so any thread can access the instance
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()
        //Lock is used so only single instance will be created
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance?: createDatabase(context).also {
                instance=it
            }
        }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java,
            "MyDataBase.db")
                .build()
    }
}