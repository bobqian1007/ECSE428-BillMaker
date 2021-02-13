package com.ecse428.billmaker.Service;

import com.ecse428.billmaker.dao.ExpenseRepository;
import com.ecse428.billmaker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    @Transactional
    public Expense editBill(Expense e, int amount) {
        if (amount<0){
            throw new IllegalArgumentException("Bill amount cannot be 0!");
        }
        else e.setAmount(amount);
        return e;
    }

}
