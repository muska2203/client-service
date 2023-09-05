package com.devmode.clientservice.debts.people;

import com.devmode.clientservice.exception.EntityNotFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PersonalDebt implements Comparable<PersonalDebt>{

    private int userId;

    private List<DebtItem> debtItems;

    public PersonalDebt(int userId) {
        this.userId = userId;
        this.debtItems = new ArrayList<>();
    }

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

    public void addDebtItem(DebtItem debtItem) {
        if (debtItem.getTargetUserId() != this.getUserId())
            this.getDebtItems().add(debtItem);
    }

    public boolean hasDebtWithPerson(int targetUserId) {
        return this.getDebtItemByTargetUserId(targetUserId) != null;
    }

    public DebtItem getMaximalDebtItem() {
        if (this.getDebtItems().size() == 1) {
            return this.getDebtItems().get(0);
        }
        return Collections.max(this.getDebtItems());
    }

    public void removeDebtItemByTargetUserId(int targetUserId) {
        Iterator<DebtItem> iterator = this.debtItems.iterator();
        while (iterator.hasNext()) {
            DebtItem debtItem = iterator.next();
            if (debtItem.getTargetUserId() == targetUserId) {
                iterator.remove();
            }
        }
    }

    public boolean hasDebtWithAnyPerson() {
        for (DebtItem debtItem : this.debtItems) {
            if (debtItem.isZeroDebt() || debtItem.getTargetUserId() == this.getUserId()) {
                return false;
            }
        }
        return !this.getDebtItems().isEmpty();
    }


    public boolean hasTransitiveDebtsWithPerson(PersonalDebt personalDebt) {
        return this.hasDebtWithPerson(personalDebt.getUserId()) && personalDebt.hasDebtWithAnyPerson();
    }


    public int getTargetIdOfTransitiveDebt(PersonalDebt personalDebt) {
        DebtItem debtItem = personalDebt.getMaximalDebtItem();
        if (this.getUserId() != debtItem.getTargetUserId())
            return debtItem.getTargetUserId();
        throw new EntityNotFoundException();
    }

    public List<DebtItem> getDebtItemsWithoutItemWithTargetId(int targetUserId) {
        List<DebtItem> debts = new ArrayList<>();
        for (DebtItem debtItem : this.getDebtItems()) {
            if (debtItem.getTargetUserId() != targetUserId) {
                debts.add(debtItem);
            }
        }
        return debts;
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

    @Override
    public boolean equals(Object personalDebt) {
        if (personalDebt instanceof PersonalDebt) {
            PersonalDebt debt = (PersonalDebt) personalDebt;
            return debt.getUserId() == this.getUserId();
        }
        return false;
    }
}
