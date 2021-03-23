package com.ecse428.billmaker;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecse428.billmaker.dao.IndividualUserRepository;
import com.ecse428.billmaker.dao.SupervisorUserRepository;
import com.ecse428.billmaker.model.SupervisionRequest;
import com.ecse428.billmaker.model.SupervisorUser;
import com.ecse428.billmaker.service.BillMakerService;
import com.ecse428.billmaker.service.UserService;
import com.ecse428.billmaker.dao.SupervisionRequestRepository;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SupervisorRemoveLimit extends SpringIntegrationTest{
	@Autowired
	 private BillMakerService service;
	 
	 @Autowired
	 private UserService userService;
	 
	 @Autowired
	 IndividualUserRepository userRepository;
	 
	 @Autowired
	 SupervisorUserRepository sRepository;
	 
	 @Autowired
	 SupervisionRequestRepository srr;
	 
	 String errorMessage = "";
	 double result;
	 List<String> usernamesTest = new ArrayList<>();
	 List<String> snamesTest = new ArrayList<>();
	 
	 @After
	 public void clearDatabase() {
		 errorMessage = "";
		 while (snamesTest.size() != 0) {
			 System.out.println("I delete it");
			 SupervisorUser svr = sRepository.findById(snamesTest.remove(0)).get();
			 for (SupervisionRequest sr:svr.getSupervisionRequests()) {
				 srr.delete(sr);
			 }
	         sRepository.delete(svr);
	     }
		 while (usernamesTest.size() != 0) {
			 System.out.println("I delete it");
	         userRepository.delete(userRepository.findById(usernamesTest.remove(0)).get());
	     }

	 }
	 @Given("I have a supervisor account")
	 public void givenIloggedinasanindividualuser() {
		 System.out.println("I add a user");
	     String name = "bobqian";
	     snamesTest.add(name);
	     service.createSupervisorUser(name, "123456", "bobqian1007@outlook.com");
	 }
	 @Given("I have a supervisee")
	 public void setSupervisee() {
		 String name = "zhs";
	     usernamesTest.add(name);
	     service.createSupervisorUser(name, "123456", "zzz@outlook.com");
	     userService.createSupervisionRequest(snamesTest.get(snamesTest.size()-1), name);
	 }
	 @Given("my supervisee have account limit")
	 public void ihaveaccountlimit() {
		 userService.editMonthLimit(usernamesTest.get(usernamesTest.size()-1), 100);
	 }
	 @Given("my supervisee do not have account limit")
	 public void idonothaveaccountlimit() {

	 }
	 @When("I remove his/her account limit")
	 public void iremoveaccountlimit() {
		 userService.SupervisorRemoveLimit(snamesTest.get(0),usernamesTest.get(0));
	 }
	 @When("I request for his/her account limit")
	 public void irequestaccountlimit() {
		 result = userService.getMonthLimit(usernamesTest.get(0));
	 }
	 @Then("his/her account limit should be infinity")
	 public void checkResult() {
		 assertEquals(Double.POSITIVE_INFINITY,result,0.0001);
	 }
}
