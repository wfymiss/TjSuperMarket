package com.tangjiu.supermarket.model

/**
 *
 * @ProjectName: TjSuperMarket
 * @Package: com.tangjiu.supermarket.model
 * @ClassName: OrderBean
 * @Description:
 * @Author: 付国勇
 * @CreateDate: 2020/6/3 15:14
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/3 15:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
data class OrderBean(
    val OrderMain: List<OrderDetailBean>,
    val OrderDetail: List<OrderDetailBean>,
    val Result: String
)