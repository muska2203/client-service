package com.devmode.clientservice.debts.algorithms;

import com.devmode.clientservice.debts.people.DebtItem;
import com.devmode.clientservice.debts.people.PersonalDebt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Qualifier(value = "smartDebtOptimizer")
public class SmartDebtOptimizer extends SimpleDebtOptimizer {

    @Override
    public List<PersonalDebt> optimize(Collection<PersonalDebt> personalDebtCollection) {
        Map<String, DebtInfo> debts = new HashMap<>();
        for (PersonalDebt personalDebt : personalDebtCollection) {
            debts.put(personalDebt.getUserId(), new DebtInfo());
        }
        for (PersonalDebt personalDebt: personalDebtCollection) {
            DebtInfo userDeptInfo = debts.get(personalDebt.getUserId());
            for (DebtItem debtItem : personalDebt.getDebtItems()) {
                BigDecimal debtAmount = debtItem.getDebtAmount();
                userDeptInfo.addDept(debtAmount);
                debts.get(debtItem.getTargetUserId()).subtractDept(debtAmount);
            }
        }
        Map<String, PersonalDebt> personalDebtMap = new HashMap<>();
        for (Map.Entry<String, DebtInfo> deptInfoEntry : debts.entrySet()) {
            String userId = deptInfoEntry.getKey();
            DebtInfo userDept = deptInfoEntry.getValue();
            if (userDept.getDept().compareTo(BigDecimal.ZERO) > 0) {
                for (Map.Entry<String, DebtInfo> target : debts.entrySet()) {
                    DebtInfo targetDept = target.getValue();
                    if (!userId.equals(target.getKey()) && userDept.getDept().compareTo(BigDecimal.ZERO) > 0 && targetDept.getDept().compareTo(BigDecimal.ZERO) < 0) {
                        BigDecimal abs = userDept.getDept().min(targetDept.getDept().abs());
                        userDept.subtractDept(abs);
                        targetDept.addDept(abs);
                        addDept(personalDebtMap, userId, target.getKey(), abs);
                    }
                }
            }
        }

        return new ArrayList<>(personalDebtMap.values());
    }

    private static void addDept(Map<String, PersonalDebt> personalDebts, String userId, String targetId, BigDecimal amount) {
        PersonalDebt personalDebt = personalDebts.get(userId);
        if (personalDebt == null) {
            personalDebt = new PersonalDebt(userId);
            personalDebts.put(userId, personalDebt);
        }
        personalDebt.getDebtItems().add(new DebtItem(targetId, amount));
    }

    private static class DebtInfo {
        private BigDecimal dept = BigDecimal.ZERO;

        public void addDept(BigDecimal dept) {
            this.dept = this.dept.add(dept);
        }
        public void subtractDept(BigDecimal dept) {
            this.dept = this.dept.subtract(dept);
        }

        public BigDecimal getDept() {
            return this.dept;
        }
    }
}
