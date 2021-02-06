package com.ecse428.billmaker;

import com.ecse428.billmaker.dao.SupervisorUserRepository;
import com.ecse428.billmaker.model.SupervisorUser;
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

@RunWith(Cucumber.class)
@SpringBootTest
public class CreateSupervisorUserTests {
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

    @When("the username {string} and password {string} and email {string} are entered")
    public void theUsernameAndPasswordAndEmailAreEntered(String name, String password, String email) {
        try {
            service.createSupervisorUser(name, password, email);
            usernames.add(name);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("a supervisor account is created with username {string} and password {string} and email {string}")
    public void aSupervisorAccountIsCreatedWithUsernameAndPasswordAndEmail(String name, String password, String email) {
        assertEquals("", errorMessage);

        SupervisorUser temp = service.getSupervisorUser(name);
        assertEquals(name, temp.getUsername());
        assertEquals(password, temp.getPassword());
        assertEquals(email, temp.getEmail());
    }

    @Given("a supervisor user has been created with username {string} and password {string} and email {string}")
    public void aSupervisorUserHasBeenCreatedWithUsernameAndPasswordAndEmail(String name, String password, String email) {
        try {
            service.createSupervisorUser(name, password, email);
            usernames.add(name);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the error message {string} is returned")
    public void theErrorMessageIsReturned(String error) {
        assertEquals(error, errorMessage);
    }

}
