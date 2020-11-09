package com.bhhan.tram.order.web;

import com.bhhan.tram.common.Money;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
public class CreateOrderRequest {
    private Money orderTotal;
    private Long customerId;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(Long customerId, Money orderTotal) {
        this.customerId = customerId;
        this.orderTotal = orderTotal;
    }
}
