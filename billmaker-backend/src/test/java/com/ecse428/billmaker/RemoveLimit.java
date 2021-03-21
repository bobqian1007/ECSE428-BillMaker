package com.ecse428.billmaker;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecse428.billmaker.dao.IndividualUserRepository;
import com.ecse428.billmaker.service.BillMakerService;
import com.ecse428.billmaker.service.UserService;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RemoveLimit extends SpringIntegrationTest {
	 @Autowired
	 private BillMakerService service;
	 
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 IndividualUserRepository userRepository;
	 
	 String errorMessage = "";
	 double result;
	 List<String> usernamesTest = new ArrayList<>();
	 
	 
	 @After
	 public void clearDatabase() {
		 errorMessage = "";

		 while (usernamesTest.size() != 0) {
			 System.out.println("I delete it");
	         userRepository.delete(userRepository.findById(usernamesTest.remove(0)).get());
	     }


	 }
	 
	 @Given("I have a account")
	 public void givenIloggedinasanindividualuser() {
		 System.out.println("I add a user");
	     String name = "bobqian";
	     usernamesTest.add(name);
	     service.createIndividualUser(name, "123456", "bobqian1007@outlook.com");
	 }
	 @Given("I have account limit")
	 public void ihaveaccountlimit() {
		 userService.editMonthLimit(usernamesTest.get(0), 100);
	 }
	 @Given("I do not have account limit")
	 public void idonothaveaccountlimit() {

	 }
	 
	 @When("I remove the account limit")
	 public void iremoveaccountlimit() {
		 userService.removeMonthLimit(usernamesTest.get(0));
	 }
	 @When("I request for the account limit")
	 public void irequestaccountlimit() {
		 result = userService.getMonthLimit(usernamesTest.get(0));
	 }
	 @Then("The account limit should be infinity")
	 public void checkResult() {
		 assertEquals(Double.POSITIVE_INFINITY,result,0.0001);
	 }
	 
}
