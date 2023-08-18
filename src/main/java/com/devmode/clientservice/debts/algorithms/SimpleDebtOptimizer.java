package com.devmode.clientservice.debts.algorithms;

import com.devmode.clientservice.debts.orders.OrderInfo;
import com.devmode.clientservice.debts.orders.OrderItem;
import com.devmode.clientservice.debts.people.DebtItem;
import com.devmode.clientservice.debts.people.PersonalDebt;
import com.devmode.clientservice.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Component
@Qualifier(value = "simpleDebtOptimizer")
public class SimpleDebtOptimizer implements DebtOptimizer {

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
            personalDebts.add(personalDebt);
        }
        return personalDebts;
    }

    @Override
    public List<PersonalDebt> optimize(Collection<PersonalDebt> personalDebtCollection) {
        List<PersonalDebt> optimizedPersonalDebts = optimizeNonTransitionDebts(personalDebtCollection);
        DependencyManager dependencyManager = new DependencyManager();
        if (dependencyManager.hasTransitionDebts(optimizedPersonalDebts)) {
            return clearZeroDebtors(optimizeNonTransitionDebts(optimizeTransitionDebts(optimizedPersonalDebts)));
        }
        return clearZeroDebtors(optimizedPersonalDebts);
    }

    public List<PersonalDebt> optimizeNonTransitionDebts(Collection<PersonalDebt> personalDebtCollection) {
        List<PersonalDebt> personalDebtList = new ArrayList<>(personalDebtCollection);
        List<PersonalDebt> personalDebts = new ArrayList<>();
        for (int i = 0; i < personalDebtList.size(); i++) {
            PersonalDebt personalDebt = personalDebtList.get(i);
            personalDebt = new PersonalDebt(personalDebt.getUserId(), new ArrayList<>(personalDebt.getDebtItems()));
            for (int j = 0; j < personalDebtList.size(); j++) {
                PersonalDebt comparedDebt = personalDebtList.get(j);
                comparedDebt = new PersonalDebt(comparedDebt.getUserId(), new ArrayList<>(comparedDebt.getDebtItems()));
                if (!personalDebt.equals(comparedDebt)) {
                    int personalDebtId = personalDebt.getUserId();
                    int comparedDebtId = comparedDebt.getUserId();
                    if (personalDebt.hasDebtWithPerson(comparedDebtId) && comparedDebt.hasDebtWithPerson(personalDebtId)) {
                        DebtItem personalDebtItem = personalDebt.getDebtItemByTargetUserId(comparedDebtId);
                        DebtItem comparedDebtItem = comparedDebt.getDebtItemByTargetUserId(personalDebtId);
                        BigDecimal personalDebtItemDebtValue = personalDebtItem.minus(comparedDebtItem);
                        BigDecimal comparedDebtItemValue = comparedDebtItem.minus(personalDebtItem);
                        if (personalDebtItemDebtValue.doubleValue() > 0.0) {
                            comparedDebtItem.setDebtAmount(personalDebtItemDebtValue);
                        } else {
                            comparedDebt.removeDebtItemByTargetUserId(personalDebtId);
                        }
                        if (comparedDebtItemValue.doubleValue() > 0.0) {
                            personalDebtItem.setDebtAmount(comparedDebtItemValue);
                        } else {
                            personalDebt.removeDebtItemByTargetUserId(comparedDebtId);
                        }
                        personalDebtList = fillListByNewVariables(personalDebtList, personalDebt, comparedDebt);
                        if (!personalDebts.contains(comparedDebt)) {
                            personalDebts.add(comparedDebt);
                        } else {
                            removeByUserId(personalDebts, comparedDebtId);
                            personalDebts.add(comparedDebt);
                        }
                        if (!personalDebts.contains(personalDebt)) {
                            personalDebts.add(personalDebt);
                        } else {
                            removeByUserId(personalDebts, personalDebtId);
                            personalDebts.add(personalDebt);
                        }
                    }
                }
            }
        }

        if (personalDebts.isEmpty()) {
            return personalDebtList;
        }
        personalDebts = fillList(personalDebtList, personalDebts);
        return personalDebts;
    }

    public List<PersonalDebt> optimizeTransitionDebts(Collection<PersonalDebt> personalDebtCollection) {
        List<PersonalDebt> optimizedPersonalDebts = new ArrayList<>(clearZeroDebtors(personalDebtCollection));
        List<PersonalDebt> personalDebts = new ArrayList<>();
        DependencyManager dependencyManager = new DependencyManager();
        while (dependencyManager.hasTransitionDebts(optimizedPersonalDebts)) {
            if (!optimizedPersonalDebts.isEmpty()) {
                for (int i = 0; i < optimizedPersonalDebts.size(); i++) {
                    PersonalDebt personalDebt = optimizedPersonalDebts.get(i);
                    int personalDebtId = personalDebt.getUserId();
                    for (int j = 0; j < optimizedPersonalDebts.size(); j++) {
                        PersonalDebt nextPersonalDebt = optimizedPersonalDebts.get(j);
                        if (!personalDebt.equals(nextPersonalDebt)) {
                            int nextPersonalDebtId = nextPersonalDebt.getUserId();
                            if (personalDebt.hasTransitiveDebtsWithPerson(nextPersonalDebt)) {
                                DebtItem personalDebtItem;
                                DebtItem nextPersonalDebtItem;
                                double personalDebtItemValue;
                                double nextPersonalDebtItemValue;
                                int maxTargetUserDebtItemId;
                                try {
                                    maxTargetUserDebtItemId = personalDebt.getTargetIdOfTransitiveDebt(nextPersonalDebt);
                                } catch (EntityNotFoundException e) {
                                    continue;
                                }
                                if (maxTargetUserDebtItemId != personalDebtId) {
                                    personalDebtItem = personalDebt.getDebtItemByTargetUserId(nextPersonalDebtId);
                                    personalDebtItemValue = personalDebtItem.getDebtAmount().doubleValue();
                                    nextPersonalDebtItem = nextPersonalDebt.getDebtItemByTargetUserId(maxTargetUserDebtItemId);
                                    nextPersonalDebtItemValue = nextPersonalDebtItem.getDebtAmount().doubleValue();
                                    personalDebt
                                            = new PersonalDebt(personalDebtId, personalDebt.getDebtItemsWithoutItemWithTargetId(nextPersonalDebtId));
                                    nextPersonalDebt
                                            = new PersonalDebt(nextPersonalDebtId, nextPersonalDebt.getDebtItemsWithoutItemWithTargetId(personalDebtId));
                                    BigDecimal minPersonalDebtValue = dependencyManager.findMinimalDebt(List.of(personalDebtItem, nextPersonalDebtItem)).getDebtAmount();
                                    BigDecimal maxPersonalDebtValue = dependencyManager.findMaximumDebt(List.of(personalDebtItem, nextPersonalDebtItem)).getDebtAmount();
                                    BigDecimal debtValue = maxPersonalDebtValue.subtract(minPersonalDebtValue);
                                    if (personalDebtItemValue == nextPersonalDebtItemValue) {
                                        if (!personalDebt.hasDebtWithPerson(maxTargetUserDebtItemId)) {
                                            personalDebt.addDebtItem(new DebtItem(maxTargetUserDebtItemId, BigDecimal.valueOf(personalDebtItemValue)));
                                        } else {
                                            DebtItem debtItem = personalDebt.getDebtItemByTargetUserId(maxTargetUserDebtItemId);
                                            debtItem.setDebtAmount(BigDecimal.valueOf(debtItem.getDebtAmount().doubleValue() + personalDebtItemValue));
                                        }
                                        nextPersonalDebt.removeDebtItemByTargetUserId(maxTargetUserDebtItemId);
                                    } else if (personalDebtItemValue < nextPersonalDebtItemValue) {
                                        if (!personalDebt.hasDebtWithPerson(maxTargetUserDebtItemId)) {
                                            personalDebt.addDebtItem(new DebtItem(maxTargetUserDebtItemId, minPersonalDebtValue));
                                        } else {
                                            DebtItem debtItem = personalDebt.getDebtItemByTargetUserId(maxTargetUserDebtItemId);
                                            debtItem.setDebtAmount(debtItem.getDebtAmount().add(minPersonalDebtValue));
                                        }
                                        nextPersonalDebt.getDebtItemByTargetUserId(maxTargetUserDebtItemId).setDebtAmount(debtValue);
                                    } else {
                                        personalDebt = new PersonalDebt(personalDebtId, new ArrayList<>(personalDebt.getDebtItems()));
                                        if (!personalDebt.hasDebtWithPerson(maxTargetUserDebtItemId)) {
                                            personalDebt.addDebtItem(new DebtItem(maxTargetUserDebtItemId, minPersonalDebtValue));
                                        } else {
                                            DebtItem debtItem = personalDebt.getDebtItemByTargetUserId(maxTargetUserDebtItemId);
                                            debtItem.setDebtAmount(debtItem.getDebtAmount().add(minPersonalDebtValue));
                                        }
                                        if (personalDebt.hasDebtWithPerson(nextPersonalDebtId)) {
                                            DebtItem ownPersonalDebtItem = personalDebt.getDebtItemByTargetUserId(nextPersonalDebtId);
                                            ownPersonalDebtItem.setDebtAmount(debtValue);
                                        } else {
                                            personalDebt.addDebtItem(new DebtItem(nextPersonalDebtId, debtValue));
                                        }
                                        nextPersonalDebt.removeDebtItemByTargetUserId(maxTargetUserDebtItemId);
                                    }
                                    if (!personalDebts.contains(personalDebt)) {
                                        personalDebts.add(personalDebt);
                                    } else {
                                        removeByUserId(personalDebts, personalDebtId);
                                        personalDebts.add(personalDebt);
                                    }
                                    if (!personalDebts.contains(nextPersonalDebt)) {
                                        personalDebts.add(nextPersonalDebt);
                                    } else {
                                        removeByUserId(personalDebts, nextPersonalDebtId);
                                        personalDebts.add(nextPersonalDebt);
                                    }
                                    optimizedPersonalDebts = fillListByNewVariables(optimizedPersonalDebts, personalDebt, nextPersonalDebt);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (personalDebts.isEmpty()) {
            return optimizedPersonalDebts;
        }
        return personalDebts;
    }

    private void removeByUserId(List<PersonalDebt> personalDebtList, int userId) {
        Iterator<PersonalDebt> iterator = personalDebtList.iterator();
        while (iterator.hasNext()) {
            PersonalDebt debt = iterator.next();
            if (debt.getUserId() == userId) {
                iterator.remove();
            }
        }
    }

    private List<PersonalDebt> fillList(List<PersonalDebt> ownPersonalDebts, List<PersonalDebt> personalDebts) {
        List<PersonalDebt> newList = new ArrayList<>(personalDebts);
        for (PersonalDebt ownPersonalDebt : ownPersonalDebts) {
            if (!personalDebts.contains(ownPersonalDebt)) {
                newList.add(ownPersonalDebt);
            }
        }
        return newList;
    }

        private List<PersonalDebt> fillListByNewVariables (List<PersonalDebt> personalDebtList, PersonalDebt... personalDebts){
            List<PersonalDebt> newList = new ArrayList<>();
            for (PersonalDebt listDebt : personalDebtList) {
                for (PersonalDebt arrayDebt : personalDebts) {
                    if (listDebt.equals(arrayDebt)) {
                        if (!newList.contains(arrayDebt)) {
                            newList.add(arrayDebt);
                        } else {
                            removeByUserId(newList, arrayDebt.getUserId());
                            newList.add(arrayDebt);
                        }

                    } else {
                        if (!newList.contains(listDebt))
                            newList.add(listDebt);
                    }
                }
            }
            return newList;
        }


        private List<PersonalDebt> clearZeroDebtors (Collection <PersonalDebt> personalDebtCollection) {
            List<PersonalDebt> personalDebts = new ArrayList<>(personalDebtCollection);
            List<PersonalDebt> debts = new ArrayList<>();
            for (PersonalDebt personalDebt : personalDebts) {
                List<DebtItem> debtItems = personalDebt.getDebtItems();
                for (DebtItem debtItem : debtItems) {
                    if (!debtItem.isZeroDebt() && !debtItems.isEmpty() && !debts.contains(personalDebt)) {
                        debts.add(personalDebt);
                    }
                }
            }
            return debts;
        }
}
