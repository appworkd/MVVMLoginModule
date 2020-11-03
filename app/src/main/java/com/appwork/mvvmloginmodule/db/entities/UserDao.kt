package com.appwork.mvvmloginmodule.db.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateUser(user: User): Long

    @Query("SELECT * FROM user WHERE uId=$CURRENT_USER_ID")
    fun getUserData(): LiveData<User>
}