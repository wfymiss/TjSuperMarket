package com.tangjiu.supermarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tangjiu.supermarket.dao.AppDatabase
import com.tangjiu.supermarket.dao.OrderDao
import com.tangjiu.supermarket.utils.ActivityToActivity
import com.tangjiu.supermarket.view.OrderListActivity
import com.tangjiu.supermarket.view.SettingActivity
import com.tangjiu.supermarket.view.StoreOrderActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val orderDao: OrderDao = AppDatabase.getInstance(this).orderDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list = orderDao.getAllCollectedOrder()
        orderTv.setOnClickListener {
            ActivityToActivity.toActivity(this, OrderListActivity::class.java)
        }
        goodTv.setOnClickListener {
            ActivityToActivity.toActivity(this, StoreOrderActivity::class.java)
        }
        settingTv.setOnClickListener {
            ActivityToActivity.toActivity(this, SettingActivity::class.java)
        }
    }


}
