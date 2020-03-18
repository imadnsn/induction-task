package com.progressoft.induction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {

    public static final Money ZERO = new Money(BigDecimal.ZERO);
    public static final Money DINAR = new Money(BigDecimal.ONE);
    public static final Money HALF_DINAR = new Money(BigDecimal.valueOf(0.5));
    public static final Money QUARTER_DINAR = new Money(BigDecimal.valueOf(0.25));

    private final BigDecimal amount; // Money is immutable
    public Money(BigDecimal amount) {
        if(amount == null)
            throw new IllegalArgumentException("Money amount cannot be null.");
        if(amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Money amount cannot be negative.");
        this.amount = amount.setScale(3, RoundingMode.HALF_UP); // ensures equals and hashcode behaviour is value-based
    }

    public Money add(Money other){
        if(other == null) {
            throw new IllegalArgumentException("Money to add cannot be null.");
        }
        return new Money(amount.add(other.amount));
    }

    public Money subtract(Money other){
        if(other == null) {
            throw new IllegalArgumentException("Money to add cannot be null.");
        }
        if(isLessThan(other)) {
            throw new IllegalArgumentException("Cannot subtract from a smaller Money amount");
        }
        return new Money(amount.subtract(other.amount));
    }

    public boolean isEqualTo(Money other) { // uses compareTo instead of equals
        if(other == null) {
            return false;
        }
        return amount.compareTo(other.amount) == 0;
    }

    public boolean isLessThan(Money other) {
        if(other == null) {
            return false;
        }
        return amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThan(Money other) {
        if(other == null) {
            return false;
        }
        return amount.compareTo(other.amount) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
