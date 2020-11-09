package com.bhhan.tram.customer.web;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
public class CreateCustomerResponse {
    private Long customerId;

    public CreateCustomerResponse() {
    }

    public CreateCustomerResponse(Long customerId) {
        this.customerId = customerId;
    }
}
