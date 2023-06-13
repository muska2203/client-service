package com.devmode.clientservice.debts.people;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PersonalDebt implements Comparable<PersonalDebt>{

    private int userId;

    private List<DebtItem> debtItems;

    public PersonalDebt(int userId, List<DebtItem> debtItems) {
        this.userId = userId;
        this.debtItems = debtItems;
    }

    public DebtItem getDebtItemByTargetUserId(int targetUserId) {
        for (DebtItem debt : this.debtItems) {
            if (debt.getTargetUserId() == targetUserId) {
                return debt;
            }
        }
        return null;
    }

    public void removeDebtItemByTargetUserId(int targetUserId) {
        this.debtItems.removeIf(debt -> debt.getTargetUserId() == targetUserId);
    }

    @Override
    public int compareTo(PersonalDebt o) {
        DebtItem first = o.getDebtItemByTargetUserId(this.getUserId());
        DebtItem second = this.getDebtItemByTargetUserId(o.getUserId());
        if (first != null && second != null) {
            double firstMaxValue = first.getDebtAmount().doubleValue();
            double secondMaxValue = second.getDebtAmount().doubleValue();
            if (firstMaxValue > secondMaxValue) {
                return 1;
            } else if (firstMaxValue < secondMaxValue) {
                return -1;
            }
        }
        return 0;
    }
}
