package com.tangjiu.supermarket.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @ProjectName: TjSuperMarket
 * @Package: com.tangjiu.supermarket.model
 * @ClassName: Goods
 * @Description:
 * @Author: 付国勇
 * @CreateDate: 2020/6/4 10:44
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/4 10:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Entity(tableName = "Goods")
data class GoodsBean(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var GoodsCode: String, val Barcode: String
)