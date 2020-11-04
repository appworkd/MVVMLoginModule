package com.appwork.mvvmloginmodule.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appwork.mvvmloginmodule.data.repository.LoginRepository

/**
 * Created by Vivek Kumar belongs to APP WORK  on 04-11-2020.
 */
class LoginVMFactory(private val repository: LoginRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}