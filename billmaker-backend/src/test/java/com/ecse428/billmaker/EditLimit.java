package com.ecse428.billmaker;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.ecse428.billmaker.model.SupervisionRequest;
import com.ecse428.billmaker.model.SupervisorUser;
import com.ecse428.billmaker.model.IndividualUser;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class EditLimit {

    private static IndividualUser individualUser;
    private static Double limit;

    @Given("I logged in as an individual user")
    public void i_logged_in_as_an_individual_user() {

        individualUser = new IndividualUser();

    }

    @When("I edit the account limit to a positive number")
    public void i_edit_the_account_limit_to_a_positive_number() {
        individualUser.setMonthlyLimit(2000);
    }

    @When("I request for the account limit")
    public void i_request_for_the_account_limit() {
        limit = individualUser.getMonthlyLimit();
    }

    @Then("The account limit should match with the value we set")
    public void the_account_limit_should_match_with_the_value_we_set() {
        double expectLimit = 2000;
        assertEquals(java.util.Optional.of(expectLimit), limit);
    }

    @When("I edit the account limit to zero")
    public void i_edit_the_account_limit_to_zero() {

        individualUser.setMonthlyLimit(0);
    }

    @Then("The system should give me an error message of the limit should not be negative")
    public void the_system_should_give_me_an_error_message_of_the_limit_should_not_be_negative() {
        String error = "";
        if (individualUser.getMonthlyLimit() < 0){
            error = "The limit should not be negative";
        }
        assertEquals("The limit should not be negative", error);
    }

}
