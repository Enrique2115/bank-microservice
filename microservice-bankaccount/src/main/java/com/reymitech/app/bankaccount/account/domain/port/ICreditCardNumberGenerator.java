package com.reymitech.app.bankaccount.account.domain.port;

public interface ICreditCardNumberGenerator {
    String generate(String prefix, int length);
}
