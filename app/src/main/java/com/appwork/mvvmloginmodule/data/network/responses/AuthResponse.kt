package com.appwork.mvvmloginmodule.data.network.responses

import com.appwork.mvvmloginmodule.db.entities.User

data class AuthResponse (
    var isSuccessful :Boolean?,
    var message:String?,
    var user:User?
)