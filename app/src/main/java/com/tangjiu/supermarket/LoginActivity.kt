package com.tangjiu.supermarket

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.tangjiu.supermarket.model.User
import com.tangjiu.supermarket.model.UserInfoUtils
import com.tangjiu.supermarket.net.ApiServier.Companion.UrlConnectTest
import com.tangjiu.supermarket.net.ApiServier.Companion.UrlUserVerify
import com.tangjiu.supermarket.net.SoapCallback
import com.tangjiu.supermarket.net.Webservice
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener {
            if (et_password.text.toString() != null && et_username.text.toString() != null) {
                tologin()
            } else {
                Toast.makeText(this, "请输入账号密码", Toast.LENGTH_SHORT).show()
            }
        }


    }

    /*
    *  login
    * */
    private fun tologin() {
        GlobalScope.launch {
            Webservice.getInstance(this@LoginActivity).login(
                UrlUserVerify,
                et_password.text.toString(),
                et_username.text.toString(),
                object : SoapCallback<String> {
                    override fun onResponseResult(mData: String?) {
                        handler.post {
                            var user = Gson().fromJson(mData, User::class.java)
                            UserInfoUtils.saveUser(user)
                        }
                    }

                    override fun onFailResult(code: Int) {
                        handler.post {
                            Toast.makeText(this@LoginActivity, code.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFinally() {
                    }

                })

        }
    }


}
