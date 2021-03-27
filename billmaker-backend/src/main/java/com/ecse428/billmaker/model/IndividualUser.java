package com.ecse428.billmaker.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
public class IndividualUser extends User{
   private double monthlyLimit;

public void setMonthlyLimit(double value) {
    this.monthlyLimit = value;
}
public double getMonthlyLimit() {
    return this.monthlyLimit;
}
   private Set<SupervisionRequest> supervisionRequests;
   
   @OneToMany(mappedBy="individualUser" )
   public Set<SupervisionRequest> getSupervisionRequests() {
      return this.supervisionRequests;
   }
   
   public void setSupervisionRequests(Set<SupervisionRequest> supervisionRequestss) {
      this.supervisionRequests = supervisionRequestss;
   }
   
   private Set<Transaction> transactions;
   
   @OneToMany(mappedBy="individualUser" ,fetch = FetchType.EAGER, cascade={CascadeType.ALL})
   public Set<Transaction> getTransactions() {
      return this.transactions;
   }
   
   public void setTransactions(Set<Transaction> transactionss) {
      this.transactions = transactionss;
   }
   
   }
