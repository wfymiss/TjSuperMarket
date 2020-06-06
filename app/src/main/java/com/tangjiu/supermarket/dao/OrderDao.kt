package com.tangjiu.supermarket.dao

import androidx.room.Dao
import androidx.room.Query
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


    //删全部
    @Query("DELETE FROM DeliveryOrderDetails")
    fun deleteAll()
}