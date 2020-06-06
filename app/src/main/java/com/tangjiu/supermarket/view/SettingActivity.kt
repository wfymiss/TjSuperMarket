package com.tangjiu.supermarket.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.google.gson.Gson
import com.tangjiu.supermarket.MainActivity
import com.tangjiu.supermarket.R
import com.tangjiu.supermarket.model.User
import com.tangjiu.supermarket.model.UserInfoUtils
import com.tangjiu.supermarket.net.ApiServier
import com.tangjiu.supermarket.net.ApiServier.Companion.UrlUserVerify
import com.tangjiu.supermarket.net.SoapCallback
import com.tangjiu.supermarket.net.Webservice
import com.tangjiu.supermarket.utils.ActivityToActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.activity_store_order.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SettingActivity : AppCompatActivity() {
    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        netWorkTv.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this, netWorkTv)
            popupMenu.menuInflater.inflate(R.menu.net_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_Unmobile ->
                        netWorkTv.text = item.title
                    R.id.action_mobile ->
                        netWorkTv.text = item.title
                }
                true
            })
            popupMenu.show()

        }

        btn_netTest.setOnClickListener {
            ConnectTest()
        }

    }


    /*
     *  ConnectTest
     *
     */
    private fun ConnectTest() {
        GlobalScope.launch {
            Webservice.getInstance(this@SettingActivity)
                .httpURLGetConnection(ApiServier.UrlConnectTest, object : SoapCallback<String> {
                    override fun onResponseResult(mData: String?) {
                        handler.post {
                            Toast.makeText(this@SettingActivity, "连接成功", Toast.LENGTH_SHORT).show()
                        }

                    }

                    override fun onFailResult(code: Int) {
                        handler.post {
                            Toast.makeText(
                                this@SettingActivity,
                                code.toString(),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                })

        }

    }
}
