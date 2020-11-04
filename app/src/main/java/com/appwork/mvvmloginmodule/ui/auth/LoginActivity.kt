package com.appwork.mvvmloginmodule.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.appwork.mvvmloginmodule.R
import com.appwork.mvvmloginmodule.data.network.ApiRequests
import com.appwork.mvvmloginmodule.data.repository.LoginRepository
import com.appwork.mvvmloginmodule.databinding.ActivityLoginBinding
import com.appwork.mvvmloginmodule.db.AppDatabase
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
        val db = AppDatabase(this)
        val api = ApiRequests()
        val repository = LoginRepository(api, db)
        val factory=LoginVMFactory(repository)
        val loginBinding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val loginViewModel = ViewModelProvider(this,factory).get(LoginViewModel::class.java)
        loginBinding.viewModelLogin = loginViewModel
        loginViewModel.authListener = this

        loginViewModel.getLoggedInUser().observe(this, Observer { user->
            if (user!=null){
                val intent = Intent(this,SignUpActivity::class.java).also {
                    it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
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
        //showToast(errorMessage)
        root_layout.showSnack(errorMessage)
    }
}