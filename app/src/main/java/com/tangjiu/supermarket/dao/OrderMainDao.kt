package com.tangjiu.supermarket.dao

import androidx.room.*
import com.tangjiu.supermarket.model.DeliveryOrderBean
import com.tangjiu.supermarket.model.OrderDetailBean

@Dao
interface OrderMainDao : BaseDao<DeliveryOrderBean> {

    /**
     * 获取所有Order
     */
    @Query("SELECT * FROM `DeliveryOrder`")
    fun getAllCollectedOrder(): MutableList<DeliveryOrderBean>


    /**
     * 根据id获取Order
     */
    @Query("SELECT * FROM `DeliveryOrder` where DeliveryOrderID = :id ")
    fun selectOrder(id: Int): DeliveryOrderBean

    /**
     * 根据State获取Order
     */
    @Query("SELECT * FROM `DeliveryOrder` where State = :State ")
    fun selectOrderByState(State: Int): MutableList<DeliveryOrderBean>


    //删全部
    @Query("DELETE FROM DeliveryOrder")
    fun deleteAll()
}