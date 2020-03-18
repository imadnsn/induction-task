package com.progressoft.induction;

public class Snack {
    private final SnackType type;
    private int quantity;
    public Snack(SnackType snackType, int quantity) {
        this.type = snackType;
        setQuantity(quantity);
    }

    void setQuantity(int quantity) {
        if(quantity < 0)
            throw new IllegalArgumentException("Quantity of Snack cannot be negative");
        this.quantity = quantity;
    }

    void addQuantity(int amount){
        this.quantity += amount;
    }

    void subtractQuantity(int amount){
        if(quantity < amount)
            throw new IllegalArgumentException("Amount to subtract of Snack cannot be greater than quantity");
        this.quantity -= amount;
    }

    public int quantity() {
        return this.quantity;
    }

    public SnackType snackType() {
        return type;
    }
}
