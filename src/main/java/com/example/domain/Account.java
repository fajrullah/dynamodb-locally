package com.example.domain;
import java.math.BigDecimal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Account {

    private String id;
    private BigDecimal balance;
    private String currency;


    public static Account of(String id, BigDecimal balance, String currency) {
        Account acc = new Account();
        acc.setId(id);
        acc.setBalance(balance);
        acc.setCurrency(currency);

        return acc;
    }

    private void setCurrency(String currency) {
        this.currency = currency;
    }

    private void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    private void setId(String id) {
        this.id = id;
    }


}