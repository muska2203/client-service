package com.devmode.clientservice.debts.people;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DebtItem implements Comparable<DebtItem> {

    private int targetUserId;

    private BigDecimal debtAmount;

    public DebtItem(int targetUserId, BigDecimal debtAmount) {
        this.targetUserId = targetUserId;
        this.debtAmount = debtAmount;
    }

    @Override
    public int compareTo(DebtItem o) {
        double firstDebtAmount = o.getDebtAmount().doubleValue();
        double secondDebtAmount = getDebtAmount().doubleValue();
        if (firstDebtAmount > secondDebtAmount) {
            return 1;
        } else if (firstDebtAmount < secondDebtAmount) {
            return -1;
        }
        return 0;
    }
}
