package com.progressoft.induction;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SnackMachine {
    public static final int DEFAULT_QUANTITY = 10;
    private static final Set<Money> supportedInput = new HashSet<>(
            Arrays.asList(Money.QUARTER_DINAR, Money.HALF_DINAR, Money.DINAR,
                    new Money(BigDecimal.valueOf(5)), new Money(BigDecimal.valueOf(10))));
    private Money balance;
    private Money transBalance;
    private Snack chewingGums, chips, chocolates;

    public SnackMachine() {
        balance = Money.ZERO;
        transBalance = Money.ZERO;
        chewingGums = new Snack(SnackType.CHEWING_GUM, DEFAULT_QUANTITY);
        chips = new Snack(SnackType.CHIPS, DEFAULT_QUANTITY);
        chocolates = new Snack(SnackType.CHOCOLATE, DEFAULT_QUANTITY);
    }

    public Snack chewingGums() {
        return chewingGums;
    }

    public Snack chips() {
        return chips;
    }

    public Snack chocolates() {
        return chocolates;
    }

    public Money moneyInTransaction() {
        return transBalance;
    }

    public Money moneyInside() {
        return balance;
    }

    public void insertMoney(Money amount) {
        if(!supportedInput.contains(amount))
            throw new IllegalArgumentException("Cannot insert unsupported Money amount to SnackMachine");
        transBalance = transBalance.add(amount);
    }

    public Money buySnack(SnackType type){
        if(transBalance.isLessThan(type.price))
            throw new IllegalStateException("Money inserted is not enough to buy Snack");
        switch (type) {
            case CHEWING_GUM:
                if(chewingGums.quantity() < 1)
                    throw new IllegalStateException("Not enough snacks in machine");
                chewingGums.subtractQuantity(1);
                break;
            case CHIPS:
                if(chips.quantity() < 1)
                    throw new IllegalStateException("Not enough snacks in machine");
                chips.subtractQuantity(1);
                break;
            case CHOCOLATE:
                if(chocolates.quantity() < 1)
                    throw new IllegalStateException("Not enough snacks in machine");
                chocolates.subtractQuantity(1);
                break;
        }
        balance = balance.add(type.price);
        Money change = transBalance.subtract(type.price);
        transBalance = Money.ZERO;
        return change;
    }
}
