package com.devmode.clientservice.debts.algorithms;

import com.devmode.clientservice.debts.people.DebtItem;
import com.devmode.clientservice.debts.people.PersonalDebt;
import com.devmode.clientservice.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class DependencyManager {

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

    public boolean hasTransitionDebts(Collection<PersonalDebt> personalDebtCollection) {
        for (int i = 0; i < personalDebtCollection.size(); i++) {
            for (int j = 0; j < personalDebtCollection.size(); j++) {
                PersonalDebt personalDebt = (PersonalDebt) personalDebtCollection.toArray()[i];
                PersonalDebt nextPersonalDebt = (PersonalDebt) personalDebtCollection.toArray()[j];
                int maxId;
                try {
                    maxId = personalDebt.getTargetIdOfTransitiveDebt(nextPersonalDebt);
                } catch (EntityNotFoundException | NoSuchElementException e) {
                    continue;
                }
                if (!personalDebt.equals(nextPersonalDebt))
                    if (personalDebt.hasTransitiveDebtsWithPerson(nextPersonalDebt) && maxId != nextPersonalDebt.getUserId()) return true;
            }
        }
        return false;
    }
}
