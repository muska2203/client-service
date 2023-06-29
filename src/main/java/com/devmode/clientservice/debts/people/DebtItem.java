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

    public boolean isZeroDebt() {
        return this.debtAmount.equals(BigDecimal.valueOf(0.0));
    }

    public double minus(DebtItem debtItem) {
        return debtItem.getDebtAmount().doubleValue() - this.getDebtAmount().doubleValue();
    }


    @Override
    public int compareTo(DebtItem o) {
        double firstDebtAmount = o.getDebtAmount().doubleValue();
        double secondDebtAmount = this.getDebtAmount().doubleValue();
        if (firstDebtAmount > secondDebtAmount) {
            return 1;
        } else if (firstDebtAmount < secondDebtAmount) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DebtItem) {
            DebtItem debtItem = (DebtItem) o;
            return debtItem.getTargetUserId() == this.getTargetUserId() && debtItem.getDebtAmount().doubleValue() == this.getDebtAmount().doubleValue();
        }
        return false;
    }
}
