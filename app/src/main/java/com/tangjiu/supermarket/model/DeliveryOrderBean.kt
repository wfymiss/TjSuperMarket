package com.tangjiu.supermarket.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DeliveryOrder")
data class DeliveryOrderBean (
    @PrimaryKey(autoGenerate = true) val id:Int,
    val DeliveryOrderID: Int,
    val Num: String,
    val Line: String,
    val JobID: String,
    val DeliveryOrderTime: String,
    val StoreCode: String,
    val StoreName: String,
    val Qty: Double,
    val PackageQty: Double,
    val Total: Double,
    val Warehouse: String,
    val Remark: String,
    val State: Int,
    val FinishTime: String,
    val Operator: String
)