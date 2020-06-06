package com.tangjiu.supermarket.view

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.tangjiu.supermarket.MainActivity
import com.tangjiu.supermarket.R
import com.tangjiu.supermarket.base.BaseActivity
import com.tangjiu.supermarket.model.User
import com.tangjiu.supermarket.model.UserInfoUtils
import com.tangjiu.supermarket.net.ApiServier
import com.tangjiu.supermarket.net.ApiServier.Companion.UrlUserVerify
import com.tangjiu.supermarket.net.SoapCallback
import com.tangjiu.supermarket.net.Webservice
import com.tangjiu.supermarket.utils.ActivityToActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {
    val handler = Handler(Looper.getMainLooper())
    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initView() {
        btn_login.setOnClickListener {
            if (!et_password.text.toString().isNullOrEmpty() && !et_username.text.toString()
                    .isNullOrEmpty()
            ) {
                vervifylogin()
            } else {
                Toast.makeText(this, "请输入账号密码", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun vervifylogin() {
        GlobalScope.launch {
            Webservice.getInstance(this@LoginActivity).login(
                UrlUserVerify,
                et_password.text.toString(),
                et_username.text.toString(),
                object : SoapCallback<String> {
                    override fun onResponseResult(mData: String?) {
                        var user = Gson().fromJson(mData, User::class.java)
                        if (user.UserName.isNotEmpty()) {
                            UserInfoUtils.saveUser(user)
                            ActivityToActivity.toActivity(
                                this@LoginActivity,
                                MainActivity::class.java
                            )
                            finish()
                        } else {

                            ActivityToActivity.toActivity(
                                this@LoginActivity,
                                MainActivity::class.java
                            )

//                        handler.post {
//                            Toast.makeText(this@LoginActivity, "登录失败", Toast.LENGTH_SHORT)
//                                .show()
//                        }
                        }

                    }

                    override fun onFailResult(code: Int) {
                        handler.post {
                            Toast.makeText(this@LoginActivity, code.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                })

        }
    }

}
