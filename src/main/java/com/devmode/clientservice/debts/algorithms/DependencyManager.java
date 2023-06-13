package com.devmode.clientservice.debts.algorithms;

import com.devmode.clientservice.debts.people.PersonalDebt;

import java.util.Collection;

public class DependencyManager {

    public PersonalDebt findMinimalDebt(Collection<PersonalDebt> personalDebtCollection) {
        return sortByDebtsValue(personalDebtCollection)[personalDebtCollection.size() - 1];
    }

    public PersonalDebt findMaximumDebt(Collection<PersonalDebt> personalDebtCollection) {
        return sortByDebtsValue(personalDebtCollection)[0];
    }

    public PersonalDebt[] sortByDebtsValue(Collection<PersonalDebt> personalDebtCollection) {
        PersonalDebt[] personalDebts = personalDebtCollection.toArray(new PersonalDebt[0]);
        for (int i = 0; i < personalDebtCollection.size(); i++) {
            for (int j = 1; j < personalDebtCollection.size() - i; j++) {
                PersonalDebt personalDebt = (PersonalDebt) personalDebtCollection.toArray()[i];
                PersonalDebt nextPersonalDebt = (PersonalDebt) personalDebtCollection.toArray()[j];
                if (nextPersonalDebt.getDebtItemByTargetUserId(personalDebt.getUserId()) != null
                        && personalDebt.getDebtItemByTargetUserId(nextPersonalDebt.getUserId()) != null) {
                    double personalDebtValue = personalDebt.getDebtItemByTargetUserId(nextPersonalDebt.getUserId()).getDebtAmount().doubleValue();
                    double nextPersonalDebtValue = nextPersonalDebt.getDebtItemByTargetUserId(personalDebt.getUserId()).getDebtAmount().doubleValue();
                    if (personalDebtValue < nextPersonalDebtValue) {
                        PersonalDebt temp = personalDebts[i];
                        personalDebts[i] = personalDebts[j];
                        personalDebts[j] = temp;
                    }
                }
            }
        }
        return personalDebts;
    }

    public boolean hasTransitiveDebts(Collection<PersonalDebt> personalDebtCollection) {
        int i = 0;
        for (PersonalDebt personalDebt : personalDebtCollection) {
            if (personalDebt.getDebtItems().size() == 1 && personalDebt.getDebtItems().get(0).getDebtAmount().doubleValue() != 0.0) {
                i++;
            }
        }
        return i == personalDebtCollection.size();
    }

}
