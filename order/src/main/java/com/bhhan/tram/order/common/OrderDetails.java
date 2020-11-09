package com.bhhan.tram.order.common;

import com.bhhan.tram.common.Money;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@Embeddable
@Getter
@Setter
public class OrderDetails {

    private Long customerId;

    @Embedded
    private Money orderTotal;

    public OrderDetails() {
    }

    public OrderDetails(Long customerId, Money orderTotal) {
        this.customerId = customerId;
        this.orderTotal = orderTotal;
    }
}

