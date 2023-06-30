package com.devmode.clientservice.debts.controller;

import com.devmode.clientservice.debts.algorithms.SimpleDebtOptimizer;
import com.devmode.clientservice.debts.dto.PersonalDebtDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/debts")
public class DebtsRestController {

    private final SimpleDebtOptimizer simpleDebtOptimizer = new SimpleDebtOptimizer();
    private final Mapper mapper = new Mapper();

    @PostMapping("/optimize")
    public ResponseEntity<List<PersonalDebtDto>>  optimizeDebts(@RequestBody List<PersonalDebtDto> personalDebtDtos) {
        return ResponseEntity.ok(simpleDebtOptimizer
                .optimize(personalDebtDtos.stream()
                        .map(mapper::toPersonalDebt)
                        .collect(Collectors.toList()))
                .stream()
                .map(mapper::toPersonalDebtDto)
                .collect(Collectors.toList()));
    }
}
