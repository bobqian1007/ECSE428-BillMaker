package com.ecse428.billmaker.model;

import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public abstract class Transaction{
   private double amount;

public void setAmount(double value) {
    this.amount = value;
}
public double getAmount() {
    return this.amount;
}
private Date date;

public void setDate(Date value) {
    this.date = value;
}
public Date getDate() {
    return this.date;
}
private String description;

public void setDescription(String value) {
    this.description = value;
}
public String getDescription() {
    return this.description;
}
private IndividualUser individualUser;

@ManyToOne(optional=false)
public IndividualUser getIndividualUser() {
   return this.individualUser;
}

public void setIndividualUser(IndividualUser individualUser) {
   this.individualUser = individualUser;
}

private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
}
