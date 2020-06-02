package com.tangjiu.supermarket.model

import com.google.gson.Gson
import com.tencent.mmkv.MMKV

/**
 *
 * @ProjectName: TjSuperMarket
 * @Package: com.tangjiu.supermarket.model
 * @ClassName: UserInfoUtils
 * @Description:
 * @Author: 付国勇
 * @CreateDate: 2020/6/2 17:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/2 17:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
object UserInfoUtils {
    /**
     * 保存用户信息
     *
     * @param user user
     */
    fun saveUser(user: User?) {
        if (user != null) {
            MMKV.defaultMMKV().encode("userInfo", Gson().toJson(user))
        }
    }

    /**
     * 获取用户信息
     *
     * @return user
     */
    @JvmStatic
    val user: User
        get() = Gson().fromJson(MMKV.defaultMMKV().decodeString("userInfo"), User::class.java)


    @JvmStatic
    val userForSplash: User?
        get() = Gson().fromJson(MMKV.defaultMMKV().decodeString("userInfo"), User::class.java)

    /**
     * 清除用户信息
     */
    fun cleanUser() {
        MMKV.defaultMMKV().removeValueForKey("userInfo")
    }
}