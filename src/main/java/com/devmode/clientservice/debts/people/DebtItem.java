package com.devmode.clientservice.debts.people;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DebtItem {

    private int targetUserId;

    private BigDecimal debtAmount;

    public DebtItem(int targetUserId, BigDecimal debtAmount) {
        this.targetUserId = targetUserId;
        this.debtAmount = debtAmount;
    }
}
