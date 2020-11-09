package com.bhhan.tram.customer.domain;

import com.bhhan.tram.common.Money;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Collections;
import java.util.Map;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name="Customer")
@Access(AccessType.FIELD)
@Slf4j
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    private Money creditLimit;

    @ElementCollection
    private Map<Long, Money> creditReservations;

    @Version
    private Long version;

    Money availableCredit() {
        return creditLimit.substract(creditReservations.values().stream().reduce(Money.ZERO, Money::add));
    }

    public Customer() {
    }

    public Customer(String name, Money creditLimit) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.creditReservations = Collections.emptyMap();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void reserveCredit(Long orderId, Money orderTotal) {
        if (availableCredit().isGreaterThanOrEqual(orderTotal)) {
            creditReservations.put(orderId, orderTotal);
        } else
            throw new CustomerCreditLimitExceededException();
    }

    @Override
    public String toString() {
        printCreditReservations();
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creditLimit=" + creditLimit +
                ", version=" + version +
                '}';
    }

    private void printCreditReservations(){
        creditReservations
                .forEach((aLong, money) -> log.info(aLong + " : " + money.toString()));
    }
}
