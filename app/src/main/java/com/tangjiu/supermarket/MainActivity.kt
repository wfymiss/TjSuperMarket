package com.tangjiu.supermarket

import android.app.ProgressDialog
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.Printer
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tangjiu.supermarket.base.BaseActivity
import com.tangjiu.supermarket.dao.AppDatabase
import com.tangjiu.supermarket.dao.GoodsDao
import com.tangjiu.supermarket.dao.OrderDao
import com.tangjiu.supermarket.dao.OrderMainDao
import com.tangjiu.supermarket.model.BaseResponse
import com.tangjiu.supermarket.model.GoodsBean
import com.tangjiu.supermarket.model.OrderBean
import com.tangjiu.supermarket.net.ApiServier
import com.tangjiu.supermarket.net.SoapCallback
import com.tangjiu.supermarket.net.Webservice
import com.tangjiu.supermarket.utils.ActivityToActivity
import com.tangjiu.supermarket.view.OrderListActivity
import com.tangjiu.supermarket.view.SettingActivity
import com.tangjiu.supermarket.view.StoreOrderActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : BaseActivity() {
    //商品资料
    val goodsDao: GoodsDao = AppDatabase.getInstance(this).GoodsDao()

    //订单主表
    val OrderMainDao: OrderMainDao = AppDatabase.getInstance(this).OrderMainDao()

    //订单商品明细表
    val orderDao: OrderDao = AppDatabase.getInstance(this).orderDao()


    val handler = Handler(Looper.getMainLooper())

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
//        var goods = goodsDao.getAllCollectedOrder()
//        if (goods.isNotEmpty()) {
//            goods.
//        } else {
//
//        }
        GetGoodsData()
        GetOrderData()
        orderTv.setOnClickListener {
            GetOrderData()
        }
        goodTv.setOnClickListener {
            ActivityToActivity.toActivity(this, StoreOrderActivity::class.java)
        }
        settingTv.setOnClickListener {
            ActivityToActivity.toActivity(this, SettingActivity::class.java)
        }
    }

    private fun GetOrderData() {
        showDialog("正在下载订单信息，请稍后...")
        GlobalScope.launch {
            Webservice.getInstance(this@MainActivity)
                .GetStoreOrders(ApiServier.UrlGetStoreOrders, "888", object : SoapCallback<String> {
                    override fun onResponseResult(mData: String?) {
                        dissDialogprocess()
                        var orderBean = Gson().fromJson<OrderBean>(mData, OrderBean::class.java)
                        if (orderBean.Result.equals("ok")) {
                            orderDao.deleteAll()
                            OrderMainDao.deleteAll()
                            orderDao.insertAll(orderBean.OrderDetail)
                            OrderMainDao.insertAll(orderBean.OrderMain)
                        } else {
                            handler.post {
                                Toast.makeText(
                                    this@MainActivity,
                                    orderBean.Result,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    override fun onFailResult(code: Int) {
                    }
                })
        }
    }

    private fun GetGoodsData() {
        var process = ProgressDialog(this)
        process?.setMessage(" 正在下载商品资料，请稍后...")
        process?.show()
        GlobalScope.launch {
            Webservice.getInstance(this@MainActivity)
                .httpURLGetConnection(ApiServier.UrlGetGoods1, object : SoapCallback<String> {
                    override fun onResponseResult(mData: String?) {
                        var orderBean =
                            Gson().fromJson<BaseResponse<GoodsBean>>(
                                mData, object : TypeToken<BaseResponse<GoodsBean>>() {}.type
                            )
                        if (orderBean.Result.equals("ok")) {
                            goodsDao.deleteAll()
                            goodsDao.insertAll(orderBean.Goods)
                        }
                        process.dismiss()
                    }

                    override fun onFailResult(code: Int) {
                        TODO("Not yet implemented")
                    }


                })

        }
    }


}
