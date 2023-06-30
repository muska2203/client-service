package com.devmode.clientservice.debts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class DebtItemDto {

    private int targetUserId;

    private BigDecimal debtAmount;

}
