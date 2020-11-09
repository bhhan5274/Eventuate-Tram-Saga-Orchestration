package com.bhhan.tram.order.web;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
public class CreateOrderResponse {
    private long orderId;

    public CreateOrderResponse() {
    }

    public CreateOrderResponse(long orderId) {
        this.orderId = orderId;
    }
}
