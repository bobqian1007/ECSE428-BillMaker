package com.ecse428.billmaker;

import io.cucumber.java.en.When;

public class DeleteUserTestDef {

    @When("I delete the user {string} with password {string} and email {string}")
    public void whenideletetheuserwithpasswordandemail(String username, String password, String email) {

    }

    @When("the user {string} does not exist in the system")
    public void theuserdoesnotexistinthesystem(String username) {

    }

}
