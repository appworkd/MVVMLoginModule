package com.appwork.mvvmloginmodule.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appwork.mvvmloginmodule.db.entities.CURRENT_USER_ID
import com.appwork.mvvmloginmodule.db.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(user: User): Long

    @Query("SELECT * FROM user WHERE uId=$CURRENT_USER_ID")
    fun getUserData(): LiveData<User>
}