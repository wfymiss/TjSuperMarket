package com.tangjiu.supermarket.net;

/**
 * @ProjectName: TjSuperMarket
 * @Package: com.tangjiu.supermarket
 * @ClassName: SoapCallback
 * @Description:
 * @Author: 付国勇
 * @CreateDate: 2020/6/2 11:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/2 11:07
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
 public interface SoapCallback<T> {
    void onResponseResult(T mData);
    void onFailResult(int code);
    void onFinally();
}
