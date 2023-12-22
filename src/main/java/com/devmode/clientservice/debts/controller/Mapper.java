package com.devmode.clientservice.debts.controller;


import com.devmode.clientservice.debts.dto.DebtItemDto;
import com.devmode.clientservice.debts.dto.PersonalDebtDto;
import com.devmode.clientservice.debts.people.DebtItem;
import com.devmode.clientservice.debts.people.PersonalDebt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public PersonalDebtDto toPersonalDebtDto(PersonalDebt personalDebt) {
        String userId = personalDebt.getUserId();
        List<DebtItemDto> debtItems = new ArrayList<>();
        for (DebtItem debtItem : personalDebt.getDebtItems()) {
            debtItems.add(toDebtItemDto(debtItem));
        }
        return new PersonalDebtDto(userId, debtItems);
    }

    public PersonalDebt toPersonalDebt(PersonalDebtDto personalDebtDto) {
        String userId = personalDebtDto.getUserId();
        List<DebtItem> debtItems = new ArrayList<>();
        for (DebtItemDto debtItemDto : personalDebtDto.getDebtItems()) {
            debtItems.add(toDebtItem(debtItemDto));
        }
        return new PersonalDebt(userId, debtItems);
    }

    public DebtItemDto toDebtItemDto(DebtItem debtItem) {
        String targetUserId = debtItem.getTargetUserId();
        BigDecimal debtAmount = debtItem.getDebtAmount();
        return new DebtItemDto(targetUserId, debtAmount);
    }

    public DebtItem toDebtItem(DebtItemDto debtItemDto) {
        String targetUserId = debtItemDto.getTargetUserId();
        BigDecimal debtAmount = debtItemDto.getDebtAmount();
        return new DebtItem(targetUserId, debtAmount);
    }



}
