package com.tangjiu.supermarket.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.tangjiu.supermarket.R
import com.tangjiu.supermarket.adapter.StoreOrderAdapter
import com.tangjiu.supermarket.model.DeliveryOrderBean
import com.tangjiu.supermarket.model.OrderBean
import com.tangjiu.supermarket.net.ApiServier
import com.tangjiu.supermarket.net.SoapCallback
import com.tangjiu.supermarket.net.Webservice
import com.tangjiu.supermarket.utils.ActivityToActivity
import kotlinx.android.synthetic.main.activity_store_order.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StoreOrderActivity : AppCompatActivity() {
    val handler = Handler(Looper.getMainLooper())
    var list = mutableListOf<DeliveryOrderBean>()
    var adapter: StoreOrderAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_order)
        orderRv.layoutManager = LinearLayoutManager(this)
        adapter = StoreOrderAdapter(list)
        orderRv.adapter = adapter
        getdata()
        statuTv.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this, statuTv)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_all ->
                        statuTv.text=item.title
                    R.id.action_Confirmed ->
                        statuTv.text=item.title
                    R.id.action_Unconfirmed ->
                        statuTv.text=item.title
                    R.id.action_Received ->
                        statuTv.text=item.title
                }
                true
            })
            popupMenu.show()
        }

        btn_start.setOnClickListener {
            ActivityToActivity.toActivity(
                this@StoreOrderActivity,
                ConfirmReceiptActivity::class.java
            )
        }
    }

    private fun getdata() {
        GlobalScope.launch {
            Webservice.getInstance(this@StoreOrderActivity)
                .GetStoreOrders(ApiServier.UrlGetStoreOrders, "888", object : SoapCallback<String> {
                    override fun onResponseResult(mData: String?) {
                        var orderBean = Gson().fromJson<OrderBean>(mData, OrderBean::class.java)
                        handler.post {
                            list.clear()
                            adapter?.setNewData(orderBean.OrderMain)
                        }
                    }
                    override fun onFailResult(code: Int) {
                    }

                    override fun onFinally() {
                    }

                })
        }

    }

}