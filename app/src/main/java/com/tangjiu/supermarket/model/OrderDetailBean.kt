package com.tangjiu.supermarket.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import org.jetbrains.annotations.NotNull

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
@Entity(tableName = "DeliveryOrderDetails")
@TypeConverters(Converters::class)
data class OrderDetailBean(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val Num: String=" ",
    val GoodsCode: String=" ",
    val GoodsName: String=" ",
    val Unit: String=" ",
    val Barcode: String=" ",
    val Price: Double,
    val Qty: Double,
    val PickingUnit: Double,
    val BatchNumber: String=" ",
    val ShelfLife: Int,
    val DeliverType: String=" ",
    val DeliveryOrderDetailsID: Int,
    val ReceiveQty: Double,
    val PackageQty: Double

    )