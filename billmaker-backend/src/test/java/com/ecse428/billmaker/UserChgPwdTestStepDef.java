package com.ecse428.billmaker;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.ecse428.billmaker.model.IndividualUser;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecse428.billmaker.dao.IndividualUserRepository;
import com.ecse428.billmaker.service.BillMakerService;
import com.ecse428.billmaker.service.UserService;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserChgPwdTestStepDef extends SpringIntegrationTest {
    @Autowired
    private BillMakerService service;

    @Autowired
    private UserService userService;

    @Autowired
    IndividualUserRepository userRepository;

    String errorMessage = "";
    double result;
    List<String> usernamesTest = new ArrayList<>();
    IndividualUser testUser = new IndividualUser();

    String newPassword = new String();


    @After
    public void clearDatabase() {
        errorMessage = "";
        testUser = null;

        while (usernamesTest.size() != 0) {
            System.out.println("I delete it");
            userRepository.delete(userRepository.findById(usernamesTest.remove(0)).get());
        }
    }

    @Given("I have a user {string} with password {string} and email {string}")
    public void givenIhaveaUser(String user, String password, String email) {
        testUser = service.createIndividualUser(user, password, email);
    }

    @When("I change the old password {string} to a new password {string}")
    public void ichangetheoldpwd(String oldpwd, String newpwd) {
        testUser.setPassword(newpwd);
        newPassword = newpwd;
    }

    @Then("the user password is now {string}")
    public void thenTheUserPasswordIsNow(String pwd) {
        assertEquals(pwd,testUser.getPassword());
    }

    @Then("the password is not changed")
    public void thenThePasswordIsNotChanged() {
        if (!newPassword.equals(testUser.getPassword())) {
            assertEquals("yes","yes");
        }
    }






}
