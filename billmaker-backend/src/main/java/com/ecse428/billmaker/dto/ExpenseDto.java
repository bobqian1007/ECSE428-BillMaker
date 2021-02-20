package com.ecse428.billmaker.dto;

import com.ecse428.billmaker.model.Category;

import java.sql.Date;
import java.util.Set;

public class ExpenseDto {
    private int id;
    private double amount;
    private String location;
    private String individualUserName;
    private Date date;
    private String description;
    private Set<Category> categories;

    public ExpenseDto(){

    }

    public ExpenseDto(int id, double amount, String location, String individualUserName, Date date, String description, Set<Category> categories) {
        this.id = id;
        this.amount = amount;
        this.location = location;
        this.individualUserName = individualUserName;
        this.date = date;
        this.description = description;
        this.categories = categories;
    }

    public int getExpenseId() {
        return id;
    }

    public double getExpenseAmount() {
        return amount;
    }

    public String getExpenseLocation() {
        return location;
    }

    public String getExpenseIndividualUserName() {
        return individualUserName;
    }

    public Date getExpenseDate() {
        return date;
    }

    public String getExpenseDescription() {
        return description;
    }

    public Set<Category> getExpenseCategory() {
        return categories;
    }
}
