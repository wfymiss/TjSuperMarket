package com.tangjiu.supermarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tangjiu.supermarket.utils.ActivityToActivity
import com.tangjiu.supermarket.view.OrderListActivity
import com.tangjiu.supermarket.view.StoreOrderActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        orderTv.setOnClickListener {
            ActivityToActivity.toActivity(this, OrderListActivity::class.java)
        }
        goodTv.setOnClickListener {
            ActivityToActivity.toActivity(this, StoreOrderActivity::class.java)
        }
    }


}
