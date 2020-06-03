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
import com.tangjiu.supermarket.model.OrderDetailBean

class OrderListAdapter(data: List<OrderDetailBean>) :
    BaseQuickAdapter<OrderDetailBean, BaseViewHolder>(R.layout.order_list_item, data) {
    override fun convert(helper: BaseViewHolder, item: OrderDetailBean) {
        helper.setText(R.id.goodcodeTV, item.GoodsCode)
            .setText(R.id.goodNameTV, item.GoodsName)
            .setText(R.id.goodunitTV, item.Unit)
            .setText(R.id.goodDistributionunitTv, item.DeliveryOrderDetailsID.toString())
            .setText(R.id.goodnumbTV, item.PickingUnit.toString())
            .setText(R.id.goodpartsTv, item.Qty.toString())
            .setText(R.id.goodpriceTv, item.Price.toString())
            .setText(R.id.goodTypeTv, item.DeliverType)
            .setText(R.id.getGoodTV, item.ReceiveQty.toString())
    }
}