package com.bhhan.tram.order.saga;

import com.bhhan.tram.order.common.OrderDetails;
import com.bhhan.tram.order.common.RejectionReason;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
@NoArgsConstructor
public class CreateOrderSagaData {
    private OrderDetails orderDetails;
    private Long orderId;
    private RejectionReason rejectionReason;

    public CreateOrderSagaData(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}
