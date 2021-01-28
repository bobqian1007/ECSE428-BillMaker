package com.ecse428.billmaker.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Expense extends Transaction{
   private String location;

public void setLocation(String value) {
    this.location = value;
}
public String getLocation() {
    return this.location;
}
   private Set<Category> categories;
   
   @OneToMany(mappedBy="expense" )
   public Set<Category> getCategories() {
      return this.categories;
   }
   
   public void setCategories(Set<Category> categoriess) {
      this.categories = categoriess;
   }
   
   }
