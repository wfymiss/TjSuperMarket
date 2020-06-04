package com.tangjiu.supermarket.dao

import androidx.room.*
import com.tangjiu.supermarket.model.OrderDetailBean

@Dao
interface OrderDao:BaseDao<OrderDetailBean> {

    /**
     * 获取所有Order
     */
    @Query("SELECT * FROM `DeliveryOrderDetails`")
    fun getAllCollectedOrder(): List<OrderDetailBean>


    /**
     * 根据id获取Order
     */
    @Query("SELECT * FROM `DeliveryOrderDetails` where DeliveryOrderDetailsID = :id ")
    fun selectOrder(id: Int): OrderDetailBean
}