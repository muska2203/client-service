package com.devmode.clientservice.debts.controller;

import com.devmode.clientservice.debts.algorithms.SimpleDebtOptimizer;
import com.devmode.clientservice.debts.dto.PersonalDebtDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/debts")
public class DebtsRestController {

    private final SimpleDebtOptimizer simpleDebtOptimizer;
    private final Mapper mapper = new Mapper();

    public DebtsRestController(@Qualifier(value = "simpleDebtOptimizer") SimpleDebtOptimizer simpleDebtOptimizer) {
        this.simpleDebtOptimizer = simpleDebtOptimizer;
    }

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
