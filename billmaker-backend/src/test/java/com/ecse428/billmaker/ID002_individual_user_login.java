package com.ecse428.billmaker;

import com.ecse428.billmaker.BillMakerBackendApplication;
import com.ecse428.billmaker.dao.SupervisorUserRepository;
import com.ecse428.billmaker.model.*;
import com.ecse428.billmaker.service.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import org.json.JSONException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.junit.Assert.*;

public class ID002_individual_user_login {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserService service;

    String email = new String();
    String status = new String();

    @Before
    public void waitBeforeFirstStart() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Given("I have a user {string} with password {string}")
    public void iHaveAUserUserIDWithPasswordPassword(String id, String pw) {
        String dtype = "USER";
        String rand = Double.toString(Math.random());
        email = id;
        String query = ("INSERT INTO bill_user (dtype, username, email, password, monthly_limit)"
            + "VALUES ("+ dtype + "," + rand + ", '" + id + "', '" + pw + "', '0')");
        jdbcTemplate.update(query);
    }

    @When("I use the username {string} and password {string} to login")
    public void iUseTheUsernameUserIDAndPasswordPasswordToLogin(String id, String pw) {
        HttpRequest httpRequest = new HttpRequest();

        try {
            status = Integer.toString(httpRequest.POST("http://localhost:8080/login?email=" + id + "&password=" + pw));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Then("the returned state is {string}") public void theReturnedStateIsState(String state) {
        assertEquals(state, state);
    }

    @Given("I do not have a user {string} with password {string}")
    public void iDoNotHaveAUserUserIDWithPasswordPassword(String id, String pw) {
        HttpRequest httpRequest = new HttpRequest();
        try {
            assertEquals(401,httpRequest.POST("http://localhost:8080/login?email=" + id + "&password=" + pw));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void cleanUpAll() {
        String query = ("DELETE FROM bill_user WHERE email= '" + email +"'");
        jdbcTemplate.update(query);
    }
}
