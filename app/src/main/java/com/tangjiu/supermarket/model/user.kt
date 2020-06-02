package com.tangjiu.supermarket.model

/**
 *
 * @ProjectName: TjSuperMarket
 * @Package: com.tangjiu.supermarket.model
 * @ClassName: user
 * @Description:
 * @Author: 付国勇
 * @CreateDate: 2020/6/2 17:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/2 17:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
data class User(
    val StoreCode: String="",
    val StoreName: String="",
    val UserName: String="",
    val ServiceDate: String="",
    val Result: String=""
)