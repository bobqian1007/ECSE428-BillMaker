package com.ecse428.billmaker;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.ecse428.billmaker.model.SupervisionRequest;
import com.ecse428.billmaker.model.SupervisorUser;
import com.ecse428.billmaker.model.IndividualUser;

import java.util.HashSet;
import java.util.Set;

public class SendSuperviseRequestTests {

    private static IndividualUser individualUser;
    private static SupervisorUser supervisorUser1;
    private static SupervisorUser supervisorUser2;
    private static SupervisionRequest supervisionRequest;

    @Given("I logged in as a supervisor user")
    public void i_logged_in_as_a_supervisor_user() {

        supervisorUser1 = new SupervisorUser();

    }

    @Given("The other user is an individual user")
    public void the_other_user_is_an_individual_user() {

        individualUser = new IndividualUser();

    }

    @When("I send the supervise request to the other user")
    public void i_send_the_supervise_request_to_the_other_user() {

        supervisionRequest = new SupervisionRequest();
        supervisionRequest.setId(0);
        supervisionRequest.setStatus(Boolean.FALSE);
        supervisionRequest.setSupervisorUser(supervisorUser1);
        Set<SupervisionRequest> newRequests = new HashSet<>();
        newRequests.add(supervisionRequest);
        supervisorUser1.setSupervisionRequests(newRequests);

    }

    @Then("The other user receives the supervisor's request")
    public void the_other_user_receives_the_supervisor_s_request() {

        supervisionRequest.setStatus(Boolean.TRUE);
        supervisionRequest.setIndividualUser(individualUser);
        Set<SupervisionRequest> newRequests = new HashSet<>();
        newRequests.add(supervisionRequest);
        individualUser.setSupervisionRequests(newRequests);

    }

    @Given("The other user is a supervisor user")
    public void the_other_user_is_a_supervisor_user() {

        supervisorUser2 = new SupervisorUser();

    }

    @Then("I shall be notified that the supervise request is not supported by the system")
    public void i_shall_be_notified_that_the_supervise_request_is_not_supported_by_the_system() {

        supervisorUser1.getSupervisionRequests().remove(supervisionRequest);
        System.out.println("This supervise request is not supported by the system.");

    }

}
