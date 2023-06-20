package com.devmode.clientservice.debts.algorithms;

import com.devmode.clientservice.debts.people.DebtItem;
import com.devmode.clientservice.debts.people.PersonalDebt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DependencyManager {

    private final Collection<PersonalDebt> personalDebtCollection;

    public DependencyManager(Collection<PersonalDebt> personalDebtCollection) {
        this.personalDebtCollection = personalDebtCollection;
    }

    public DebtItem findMinimalDebt(Collection<DebtItem> debtItemCollection) {
        List<DebtItem> debtItems = new ArrayList<>(debtItemCollection);
        DebtItem firstDebtItem = debtItems.get(0);
        DebtItem secondDebtItem = debtItems.get(1);
        if (firstDebtItem.compareTo(secondDebtItem) < 0) {
            return secondDebtItem;
        } else {
            return firstDebtItem;
        }
    }

    public DebtItem findMaximumDebt(Collection<DebtItem> debtItemCollection) {
        List<DebtItem> debtItems = new ArrayList<>(debtItemCollection);
        DebtItem firstDebtItem = debtItems.get(0);
        DebtItem secondDebtItem = debtItems.get(1);
        if (firstDebtItem.compareTo(secondDebtItem) > 0) {
            return secondDebtItem;
        } else {
            return firstDebtItem;
        }
    }

    public boolean hasTransitionDebts() {
        for (int i = 0; i < personalDebtCollection.size(); i++) {
            for (int j = 0; j < personalDebtCollection.size(); j++) {
                PersonalDebt personalDebt = (PersonalDebt) personalDebtCollection.toArray()[i];
                PersonalDebt nextPersonalDebt = (PersonalDebt) personalDebtCollection.toArray()[j];
                if (!personalDebt.equals(nextPersonalDebt))
                    if (personalDebt.hasTransitiveDebtsWithPerson(nextPersonalDebt)) return true;
            }
        }
        return false;
    }
}
