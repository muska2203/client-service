package com.devmode.clientservice.debts.controller;

import com.devmode.clientservice.debts.algorithms.SimpleDebtOptimizer;
import com.devmode.clientservice.debts.people.PersonalDebt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/debts")
public class DebtsRestController {

    private final SimpleDebtOptimizer simpleDebtOptimizer = new SimpleDebtOptimizer();

    @PostMapping("/optimize")
    public ResponseEntity<List<PersonalDebt>>  optimizeDebts(@RequestBody List<PersonalDebt> personalDebts) {
        return ResponseEntity.ok(simpleDebtOptimizer.optimize(personalDebts));
    }
}
