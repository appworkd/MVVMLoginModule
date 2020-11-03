package com.appwork.mvvmloginmodule.ui.auth

import androidx.lifecycle.LiveData
import com.appwork.mvvmloginmodule.db.entities.User

interface LoginAuthListener {
    public fun onStartAuth()
    public fun onSuccess(user: User)
    public fun onFinished()
    public fun onError(errorMessage:String)
}