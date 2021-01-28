package com.ecse428.billmaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Category{
   private String name;

public void setName(String value) {
    this.name = value;
}
@Id
public String getName() {
    return this.name;
}
   private Expense expense;
   
   @ManyToOne
   public Expense getExpense() {
      return this.expense;
   }
   
   public void setExpense(Expense expense) {
      this.expense = expense;
   }
   
   }
