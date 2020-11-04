package com.appwork.mvvmloginmodule.data.repository

import com.appwork.mvvmloginmodule.data.network.ApiRequests
import com.appwork.mvvmloginmodule.data.network.SafeApiRequest
import com.appwork.mvvmloginmodule.data.network.responses.AuthResponse
import com.appwork.mvvmloginmodule.db.AppDatabase
import com.appwork.mvvmloginmodule.db.entities.User
import retrofit2.Response

class LoginRepository(
    private val api: ApiRequests,
    private val db: AppDatabase
) : SafeApiRequest() {
    //Suspend keyword is used for coroutines and can pause and restart the functionality
    suspend fun loginUser(email: String, password: String): AuthResponse {
        return apiRequest { api.loginRequest(email, password) }
    }

    suspend fun insertUser(user: User) = db.gerUserDao().insertOrUpdateUser(user)

     fun getUser()=db.gerUserDao().getUserData()
}