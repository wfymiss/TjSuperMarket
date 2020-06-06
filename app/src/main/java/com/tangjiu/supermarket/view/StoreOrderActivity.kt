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
import com.tangjiu.supermarket.dao.AppDatabase
import com.tangjiu.supermarket.dao.GoodsDao
import com.tangjiu.supermarket.dao.OrderMainDao
import com.tangjiu.supermarket.model.DeliveryOrderBean
import com.tangjiu.supermarket.model.OrderBean
import com.tangjiu.supermarket.net.ApiServier
import com.tangjiu.supermarket.net.SoapCallback
import com.tangjiu.supermarket.net.Webservice
import com.tangjiu.supermarket.utils.ActivityToActivity
import kotlinx.android.synthetic.main.activity_store_order.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

class StoreOrderActivity : AppCompatActivity() {
    val handler = Handler(Looper.getMainLooper())
    var list: MutableList<DeliveryOrderBean> = ArrayList()
    var adapter: StoreOrderAdapter? = null
    var goodsDao: OrderMainDao = AppDatabase.getInstance(this).OrderMainDao()
    var item: DeliveryOrderBean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_order)
        orderRv.layoutManager = LinearLayoutManager(this)
        adapter = StoreOrderAdapter(list)
        orderRv.adapter = adapter
        list = goodsDao.getAllCollectedOrder()
        if (list.size > 0) {
            adapter?.setNewData(list)
        }
        statuTv.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this, statuTv)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_all -> {
                        list = goodsDao.getAllCollectedOrder()
                    }
                    R.id.action_Confirmed -> {
                        list = goodsDao.selectOrderByState(2)
                    }
                    R.id.action_Unconfirmed -> {
                        list = goodsDao.selectOrderByState(0)
                    }

                    R.id.action_Received -> {
                        list = goodsDao.selectOrderByState(1)
                    }

                }
                statuTv.text = item.title
                adapter?.setNewData(list)
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
        btn_order.setOnClickListener {
            OrderConfirm(item?.DeliveryOrderID)
        }
        btn_orderdetail.setOnClickListener {
            var Pam: HashMap<String, Any> = HashMap()
         //   Pam.put("numb",)
            ActivityToActivity.toActivity(
                this@StoreOrderActivity,
                OrderListActivity::class.java
            )
        }
        adapter?.setOnItemClickListener { adapter, view, position ->
            item = list[position]
            adapter
            when (list.get(position)?.State) {
                0 -> {
                    btn_order.isEnabled = true
                    btn_start.isEnabled = false
                }
                1
                -> {
                    btn_order.isEnabled = false
                    btn_start.isEnabled = true
                }


            }
        }

    }

    private fun OrderConfirm(deliveryOrderID: Int?) {
        GlobalScope.launch {
            Webservice.getInstance(this@StoreOrderActivity)
                .OrderConfirm(
                    ApiServier.UrlOrderConfirm,
                    deliveryOrderID!!,
                    object : SoapCallback<String> {
                        override fun onResponseResult(mData: String?) {
                            var orderBean = Gson().fromJson<OrderBean>(mData, OrderBean::class.java)
                            if (orderBean.Result.equals("ok")) {
                                item?.State = 1
                                item?.let {
                                    goodsDao.update(item!!)
                                }
                                handler.post {
                                    list = goodsDao.getAllCollectedOrder()
                                    adapter?.setNewData(list)
                                }
                            }

                        }

                        override fun onFailResult(code: Int) {
                        }

                    })
        }


    }


}