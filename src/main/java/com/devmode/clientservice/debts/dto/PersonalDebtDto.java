package com.devmode.clientservice.debts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PersonalDebtDto {

    private String userId;

    private List<DebtItemDto> debtItems;


}
