package com.devmode.clientservice.debts.algorithms;

import com.devmode.clientservice.debts.orders.OrderInfo;
import com.devmode.clientservice.debts.orders.OrderItem;
import com.devmode.clientservice.debts.people.DebtItem;
import com.devmode.clientservice.debts.people.PersonalDebt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleDebtOptimizer implements DebtOptimizer{

    @Override
    public List<PersonalDebt> countDebt(Collection<OrderInfo> orders) {
        List<PersonalDebt> personalDebts = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderInfo[] infos = orders.toArray(new OrderInfo[0]);
            OrderInfo order = infos[i];
            int payerId = order.getPayerUserId();
            PersonalDebt personalDebt = new PersonalDebt(payerId, new ArrayList<>());
            List<OrderItem> items = order.getOrderItems();
            for (OrderItem oi : items) {
                DebtItem debtItem = new DebtItem(oi.getUserId(), oi.getCost());
                personalDebt.getDebtItems().add(debtItem);
            }
        }
        return personalDebts;
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
                for (PersonalDebt optimizedPersonalDebt : optimizedPersonalDebts) {
                    if (optimizedPersonalDebt != maximumPersonalDebt) {
                        optimizedPersonalDebt.removeDebtItemByTargetUserId(maximumPersonalDebt.getUserId());
                        optimizedPersonalDebt.getDebtItems().add(new DebtItem(maximumPersonalDebt.getUserId(), BigDecimal.valueOf(debtValue)));
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
