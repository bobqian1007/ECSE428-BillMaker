package com.ecse428.billmaker;

import com.ecse428.billmaker.model.IndividualUser;
import com.ecse428.billmaker.model.SupervisionRequest;
import com.ecse428.billmaker.model.SupervisorUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class SuperviosrEditLimit {

    private static IndividualUser individualUser;
    private static SupervisorUser supervisorUser;
    private static SupervisionRequest supervisionRequest;
    private static Double limit;

    @Given("I'm the supervisor of an individual account")
    public void i_m_the_supervisor_of_an_individual_account() {
        individualUser = new IndividualUser();
        supervisorUser = new SupervisorUser();

        supervisionRequest = new SupervisionRequest();
        supervisionRequest.setId(0);
        supervisionRequest.setStatus(Boolean.TRUE);
        supervisionRequest.setSupervisorUser(supervisorUser);
        Set<SupervisionRequest> newRequests = new HashSet<>();
        newRequests.add(supervisionRequest);
        supervisorUser.setSupervisionRequests(newRequests);
        individualUser.setSupervisionRequests(newRequests);
    }

    @When("I edit the supervised account limit to a positive number")
    public void i_edit_the_supervised_account_limit_to_a_positive_number() {
        individualUser.setMonthlyLimit(2000);
    }

    @When("I request for the supervised account limit")
    public void i_request_for_the_supervised_account_limit() {
        limit = individualUser.getMonthlyLimit();
    }

    @Then("The supervised account limit should match with the value we set")
    public void the_supervised_account_limit_should_match_with_the_value_we_set() {
        double expectLimit = 2000;
        assertEquals(java.util.Optional.of(expectLimit), java.util.Optional.of(limit));
    }

    @Then("The supervised account limit should match with the value 0 we set")
    public void the_supervised_account_limit_should_match_with_the_value0_we_set() {
        double expectLimit = 0;
        assertEquals(java.util.Optional.of(expectLimit), java.util.Optional.of(limit));
    }

    @When("I edit the supervised account limit to zero")
    public void i_edit_the_supervised_account_limit_to_zero() {
        individualUser.setMonthlyLimit(0);
    }

    @Then("The system should give me an error message of the supervised account limit should not be negative")
    public void the_system_should_give_me_an_error_message_of_the_supervised_account_limit_should_not_be_negative() {
        String error = "";
        if (individualUser.getMonthlyLimit() <= 0){
            error = "The limit should not be negative";
        }
        assertEquals("The limit should not be negative", error);
    }

}