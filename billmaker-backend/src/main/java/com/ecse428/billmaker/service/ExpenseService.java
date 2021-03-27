package com.ecse428.billmaker.service;


import com.ecse428.billmaker.dao.ExpenseRepository;
import com.ecse428.billmaker.dao.IndividualUserRepository;
import com.ecse428.billmaker.model.Category;
import com.ecse428.billmaker.model.Expense;
import com.ecse428.billmaker.model.IndividualUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ExpenseService {
    @Autowired
    private IndividualUserRepository individualUserRepository;
    @Autowired
    private ExpenseRepository expenseRepository;

    @Transactional
    public Expense createExpense(int id, double amount, String location, String individualUserName, Date date, String description, Set<Category> categories) {
        IndividualUser individualUser = individualUserRepository.findByUsername(individualUserName);
        if (individualUser == null) {
            throw new NullPointerException("No such individual user");
        }

        if (amount < 0){
            throw new NullPointerException("Amount cannot be negative!");
        }

        Expense expense = new Expense();
        expense.setId(id);
        expense.setAmount(amount);
        expense.setLocation(location);
        expense.setIndividualUser(individualUser);
        expense.setDate(date);
        expense.setDescription(description);
        expense.setCategories(categories);
        expenseRepository.save(expense);
        return expense;
    }

    @Transactional
    public Expense getExpenseById(int id) {
        return expenseRepository.findExpenseById(id);
    }

    @Transactional
    public Expense getExpenseByLocation(String location) {
        return expenseRepository.findExpenseByLocation(location);
    }

    @Transactional
    public List<Expense> getAllExpenses() {
        return toList(expenseRepository.findAll());
    }

    @Transactional
    public boolean delete(int id) {
        Expense expense = expenseRepository.findExpenseById(id);
        if (expense == null) {
            throw new NullPointerException("Record not exists");
        }
        expenseRepository.delete(expense);
        return true;
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

    @Transactional
    public Expense editBill(Expense e, int amount) {
        if (amount<0){
            throw new IllegalArgumentException("Bill amount cannot be 0!");
        }
        else e.setAmount(amount);
        return e;
    }
}