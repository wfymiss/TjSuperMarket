package com.tangjiu.supermarket.adapter

/**
 *
 * @ProjectName: TjSuperMarket
 * @Package: com.tangjiu.supermarket.adapter
 * @ClassName: OrderListAdapter
 * @Description:
 * @Author: 付国勇
 * @CreateDate: 2020/6/3 13:21
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/3 13:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

import android.graphics.Color
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tangjiu.supermarket.R
import com.tangjiu.supermarket.model.DeliveryOrderBean
import com.tangjiu.supermarket.model.OrderDetailBean

class StoreOrderAdapter(data: MutableList<DeliveryOrderBean>) :
    BaseQuickAdapter<DeliveryOrderBean, BaseViewHolder>(R.layout.store_order_list_item, data) {
    override fun convert(helper: BaseViewHolder, item: DeliveryOrderBean) {
        helper.setText(R.id.goodcodeTV, item.Num)
            .setText(R.id.goodunitTV, item.DeliveryOrderTime)
            .setText(R.id.goodnumbTV, item.Qty.toString())
            .setText(R.id.goodpartsTv, item.Total.toString())
            .setText(R.id.goodpriceTv, item.Line)
            .setText(R.id.goodTypeTv, item.FinishTime)
        when (item.State) {
            0 -> {
                helper.setText(R.id.goodDistributionunitTv, "未收货")
                helper.setBackgroundColor(R.id.item_LL, Color.RED)
            }
            1 -> {
                helper.setText(R.id.goodDistributionunitTv, "已收货")
                helper.setBackgroundColor(R.id.item_LL, Color.BLUE)
            }
            2 -> {
                helper.setText(R.id.goodDistributionunitTv, "已确认")
                helper.setBackgroundColor(R.id.item_LL, Color.YELLOW)
            }
        }
        when (item.Warehouse) {
            "00" -> helper.setText(R.id.goodNameTV, "常温")
            "L" -> helper.setText(R.id.goodNameTV, "冷藏")
            "H" -> helper.setText(R.id.goodNameTV, "冷冻")
        }

    }
}