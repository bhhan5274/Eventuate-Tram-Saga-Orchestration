package com.bhhan.tram.customer.web;

import com.bhhan.tram.common.Money;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@Getter
@Setter
public class CreateCustomerRequest {
    private String name;
    private Money creditLimit;

    public CreateCustomerRequest() {
    }

    public CreateCustomerRequest(String name, Money creditLimit) {

        this.name = name;
        this.creditLimit = creditLimit;
    }
}
