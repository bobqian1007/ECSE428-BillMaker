package com.ecse428.billmaker;

import com.ecse428.billmaker.dao.IndividualUserRepository;
import com.ecse428.billmaker.model.IndividualUser;
import com.ecse428.billmaker.service.BillMakerService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class IndividualUserChangesEmailAddress extends SpringIntegrationTest {

    @Autowired
    private IndividualUserRepository individualUserRepository;

    @Autowired
    private BillMakerService billMakerService;

    private IndividualUser individualUser;
    private String errorMessage = "";

    @Given("I am a individual user {string} with password {string} and email {string}")
    public void iAmAUserWithPasswordAndEmail(String arg0, String arg1, String arg2) {
        individualUser = billMakerService.createIndividualUser(arg0, arg1, arg2);
    }

    @When("I change the email {string} to the new email address {string}")
    public void iChangeTheEmailToTheNewEmailAddress(String arg0, String arg1) {
        try {
            assert individualUser.getEmail().equals(arg0);
        } catch (Exception e){
            errorMessage = errorMessage + "Email addresses mismatch";
            System.out.println(errorMessage);
        }
        try {
            individualUser = billMakerService.updateIndividualUserEmail(individualUser.getUsername(), arg1);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the user email address is now {string}")
    public void theUserEmailAddressIsNow(String arg0) {
        try {
            assert individualUser.getEmail().equals(arg0);
        } catch (Exception e){
            errorMessage = errorMessage + "Email addresses mismatch";
        }
    }

    @Then("the error message {string} is returned for change individual user email address")
    public void theErrorMessageIsReturnedForChangeIndividualUserEmailAddress(String arg0) {
        assertEquals(arg0, errorMessage);
    }
}
