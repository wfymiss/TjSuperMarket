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

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tangjiu.supermarket.R
import com.tangjiu.supermarket.model.DeliveryOrderBean
import com.tangjiu.supermarket.model.OrderDetailBean

class StoreOrderAdapter(data: List<DeliveryOrderBean>) :
    BaseQuickAdapter<DeliveryOrderBean, BaseViewHolder>(R.layout.store_order_list_item, data) {
    override fun convert(helper: BaseViewHolder, item: DeliveryOrderBean) {
        helper.setText(R.id.goodcodeTV, item.DeliveryOrderID.toString())
            .setText(R.id.goodNameTV, item.Warehouse)
            .setText(R.id.goodunitTV, item.DeliveryOrderTime)
            .setText(R.id.goodDistributionunitTv, if (item.State == 0) "未确认" else "已确定")
            .setText(R.id.goodnumbTV, item.Qty.toString())
            .setText(R.id.goodpartsTv, item.Total.toString())
            .setText(R.id.goodpriceTv, item.Line)
            .setText(R.id.goodTypeTv, item.FinishTime)
    }
}