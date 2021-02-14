package com.ecse428.billmaker;

import com.ecse428.billmaker.dao.SupervisorUserRepository;
import com.ecse428.billmaker.model.IndividualUser;
import com.ecse428.billmaker.model.SupervisionRequest;
import com.ecse428.billmaker.model.SupervisorUser;
import com.ecse428.billmaker.service.BillMakerService;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class acceptSupervisorRequest extends SpringIntegrationTest{
//    @Autowired
//    private BillMakerService service;
//
//    @Autowired
//    SupervisorUserRepository supervisorUserRepository;
//
//    String errorMessage = "";
//    List<String> usernames = new ArrayList<>();
//
//    @After
//    public void clearDatabase() {
//        errorMessage = "";
//
//        while (usernames.size() != 0) {
//            supervisorUserRepository.delete(supervisorUserRepository.findById(usernames.remove(0)).get());
//        }
//
//
//    }

    private static IndividualUser individualUser;
    private static SupervisorUser supervisorUser1;
    private static SupervisorUser supervisorUser2;
    private static SupervisionRequest supervisionRequest;
    private static SupervisionRequest supervisionRequest2;

    @Given("I have an account")
    public void i_have_an_account() {
        // Write code here that turns the phrase above into concrete actions
        individualUser = new IndividualUser();
    }

    @And("The supervisor has an account")
    public void the_supervisor_has_an_account() {
        supervisorUser1 = new SupervisorUser();
    }

    @And("the supervisor has sent a supervise request to me")
    public void the_supervisor_has_sent_a_supervise_request_to_me() {

        supervisionRequest = new SupervisionRequest();
        supervisionRequest.setId(0);
        supervisionRequest.setStatus(Boolean.FALSE);
        supervisionRequest.setSupervisorUser(supervisorUser1);
        Set<SupervisionRequest> newRequests = new HashSet<>();
        newRequests.add(supervisionRequest);
        supervisorUser1.setSupervisionRequests(newRequests);
        individualUser.setSupervisionRequests(newRequests);
    }

    //***** Scenario: User in family account accept the supervisor request *****
    @When("I approve the supervise request")
    public void i_approve_the_supervise_request() {

        supervisionRequest.setStatus(true);
    }
    @Then("I verify that my account has a supervisor")
    public void i_verify_that_my_account_has_a_supervisor() {
        // Write code here that turns the phrase above into concrete actions
        Set<SupervisionRequest> requests = individualUser.getSupervisionRequests();


        for (SupervisionRequest req : requests) {
            if (req.equals(supervisionRequest))
                assertEquals(true, req.getStatus());
        }

    }

    //***** Scenario: User in family account refuse the supervisor request *****
    @When("I refuse the supervise request")
    public void i_refuse_the_supervise_request() {
        supervisionRequest.setStatus(false);
        Set<SupervisionRequest> empty = new HashSet<>();
        individualUser.setSupervisionRequests(empty);
    }
    @Then("I verify that my account has no supervisor")
    public void i_verify_that_my_account_has_no_supervisor() {
        boolean haveSupervisor;
        Set<SupervisionRequest> result = individualUser.getSupervisionRequests();
        if (result.isEmpty()) {
            haveSupervisor = true;
        } else {
            haveSupervisor = false;
        }
        assertEquals(true, haveSupervisor);
    }

    //***** Scenario: User in family account accept the second supervisor request *****
    @When("Another supervisor sent request to me")
    public void another_supervisor_sent_request_to_me() {
        supervisorUser2 = new SupervisorUser();
        supervisionRequest2 = new SupervisionRequest();
        supervisionRequest2.setId(1);
        supervisionRequest2.setStatus(Boolean.FALSE);
        supervisionRequest2.setSupervisorUser(supervisorUser2);
        Set<SupervisionRequest> requests = individualUser.getSupervisionRequests();
        requests.add(supervisionRequest2);

    }

    @When("I approve the the second supervise request")
    public void i_approve_the_the_second_supervise_request() {

    }

    @Then("I shall be notified that each user can not have two supervisor")
    public void i_shall_be_notified_that_each_user_can_not_have_two_supervisor() {
        boolean moreThanOneRequest;
        Set<SupervisionRequest> requests = individualUser.getSupervisionRequests();
        if (requests.size()>1){
            moreThanOneRequest = true;
        } else {
            moreThanOneRequest = false;
        }
        assertEquals(true, moreThanOneRequest);
    }

}
