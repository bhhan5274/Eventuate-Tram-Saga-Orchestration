package com.bhhan.tram.customer.service;

import com.bhhan.tram.common.Money;
import com.bhhan.tram.customer.domain.Customer;
import com.bhhan.tram.customer.domain.CustomerCreditLimitExceededException;
import com.bhhan.tram.customer.domain.CustomerNotFoundException;
import com.bhhan.tram.customer.domain.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(String name, Money creditLimit) {
        Customer customer = new Customer(name, creditLimit);
        return customerRepository.save(customer);
    }

    public void reserveCredit(long customerId, long orderId, Money orderTotal) throws CustomerCreditLimitExceededException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
        customer.reserveCredit(orderId, orderTotal);
    }
}
