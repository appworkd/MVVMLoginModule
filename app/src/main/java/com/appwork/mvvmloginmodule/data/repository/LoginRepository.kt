package com.appwork.mvvmloginmodule.data.repository

import com.appwork.mvvmloginmodule.data.network.ApiRequests
import com.appwork.mvvmloginmodule.data.network.SafeApiRequest
import com.appwork.mvvmloginmodule.data.network.responses.AuthResponse
import retrofit2.Response

class LoginRepository : SafeApiRequest() {
    //Suspend keyword is used for coroutines and can pause and restart the functionality
    suspend fun loginUser(
        email: String,
        password: String
    ): AuthResponse {
        return apiRequest {
            ApiRequests().loginRequest(email, password)
        }
    }
}