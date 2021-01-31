package com.ecse428.billmaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="BillUser")
public abstract class User{
   private String username;

public void setUsername(String value) {
    this.username = value;
}
@Id
public String getUsername() {
    return this.username;
}
private String email;

public void setEmail(String value) {
    this.email = value;
}
public String getEmail() {
    return this.email;
}
private String password;

public void setPassword(String value) {
    this.password = value;
}
public String getPassword() {
    return this.password;
}
}
