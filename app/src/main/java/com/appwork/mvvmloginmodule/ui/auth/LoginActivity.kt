package com.appwork.mvvmloginmodule.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.appwork.mvvmloginmodule.R
import com.appwork.mvvmloginmodule.databinding.ActivityLoginBinding
import com.appwork.mvvmloginmodule.db.entities.User
import com.appwork.mvvmloginmodule.utils.hide
import com.appwork.mvvmloginmodule.utils.show
import com.appwork.mvvmloginmodule.utils.showSnack
import com.appwork.mvvmloginmodule.utils.showToast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edit_text_email
import kotlinx.android.synthetic.main.activity_login.edit_text_password
import kotlinx.android.synthetic.main.activity_login.progress_bar
import kotlinx.android.synthetic.main.activity_sign_up.*

class LoginActivity : AppCompatActivity(), LoginAuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginBinding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginBinding.viewModelLogin = loginViewModel
        loginViewModel.authListener = this
    }

    override fun onStartAuth() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        root_layout.showSnack("${user.name} is Logged In")
    }

    override fun onFinished() {
        showToast("Finished")
    }

    override fun onError(errorMessage: String) {
        progress_bar.hide()
        showToast(errorMessage)
    }
}