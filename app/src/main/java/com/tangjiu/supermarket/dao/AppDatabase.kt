package com.tangjiu.supermarket.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tangjiu.supermarket.MyApplication
import com.tangjiu.supermarket.model.OrderDetailBean


@Database(entities = arrayOf(OrderDetailBean::class), version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun orderDao(): OrderDao

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