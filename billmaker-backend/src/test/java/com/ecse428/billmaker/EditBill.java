package com.ecse428.billmaker;
import com.ecse428.billmaker.model.*;
import com.ecse428.billmaker.service.*;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class EditBill {

    Expense e;
    int amount = 10;
    ExpenseService es = new ExpenseService();
    String error = "";

    @Given("user is logged into the Bill management system as an indivudual user")
    public void user_is_logged_into_the_bill_management_system_as_an_indivudual_user() {
        error ="";
        amount = 10;

    }

    @Given("there is a bill recorded in the system")
    public void there_is_a_bill_recorded_in_the_system() {
        // Write code here that turns the phrase above into concrete actions
        e = new Expense();

    }

    @When("user change the amount of the bill to a different amount")
    public void user_change_the_amount_of_the_bill_to_a_different_amount() {
        es.editBill(e, 10);
    }

    @When("user query the amount of the bill")
    public void user_query_the_amount_of_the_bill() {
        amount = (int) e.getAmount();
    }

    @Then("the amount of the bill has changed to the expected amount")
    public void the_amount_of_the_bill_has_changed_to_the_expected_amount() {
        assertEquals(amount, (int) e.getAmount());
    }

    @When("user change the amount of the bill to a same amount")
    public void user_change_the_amount_of_the_bill_to_a_same_amount() {
        try
        {
            es.editBill(e, 10);
        }
        catch(IllegalArgumentException re)
        {
            error = re.getMessage();
        }
    }

    @When("user change the amount of the bill to a negative amount")
    public void user_change_the_amount_of_the_bill_to_a_negative_amount() {
        try
        {
            es.editBill(e, -10);
        }
        catch(IllegalArgumentException re)
        {
            error = re.getMessage();
        }
    }

    @Then("the system notifies the user the amount should not be positive")
    public void the_system_notifies_the_user_the_amount_should_not_be_positive() {
        assertEquals(error, "Bill amount cannot be 0!");
        error ="";
    }

}
