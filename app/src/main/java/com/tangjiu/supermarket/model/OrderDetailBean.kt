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
data class OrderDetailBean(
    val DeliveryOrderID: Int,
    val Num: String,
    val Line: String,
    val JobID: String,
    val DeliveryOrderTime: String,
    val StoreCode: String,
    val StoreName: String,
    val GoodsCode: String,
    val GoodsName: String,
    val Unit: String,
    val Barcode: String,
    val Price: Double,
    val PickingUnit: Double,
    val BatchNumber: String,
    val ShelfLife: Int,
    val DeliverType: String,
    val DeliveryOrderDetailsID: Int,
    val ReceiveQty: Double,
    val Qty: Double,
    val PackageQty: Double,
    val Total: Double,
    val Warehouse: String,
    val Remark: String,
    val State: Int,
    val FinishTime: String,
    val Operator: String
)