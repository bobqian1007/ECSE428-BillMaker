package com.ecse428.billmaker;

import com.ecse428.billmaker.dao.SupervisorUserRepository;
import com.ecse428.billmaker.model.SupervisorUser;
import com.ecse428.billmaker.service.BillMakerService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class SupervisorUserChangesEmailAddress extends SpringIntegrationTest {

    @Autowired
    private SupervisorUserRepository supervisorUserRepository;

    @Autowired
    private BillMakerService billMakerService;

    private SupervisorUser supervisorUser;
    private String errorMessage = "";

    @Given("I have a supervisor account {string} with password {string} and email {string}")
    public void iAmAUserWithPasswordAndEmail(String arg0, String arg1, String arg2) {
        billMakerService.createSupervisorUser(arg0, arg1, arg2);
        supervisorUser = billMakerService.getSupervisorUser(arg0);
    }

    @When("I change the supervisor email {string} to the new email address {string}")
    public void iChangeTheEmailToTheNewEmailAddress(String arg0, String arg1) {
        try {
            assert supervisorUser.getEmail().equals(arg0);
        } catch (Exception e){
            errorMessage = errorMessage + "Email addresses mismatch";
            System.out.println(errorMessage);
        }
        try {
            billMakerService.updateSupervisorUserEmail(supervisorUser.getUsername(), arg1);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }


    @Then("the error message {string} is returned for change supervisor user email address")
    public void theErrorMessageIsReturnedForChangeSupervisorUserEmailAddress(String arg0) {
        assertEquals(arg0, errorMessage);
    }
}
