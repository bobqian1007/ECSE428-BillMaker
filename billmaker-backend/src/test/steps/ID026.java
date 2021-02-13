package Steps;

import com.ecse428.billmaker.model.Expense;
import com.ecse428.billmaker.model.IndividualUser;
import com.ecse428.billmaker.model.Transaction;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.HashSet;
import java.util.Set;

public class ID026 {

    @Given("I have an account")
    public void i_have_an_account() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @And("I have no supervisor")
    public void i_have_no_supervisor() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @And("The supervisor has an account")
    public void the_supervisor_has_an_account() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @And("the supervisor has sent a supervise request to me")
    public void the_supervisor_has_sent_a_supervise_request_to_me() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //***** Scenario: User in family account accept the supervisor request *****
    @When("I approve the supervise request")
    public void i_approve_the_supervise_request() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I verify that my account has a supervisor")
    public void i_verify_that_my_account_has_a_supervisor() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //***** Scenario: User in family account refuse the supervisor request *****
    @When("I refuse the supervise request")
    public void i_refuse_the_supervise_request() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I verify that my account has no supervisor")
    public void i_verify_that_my_account_has_no_supervisor() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //***** Scenario: User in family account accept the second supervisor request *****
    @When("I approve the the supervise request")
    public void i_approve_the_the_supervise_request() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I shall be notified that each user can not have two supervisor")
    public void i_shall_be_notified_that_each_user_can_not_have_two_supervisor() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}