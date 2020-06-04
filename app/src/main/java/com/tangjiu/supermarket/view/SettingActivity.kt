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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        netWorkTv.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this, statuTv)
            popupMenu.menuInflater.inflate(R.menu.net_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_Unmobile ->
                        statuTv.text=item.title
                    R.id.action_mobile ->
                        statuTv.text=item.title
                }
                true
            })
            popupMenu.show()

        }
    }
}
