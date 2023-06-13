package com.devmode.clientservice.debts.algorithms;

import com.devmode.clientservice.debts.people.PersonalDebt;

import java.util.Collection;
import java.util.Iterator;

public class DependencyManager {

    private Collection<PersonalDebt> personalDebtCollection;

    public DependencyManager(Collection<PersonalDebt> personalDebtCollection) {
        this.personalDebtCollection = personalDebtCollection;
    }

    public PersonalDebt findMinimalDebt() {
        Iterator<PersonalDebt> i = personalDebtCollection.iterator();
        PersonalDebt candidate = i.next();

        while (i.hasNext()) {
            PersonalDebt next = i.next();
            if (next.compareTo(candidate) < 0)
                candidate = next;
        }
        return candidate;
    }

    public PersonalDebt findMaximumDebt() {
        Iterator<PersonalDebt> i = personalDebtCollection.iterator();
        PersonalDebt candidate = i.next();

        while (i.hasNext()) {
            PersonalDebt next = i.next();
            if (next.compareTo(candidate) > 0)
                candidate = next;
        }
        return candidate;
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
