package com.tangjiu.supermarket

import android.app.Application
import com.tangjiu.supermarket.dao.AppDatabase
import com.tencent.mmkv.MMKV
import kotlin.properties.Delegates

/**
 *
 * @ProjectName: TjSuperMarket
 * @Package: com.tangjiu.supermarket
 * @ClassName: application
 * @Description:
 * @Author: 付国勇
 * @CreateDate: 2020/6/2 17:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/2 17:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class MyApplication : Application() {

    companion object {

        private var instance:MyApplication by Delegates.notNull()

        fun instance() = instance!!
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        MMKV.initialize(this)
    }

}