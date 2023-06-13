package com.devmode.clientservice.debts.algorithms;

import com.devmode.clientservice.debts.orders.OrderInfo;
import com.devmode.clientservice.debts.people.PersonalDebt;

import java.util.Collection;
import java.util.List;

public interface DebtOptimizer {
    List<PersonalDebt> countDebt(Collection<OrderInfo> orders);

    List<PersonalDebt> optimize(Collection<PersonalDebt> personalDebtCollection);
}
