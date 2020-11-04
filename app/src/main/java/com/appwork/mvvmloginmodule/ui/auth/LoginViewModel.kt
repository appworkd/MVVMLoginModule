package com.appwork.mvvmloginmodule.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.appwork.mvvmloginmodule.data.repository.LoginRepository
import com.appwork.mvvmloginmodule.utils.ApiException
import com.appwork.mvvmloginmodule.utils.Coroutines

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    var email: String? = null
    var password: String? = null
    public var authListener: LoginAuthListener? = null

    public fun getLoggedInUser()=repository.getUser()

    public fun authUser(view: View) {
        authListener?.onStartAuth()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            //Error
            authListener?.onError("PLease fill the values.")
            return
        }
        //Success
        Coroutines.main {
            try {
                val authResponse = repository.loginUser(email!!, password!!)
                authResponse.user.let {
                    authListener?.onSuccess(it!!)
                    repository.insertUser(it!!)
                    return@main
                }
                authListener?.onError(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onError(e.message!!)
            }
        }

    }

}