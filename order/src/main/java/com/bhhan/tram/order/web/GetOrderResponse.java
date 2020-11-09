package com.bhhan.tram.order.web;

import com.bhhan.tram.order.common.OrderState;
import com.bhhan.tram.order.common.RejectionReason;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
public class GetOrderResponse {
    private Long orderId;
    private OrderState orderState;
    private RejectionReason rejectionReason;

    public GetOrderResponse() {
    }

    public GetOrderResponse(Long orderId, OrderState orderState, RejectionReason rejectionReason) {
        this.orderId = orderId;
        this.orderState = orderState;
        this.rejectionReason = rejectionReason;
    }
}
