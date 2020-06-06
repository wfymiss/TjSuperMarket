package com.tangjiu.supermarket.dao

import androidx.room.*
import com.tangjiu.supermarket.model.GoodsBean

@Dao
interface GoodsDao:BaseDao<GoodsBean> {

    /**
     * 获取所有Order
     */
    @Query("SELECT * FROM `Goods`")
    fun getAllCollectedOrder(): List<GoodsBean>


    /**
     * 根据id获取Order
     */
    @Query("SELECT * FROM `Goods` where GoodsCode = :id ")
    fun selectOrder(id: Int): GoodsBean

    //删全部
    @Query("DELETE FROM Goods")
    fun deleteAll()
}