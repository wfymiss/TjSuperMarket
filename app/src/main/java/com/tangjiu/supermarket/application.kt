package com.tangjiu.supermarket

import android.app.Application
import com.tencent.mmkv.MMKV

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
class application : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)
    }

}