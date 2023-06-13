package com.devmode.clientservice.debts.algorithms;

import com.devmode.clientservice.debts.orders.OrderInfo;
import com.devmode.clientservice.debts.people.DebtItem;
import com.devmode.clientservice.debts.people.PersonalDebt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleDebtOptimizer implements DebtOptimizer{

    @Override
    public List<PersonalDebt> countDebt(Collection<OrderInfo> orders) {
        return null;
    }

    @Override
    public List<PersonalDebt> optimize(Collection<PersonalDebt> personalDebtCollection) {
        List<PersonalDebt> optimizedPersonalDebts = optimizeDebts(personalDebtCollection);
        DependencyManager dependencyManager = new DependencyManager();
        if (dependencyManager.hasTransitiveDebts(personalDebtCollection)) {
            PersonalDebt maximumPersonalDebt = dependencyManager.findMaximumDebt(optimizedPersonalDebts);
            PersonalDebt minimumPersonalDebt = dependencyManager.findMinimalDebt(optimizedPersonalDebts);
            if (minimumPersonalDebt.getDebtItemByTargetUserId(maximumPersonalDebt.getUserId()) != null
                    && maximumPersonalDebt.getDebtItemByTargetUserId(minimumPersonalDebt.getUserId()) != null) {
                double maximumDebtValue = maximumPersonalDebt.getDebtItemByTargetUserId(minimumPersonalDebt.getUserId()).getDebtAmount().doubleValue();
                double minimumDebtValue = minimumPersonalDebt.getDebtItemByTargetUserId(maximumPersonalDebt.getUserId()).getDebtAmount().doubleValue();
                double debtValue = maximumDebtValue - minimumDebtValue;
                for (int i = 0; i < optimizedPersonalDebts.size(); i++) {
                    if (optimizedPersonalDebts.get(i) != maximumPersonalDebt) {
                        optimizedPersonalDebts.get(i).removeDebtItemByTargetUserId(maximumPersonalDebt.getUserId());
                        optimizedPersonalDebts.get(i).getDebtItems().add(new DebtItem(maximumPersonalDebt.getUserId(), BigDecimal.valueOf(debtValue)));
                    }
                }
                optimizedPersonalDebts = optimizeDebts(optimizedPersonalDebts);
            }


        }
        return optimizedPersonalDebts;
    }

    public List<PersonalDebt> optimizeDebts(Collection<PersonalDebt> personalDebtCollection) {
        List<PersonalDebt> personalDebts = new ArrayList<>();
        for (int i = 0; i < personalDebtCollection.size(); i++) {
            List<DebtItem> debts = new ArrayList<>();
            PersonalDebt personalDebt = (PersonalDebt) personalDebtCollection.toArray()[i];
            for (int j = 1; j < personalDebtCollection.size(); j++) {
                PersonalDebt comparedDebt =(PersonalDebt) personalDebtCollection.toArray()[j];
                if (comparedDebt.getDebtItemByTargetUserId(personalDebt.getUserId()) != null
                        && personalDebt.getDebtItemByTargetUserId(comparedDebt.getUserId()) != null) {
                    double comparedDebtValue = comparedDebt.getDebtItemByTargetUserId(personalDebt.getUserId()).getDebtAmount().doubleValue();
                    double personalDebtValue = personalDebt.getDebtItemByTargetUserId(comparedDebt.getUserId()).getDebtAmount().doubleValue();
                    double debtValue = personalDebtValue - comparedDebtValue;
                    if (debtValue >= 0) {
                        debts.add(new DebtItem(comparedDebt.getUserId(), BigDecimal.valueOf(debtValue)));
                    } else {
                        debts.add(new DebtItem(comparedDebt.getUserId(), BigDecimal.valueOf(0.0)));
                    }

                }
            }
            PersonalDebt newPersonalDebt = new PersonalDebt(personalDebt.getUserId(), debts);
            personalDebts.add(newPersonalDebt);
        }
        return personalDebts;
    }

}
