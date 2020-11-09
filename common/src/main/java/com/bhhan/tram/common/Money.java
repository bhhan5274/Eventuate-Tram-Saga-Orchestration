package com.bhhan.tram.common;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by hbh5274@gmail.com on 2020-11-09
 * Github : http://github.com/bhhan5274
 */

@Embeddable
@Access(AccessType.FIELD)
@Getter
@Setter
public class Money {

    public static Money ZERO = new Money(0L);

    private BigDecimal amount;

    private Money(){}

    public Money(BigDecimal amount){
        this.amount = amount;
    }

    public Money(String amount){
        this.amount = new BigDecimal(amount);
    }

    public Money(int amount){
        this.amount = new BigDecimal(amount);
    }

    public Money(Long amount){
        this.amount = new BigDecimal(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount.doubleValue(), money.amount.doubleValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    public Money add(Money other){
        return new Money(amount.add(other.amount));
    }

    public Money substract(Money other){
        return new Money(amount.subtract(other.amount));
    }

    public boolean isGreaterThanOrEqual(Money other){
        return amount.compareTo(other.amount) >= 0;
    }

    public String asString(){
        return amount.toPlainString();
    }

    public Money multiply(int ratio){
        return new Money(amount.multiply(BigDecimal.valueOf(ratio)));
    }
}
