package com.bhhan.tram.customer.api.command;

import com.bhhan.tram.common.Money;
import io.eventuate.tram.commands.common.Command;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
public class ReserveCreditCommand implements Command {
    private Long orderId;
    private Money orderTotal;
    private long customerId;

    public ReserveCreditCommand() {
    }

    public ReserveCreditCommand(Long customerId, Long orderId, Money orderTotal) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.orderTotal = orderTotal;
    }
}
