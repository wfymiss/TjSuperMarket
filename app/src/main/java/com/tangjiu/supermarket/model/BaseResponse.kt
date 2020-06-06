package com.tangjiu.supermarket.model

/**
 *
 * @ProjectName: TjSuperMarket
 * @Package: com.tangjiu.supermarket.model
 * @ClassName: BaseResponse
 * @Description:
 * @Author: 付国勇
 * @CreateDate: 2020/6/4 10:42
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/4 10:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
data class BaseResponse<T>(val Result: String = "", val Goods: MutableList<T>)