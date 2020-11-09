package com.bhhan.tram.customer.web;

import com.bhhan.tram.customer.domain.Customer;
import com.bhhan.tram.customer.domain.CustomerRepository;
import com.bhhan.tram.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        Customer customer = customerService.createCustomer(createCustomerRequest.getName(), createCustomerRequest.getCreditLimit());
        return new CreateCustomerResponse(customer.getId());
    }

    @RequestMapping(value="/customers/{customerId}", method= RequestMethod.GET)
    public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable Long customerId) {
        return customerRepository
                .findById(customerId)
                .map(c -> {
                    log.info("=====================================");
                    log.info(c.toString());
                    log.info("=====================================");
                    return new ResponseEntity<>(new GetCustomerResponse(c.getId(), c.getName(), c.getCreditLimit()), HttpStatus.OK);})
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
