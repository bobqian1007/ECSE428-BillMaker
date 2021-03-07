package com.ecse428.billmaker.dto;

import java.util.HashSet;
import java.util.Set;

import com.ecse428.billmaker.model.Category;
import com.ecse428.billmaker.model.Expense;

public class CategoryDto {
    private String name;
    private Set<Expense> expenses;

    public CategoryDto(String name, Set<Expense> expenses) {
        this.name = name;
        this.expenses = expenses;
    }

    public CategoryDto(Category c) {
        this.name = c.getName();
        HashSet<Expense> list = new HashSet<Expense>();

        list.add(c.getExpense());
        this.expenses = list;
    }

    public String getName() { return name; }
    public Set<Expense> getExpenses() { return expenses; }
    
}
