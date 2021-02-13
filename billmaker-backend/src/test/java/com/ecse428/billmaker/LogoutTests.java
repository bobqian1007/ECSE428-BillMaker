package com.ecse428.billmaker;

import com.ecse428.billmaker.dao.SupervisorUserRepository;
import com.ecse428.billmaker.model.SupervisorUser;
import com.ecse428.billmaker.model.User;
import com.ecse428.billmaker.service.BillMakerService;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

import io.cucumber.java.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class LogoutTests extends SpringIntegrationTest{
    @Autowired
    private BillMakerService service;

    @Autowired
    SupervisorUserRepository supervisorUserRepository;

    String errorMessage = "";
    List<String> usernames = new ArrayList<>();

    @After
    public void clearDatabase() {
        errorMessage = "";

        while (usernames.size() != 0) {
            supervisorUserRepository.delete(supervisorUserRepository.findById(usernames.remove(0)).get());
        }


    }

    @Given("I am logged into the Bill Management System as an individual user")
    public void IAmLoggedIntoTheBillManagementSystemAsAnIndividualUser(User user) {
        try {
            BillMakerService.login(user);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("I log out")
    public void ILogOut(){
        try {
            BillMakerService.logout();
        } catch (IllegalStateException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("I can no longer access my account")
    public void ICanNoLongerAccessMyAccount() {
        assertEquals("", errorMessage);

        assertEquals(null, BillMakerService.getUser());
    }

    @Given("I am not logged into the Bill Management System")
    public void IAmNotLoggedIntoTheBillManagementSystem() {
        try {
            BillMakerService.logout();
        } catch (IllegalStateException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the system will display the {string} error message")
    public void theSystemWillDisplayTheErrorMessage(String error) {
        assertEquals(errorMessage, error);
    }

}
