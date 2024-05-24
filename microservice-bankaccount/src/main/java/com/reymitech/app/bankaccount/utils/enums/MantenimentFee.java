package com.reymitech.app.bankaccount.utils.enums;

public enum MantenimentFee {
    SAVINGS(0),
    CHECKING(10),
    FIXED_TERM(0);

    private final double value;

    MantenimentFee(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
