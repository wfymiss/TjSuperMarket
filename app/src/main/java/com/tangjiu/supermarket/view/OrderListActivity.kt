package com.tangjiu.supermarket.view

import android.graphics.Canvas
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.tangjiu.supermarket.R
import com.tangjiu.supermarket.adapter.OrderListAdapter
import com.tangjiu.supermarket.dao.AppDatabase
import com.tangjiu.supermarket.dao.OrderDao
import com.tangjiu.supermarket.model.OrderBean
import com.tangjiu.supermarket.model.OrderDetailBean
import com.tangjiu.supermarket.net.ApiServier
import com.tangjiu.supermarket.net.SoapCallback
import com.tangjiu.supermarket.net.Webservice
import kotlinx.android.synthetic.main.activity_order_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OrderListActivity : AppCompatActivity() {
    val handler = Handler(Looper.getMainLooper())
    val orderDao: OrderDao =
        AppDatabase.getInstance(this@OrderListActivity).orderDao()
    var list = mutableListOf<OrderDetailBean>()
    var adapter: OrderListAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)
        orderRv.layoutManager = LinearLayoutManager(this)
        adapter = OrderListAdapter(list)
        orderRv.adapter = adapter
        var orderlist = orderDao.getAllCollectedOrder()
        if (!orderlist.isNullOrEmpty()) {
            orderlist?.let {
                adapter?.setNewData(orderlist)
            }
        } else {
            getdata()
        }
    }

    private fun getdata() {
        GlobalScope.launch {
            Webservice.getInstance(this@OrderListActivity)
                .GetStoreOrders(ApiServier.UrlGetStoreOrders, "888", object : SoapCallback<String> {
                    override fun onResponseResult(mData: String?) {
                        var orderBean = Gson().fromJson<OrderBean>(mData, OrderBean::class.java)
                        handler.post {
                            list.clear()
                            adapter?.setNewData(orderBean.OrderDetail)
                            orderDao.insertAll(orderBean.OrderDetail)
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
