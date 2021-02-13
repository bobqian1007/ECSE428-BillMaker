package com.ecse428.billmaker.dao;

import com.ecse428.billmaker.model.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, String> {

    Expense findExpenseById(int id);
    Expense findExpenseByLocation(String location);
}
