package com.tangjiu.supermarket

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tangjiu.supermarket.net.ApiServier.Companion.UrlConnectTest
import com.tangjiu.supermarket.net.SoapCallback
import com.tangjiu.supermarket.net.Webservice
import com.tangjiu.supermarket.utils.ActivityToActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val handler = Handler(Looper.getMainLooper())
        GlobalScope.launch {
            Webservice.getInstance(this@MainActivity).httpURLGetConnection(UrlConnectTest, object : SoapCallback<String> {
                override fun onResponseResult(mData: String?) {
                    handler.post {
                        ActivityToActivity.toActivity(this@MainActivity, LoginActivity::class.java)
                    }
                }

                override fun onFailResult(code: Int) {
                    Toast.makeText(this@MainActivity, code.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onFinally() {
                }

            })

        }


    }


}
