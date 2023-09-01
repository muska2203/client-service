package com.devmode.clientservice.debts;


import com.devmode.clientservice.debts.algorithms.DebtOptimizer;
import com.devmode.clientservice.debts.algorithms.SimpleDebtOptimizer;
import com.devmode.clientservice.debts.people.DebtItem;
import com.devmode.clientservice.debts.people.PersonalDebt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DebtOptimizerTest {



    @Test
    void simpleOptimizeForThreePeople() {
        DebtOptimizer optimizer = new SimpleDebtOptimizer();

        DebtItem dimaArtem = new DebtItem(2, BigDecimal.valueOf(20));
        DebtItem dimaSasha = new DebtItem(3, BigDecimal.valueOf(15));
        DebtItem artemDima = new DebtItem(1, BigDecimal.valueOf(10));
        DebtItem artemSasha = new DebtItem(3, BigDecimal.valueOf(10));
        DebtItem sashaDima = new DebtItem(1, BigDecimal.valueOf(10));
        DebtItem sashaArtem = new DebtItem(2, BigDecimal.valueOf(20));

        PersonalDebt dima = new PersonalDebt(1, List.of(dimaArtem, dimaSasha));
        PersonalDebt artem = new PersonalDebt(2, List.of(artemDima, artemSasha));
        PersonalDebt sasha = new PersonalDebt(3, List.of(sashaDima, sashaArtem));


        List<PersonalDebt> personalDebts = optimizer.optimize(List.of(dima, artem, sasha));

        for (PersonalDebt p : personalDebts) {
            if (p.getUserId() == dima.getUserId()) {
                dima = p;
            }
            if (p.getUserId() == artem.getUserId()) {
                artem = p;
            }
            if (p.getUserId() == sasha.getUserId()) {
                sasha = p;
            }
        }

        assertDebts(dima, Map.of(2, BigDecimal.valueOf(15)));
        assertDebts(sasha, Map.of(2, BigDecimal.valueOf(5)));
    }

    @Test
    void simpleOptimizeForTwoPeople() {
        DebtOptimizer optimizer = new SimpleDebtOptimizer();

        DebtItem dimaArtem = new DebtItem(1, BigDecimal.valueOf(20.0));
        DebtItem artemDima = new DebtItem(0, BigDecimal.valueOf(10.0));

        PersonalDebt dima = new PersonalDebt(0, List.of(dimaArtem));
        PersonalDebt artem = new PersonalDebt(1, List.of(artemDima));


        List<PersonalDebt> personalDebts = optimizer.optimize(List.of(dima, artem));

        for (PersonalDebt p : personalDebts) {
            if (p.getUserId() == dima.getUserId()) {
                dima = p;
            }
            if (p.getUserId() == artem.getUserId()) {
                artem = p;
            }
        }

        assertDebts(dima, Map.of(1, BigDecimal.valueOf(10.0)));

    }

    @Test
    void transitiveOptimizeThreePeople() {
        DebtOptimizer optimizer = new SimpleDebtOptimizer();

        DebtItem dimaArtem = new DebtItem(2, BigDecimal.valueOf(10.0));
        DebtItem artemSasha = new DebtItem(3, BigDecimal.valueOf(10.0));
        DebtItem sashaDima = new DebtItem(1, BigDecimal.valueOf(10.0));

        PersonalDebt dima = new PersonalDebt(1, List.of(dimaArtem));
        PersonalDebt artem = new PersonalDebt(2, List.of(artemSasha));
        PersonalDebt sasha = new PersonalDebt(3, List.of(sashaDima));

        List<PersonalDebt> personalDebts = optimizer.optimize(List.of(dima, artem, sasha));

        for (PersonalDebt p : personalDebts) {
            if (p.getUserId() == dima.getUserId()) {
                dima = p;
            }
            if (p.getUserId() == artem.getUserId()) {
                artem = p;
            }
            if (p.getUserId() == sasha.getUserId()) {
                sasha = p;
            }
        }
        assertDebts(dima, Map.of(3, BigDecimal.valueOf(10.0)));
    }

    @Test
    void transitiveOptimizeTwoPeople() {
        DebtOptimizer optimizer = new SimpleDebtOptimizer();

        DebtItem dimaSasha = new DebtItem(2, BigDecimal.valueOf(10.0));
        DebtItem sashaArtem = new DebtItem(1, BigDecimal.valueOf(10.0));

        PersonalDebt dima = new PersonalDebt(0, List.of(dimaSasha));
        PersonalDebt sasha = new PersonalDebt(2, List.of(sashaArtem));

        List<PersonalDebt> personalDebts = optimizer.optimize(List.of(dima, sasha));

        for (PersonalDebt p : personalDebts) {
            if (p.getUserId() == dima.getUserId()) {
                dima = p;
            }
        }

        assertDebts(dima, Map.of(1, BigDecimal.valueOf(10.0)));
    }

    @Test
    void mainTest() {
        DebtOptimizer optimizer = new SimpleDebtOptimizer();

        DebtItem artemSasha = new DebtItem(2, BigDecimal.valueOf(5.0));
        DebtItem artemDima = new DebtItem(0, BigDecimal.valueOf(10.0));
        DebtItem artemValya = new DebtItem(3, BigDecimal.valueOf(8.0));

        DebtItem dimaSasha = new DebtItem(2, BigDecimal.valueOf(7.0));
        DebtItem dimaArtem = new DebtItem(0, BigDecimal.valueOf(6.0));
        DebtItem dimaValya = new DebtItem(3, BigDecimal.valueOf(9.0));

        DebtItem sashaArtem = new DebtItem(2, BigDecimal.valueOf(5.0));
        DebtItem sashaDima = new DebtItem(0, BigDecimal.valueOf(10.0));
        DebtItem sashaValya = new DebtItem(3, BigDecimal.valueOf(8.0));

        DebtItem valyaSasha = new DebtItem(2, BigDecimal.valueOf(8.0));
        DebtItem valyaDima = new DebtItem(0, BigDecimal.valueOf(7.0));
        DebtItem valyaArtem = new DebtItem(3, BigDecimal.valueOf(10.0));


        PersonalDebt dima = new PersonalDebt(0, List.of(dimaArtem, dimaSasha, dimaValya));
        PersonalDebt artem = new PersonalDebt(1, List.of(artemSasha, artemValya, artemDima));
        PersonalDebt sasha = new PersonalDebt(2, List.of(sashaDima, sashaValya, sashaArtem));
        PersonalDebt valya = new PersonalDebt(3, List.of(valyaArtem, valyaDima, valyaSasha));

        List<PersonalDebt> personalDebts = optimizer.optimize(List.of(dima, artem, sasha, valya));

        for (PersonalDebt p : personalDebts) {
            if (p.getUserId() == dima.getUserId()) {
                dima = p;
            }
            if (p.getUserId() == artem.getUserId()) {
                artem = p;
            }
            if (p.getUserId() == sasha.getUserId()) {
                sasha = p;
            }
            if (p.getUserId() == valya.getUserId()) {
                valya = p;
            }
        }

        assertDebts(dima, Map.of(3, BigDecimal.valueOf(2.0)));
    }

    @Test
    void testWithTransitionDependencies() {
        DebtOptimizer optimizer = new SimpleDebtOptimizer();

        DebtItem artemDima = new DebtItem(1, BigDecimal.valueOf(100.0));

        DebtItem dimaSasha = new DebtItem(3, BigDecimal.valueOf(50.0));

        DebtItem sashaArtem = new DebtItem(2, BigDecimal.valueOf(50.0));

        DebtItem valyaSasha = new DebtItem(3, BigDecimal.valueOf(10.0));
        DebtItem valyaDima = new DebtItem(1, BigDecimal.valueOf(10.0));
        DebtItem valyaArtem = new DebtItem(2, BigDecimal.valueOf(10.0));


        PersonalDebt dima = new PersonalDebt(1, List.of(dimaSasha));
        PersonalDebt artem = new PersonalDebt(2, List.of(artemDima));
        PersonalDebt sasha = new PersonalDebt(3, List.of(sashaArtem));
        PersonalDebt valya = new PersonalDebt(4, List.of(valyaArtem, valyaDima, valyaSasha));

        List<PersonalDebt> personalDebts = optimizer.optimize(List.of(dima, artem, sasha, valya));

        for (PersonalDebt p : personalDebts) {
            if (p.getUserId() == dima.getUserId()) {
                dima = p;
            }
            if (p.getUserId() == artem.getUserId()) {
                artem = p;
            }
            if (p.getUserId() == sasha.getUserId()) {
                sasha = p;
            }
            if (p.getUserId() == valya.getUserId()) {
                valya = p;
            }
        }

        assertDebts(artem, Map.of(1, BigDecimal.valueOf(40.0)));
        assertDebts(valya, Map.of(1, BigDecimal.valueOf(20.0), 3, BigDecimal.valueOf(10.0)));
    }

    @Test
    void testDifficultVariationOfTransitionDebts() {
        DebtOptimizer optimizer = new SimpleDebtOptimizer();

        DebtItem dimaArtem = new DebtItem(2, BigDecimal.valueOf(10));
        DebtItem dimaSasha = new DebtItem(3, BigDecimal.valueOf(10));
        DebtItem sashaValya = new DebtItem(4, BigDecimal.valueOf(10));
        DebtItem sashaKatya = new DebtItem(5, BigDecimal.valueOf(10));


        PersonalDebt dima = new PersonalDebt(1, List.of(dimaArtem, dimaSasha));
        PersonalDebt artem = new PersonalDebt(2, new ArrayList<>());
        PersonalDebt sasha = new PersonalDebt(3, List.of(sashaValya, sashaKatya));
        PersonalDebt valya = new PersonalDebt(4, new ArrayList<>());

        List<PersonalDebt> personalDebts = optimizer.optimize(List.of(dima, artem, sasha, valya));

        for (PersonalDebt p : personalDebts) {
            if (p.getUserId() == dima.getUserId()) {
                dima = p;
            }
            if (p.getUserId() == sasha.getUserId()) {
                sasha = p;
            }
        }
        assertDebts(dima, Map.of(4, BigDecimal.valueOf(10.0)));
    }

    @Test
    void test1() {

        DebtOptimizer optimizer = new SimpleDebtOptimizer();

        DebtItem dimaGalya = new DebtItem(2, BigDecimal.valueOf(25.6));
        DebtItem galyaDima = new DebtItem(1, BigDecimal.valueOf(33.6));
        DebtItem dimaJenya = new DebtItem(3, BigDecimal.valueOf(25.8));
        DebtItem jenyaDima = new DebtItem(1, BigDecimal.valueOf(16.9));
        DebtItem jenyaGalya = new DebtItem(2, BigDecimal.valueOf(30.1));
        DebtItem galyaJenya = new DebtItem(3, BigDecimal.valueOf(16.9));

        PersonalDebt jenya = new PersonalDebt(3, List.of(jenyaDima, jenyaGalya));
        PersonalDebt galya = new PersonalDebt(2, List.of(galyaJenya, galyaDima));
        PersonalDebt dima = new PersonalDebt(1, List.of(dimaGalya, dimaJenya));

        List<PersonalDebt> personalDebts = optimizer.optimize(List.of(dima, galya, jenya));

        for (PersonalDebt p : personalDebts) {
            if (p.getUserId() == dima.getUserId()) {
                dima = p;
            }
            if (p.getUserId() == jenya.getUserId()) {
                jenya = p;
            }
        }

        assertDebts(dima, Map.of(2, BigDecimal.valueOf(0.9)));
        assertDebts(jenya, Map.of(2, BigDecimal.valueOf(4.3)));
    }

    @Test
    void test2() {
        DebtOptimizer optimizer = new SimpleDebtOptimizer();

        DebtItem dimaJenyaB =  new DebtItem(2, BigDecimal.valueOf(6.9));
        DebtItem dimaGalya = new DebtItem(3, BigDecimal.valueOf(61.5));
        DebtItem dimaJenyaD = new DebtItem(4, BigDecimal.valueOf(16.5));

        DebtItem jenyaBDima = new DebtItem(1, BigDecimal.valueOf(17.8));
        DebtItem jenyaBGalya = new DebtItem(3, BigDecimal.valueOf(13.9));
        DebtItem jenyaBJenyaD = new DebtItem(4, BigDecimal.valueOf(21.8));

        DebtItem galyaDima = new DebtItem(1, BigDecimal.valueOf(27.1));
        DebtItem galyaJenyaB = new DebtItem(2, BigDecimal.valueOf(15.7));
        DebtItem galyaJenyaD = new DebtItem(4, BigDecimal.valueOf(67.1));

        DebtItem jenyaDDima = new DebtItem(1, BigDecimal.valueOf(31.6));
        DebtItem jenyaDGalya = new DebtItem(3, BigDecimal.valueOf(21.8));


        PersonalDebt dima = new PersonalDebt(1, List.of(dimaGalya, dimaJenyaB, dimaJenyaD));
        PersonalDebt jenyaB = new PersonalDebt(2, List.of(jenyaBJenyaD, jenyaBDima, jenyaBGalya));
        PersonalDebt galya = new PersonalDebt(3, List.of(galyaJenyaB, galyaJenyaD, galyaDima));
        PersonalDebt jenyaD = new PersonalDebt(4, List.of(jenyaDDima, jenyaDGalya));

        List<PersonalDebt> personalDebts = optimizer.optimize(List.of(dima, galya, jenyaB, jenyaD));

        for (PersonalDebt p : personalDebts) {
            if (p.getUserId() == dima.getUserId()) {
                dima = p;
            }
            if (p.getUserId() == galya.getUserId()) {
                galya = p;
            }
            if (p.getUserId() == jenyaD.getUserId()) {
                jenyaD = p;
            }
            if (p.getUserId() == jenyaB.getUserId()) {
                jenyaB = p;
            }
        }
        assertDebts(dima, Map.of(4, BigDecimal.valueOf(8.4)));
        assertDebts(jenyaB, Map.of(4, BigDecimal.valueOf(30.9)));
        assertDebts(galya, Map.of(4, BigDecimal.valueOf(12.7)));
    }


    private void assertDebts(PersonalDebt debt, Map<Integer, BigDecimal> debtValues) {
        for (Integer key : debtValues.keySet()) {
            if (debt.getDebtItemByTargetUserId(key) != null) {
                BigDecimal personalDebt = debt.getDebtItemByTargetUserId(key).getDebtAmount();
                BigDecimal comparedDebt = debtValues.get(key);
                assertEquals(comparedDebt, personalDebt);
            } else {
                Assertions.fail("Has been not found the personal debt with this target id: " + key);
            }
        }
    }
}