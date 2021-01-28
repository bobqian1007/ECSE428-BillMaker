package com.ecse428.billmaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BillMakerManager{
   private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
}
