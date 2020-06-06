package com.tangjiu.supermarket.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tangjiu.supermarket.MyApplication
import com.tangjiu.supermarket.model.DeliveryOrderBean
import com.tangjiu.supermarket.model.GoodsBean
import com.tangjiu.supermarket.model.OrderDetailBean


@Database(
    entities = arrayOf(OrderDetailBean::class, GoodsBean::class, DeliveryOrderBean::class),
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    //订单商品明细表
    abstract fun orderDao(): OrderDao


    //商品资料
    abstract fun GoodsDao(): GoodsDao


    //【订单主表】
    abstract fun OrderMainDao(): OrderMainDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                MyApplication.instance(),
                AppDatabase::class.java, "app.db"
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()//迁移数据库如果发生错误，将会重新创建数据库，而不是发生崩溃
                .build()
    }


}