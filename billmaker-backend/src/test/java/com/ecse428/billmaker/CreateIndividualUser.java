package com.ecse428.billmaker;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import com.ecse428.billmaker.dao.IndividualUserRepository;
import com.ecse428.billmaker.model.IndividualUser;
import com.ecse428.billmaker.service.BillMakerService;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CreateIndividualUser extends SpringIntegrationTest{

    @Autowired
    private BillMakerService service;

    @Autowired
    IndividualUserRepository individualUserRepository;

    String errorMessage = "";
    List<String> usernames = new ArrayList<>();

    @After
    public void clearDatabase() {
        errorMessage = "";

        while (usernames.size() != 0) {
            individualUserRepository.delete(individualUserRepository.findById(usernames.remove(0)).get());
        }
    }

    @When("the individual username {string} and password {string} and email {string} are entered")
    public void theIndividualUsernameAndPasswordAndEmailAreEntered(String username, String password, String email) {
        try {
            service.createIndividualUser(username, password, email);
            usernames.add(username);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Given("an individual user has been created with username {string} and password {string} and email {string}")
    public void an_individual_user_has_been_created_with_username_and_password_and_email(String username, String password, String email) {
        try {
            service.createIndividualUser(username, password, email);
            usernames.add(username);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("an individual account is created with username {string} and password {string} and email {string}")
    public void an_individual_account_is_created_with_username_and_password_and_email(String username, String password, String email) {
        assertEquals("", errorMessage);

        IndividualUser temp = service.getIndividualUser(username);
        assertEquals(username, temp.getUsername());
        assertEquals(password, temp.getPassword());
        assertEquals(email, temp.getEmail());
    }

    @Then("the error message {string} is returned for individual user creation")
    public void theErrorMessageIsReturnedForIndividualUserCreation(String error) {
        assertEquals(error, errorMessage);
    }

}
