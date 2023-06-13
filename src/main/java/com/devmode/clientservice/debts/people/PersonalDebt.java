package com.devmode.clientservice.debts.people;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PersonalDebt {

    private int userId;

    private List<DebtItem> debtItems;

    public PersonalDebt(int userId, List<DebtItem> debtItems) {
        this.userId = userId;
        this.debtItems = debtItems;
    }

    public DebtItem getDebtItemByTargetUserId(int targetUserId) {
        return null;
    }

    public void removeDebtItemByTargetUserId(int targetUserId) {

    }


}
