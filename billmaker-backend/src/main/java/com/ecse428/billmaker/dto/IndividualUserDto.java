package com.ecse428.billmaker.dto;

public class IndividualUserDto {
    private String username;
    private String email;
    private String password;
    private double monthlimit;

    public IndividualUserDto(String username, String password, String email,double limit) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.monthlimit = limit;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
