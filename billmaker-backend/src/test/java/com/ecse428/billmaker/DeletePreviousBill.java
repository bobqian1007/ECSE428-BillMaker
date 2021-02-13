package com.ecse428.billmaker;

import com.ecse428.billmaker.dao.ExpenseRepository;
import com.ecse428.billmaker.dao.IndividualUserRepository;
import com.ecse428.billmaker.model.Category;
import com.ecse428.billmaker.model.Expense;
import com.ecse428.billmaker.service.BillMakerService;
import com.ecse428.billmaker.service.ExpenseService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeletePreviousBill extends SpringIntegrationTest{

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private BillMakerService billMakerService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private IndividualUserRepository individualUserRepository;

    @Deprecated
    private Date date = new Date(121, 1, 12);
    private String errorMessage = "";
    private List<Expense> expenses;

    @Before
    public void setup() {
        billMakerService.createIndividualUser("Alex", "abc", "Alex@gmail.com");
        Set<Category> categories = new HashSet<>();
        Category Food = new Category();
        categories.add(Food);
        try {
            expenseService.createExpense(0, 50.0, "Provigo", "Alex", date, "for lunch", categories);
            expenseService.createExpense(1, 100.0, "Metro", "Alex", date, "for dinner", categories);
        } catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @After
    public void clearDatabase() {
        errorMessage = "";
        individualUserRepository.deleteAll();
        expenseRepository.deleteAll();
    }

    @Given("I logged in as an individual user")
    public void iLoggedInAsAnIndividualUser() {
        // this should be implemented after login function is done
    }

    @When("I delete a bill record with a bill id")
    public void iDeleteABillRecordWithABillId() {
        try {
            assertTrue(expenseService.delete(0));
        }
        catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @And("I request for all the bill records")
    public void iRequestForAllTheBillRecords() {
        expenses = expenseService.getAllExpenses();
    }

    @Then("I should not see the record in the total record list")
    public void iShouldNotSeeTheRecordInTheTotalRecordList() {
        try {
            assert !expenses.contains(expenseService.getExpenseById(0));
        }
        catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @When("I delete a bill record at a time with a shop name")
    public void iDeleteABillRecordAtATimeWithAShopName() {
        try {
            assertTrue(expenseService.delete(expenseService.getExpenseByLocation("Provigo").getId()));
        }
        catch(NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @When("I delete a bill record at a time with a shop name that does not exist")
    public void iDeleteABillRecordAtATimeWithAShopNameThatDoesNotExist() {
        try {
            expenseService.delete(expenseService.getExpenseByLocation("IGA").getId());
        }
        catch(NullPointerException e) {
            errorMessage = errorMessage + "Record not exists";
        }
    }

    @Then("A message indicating {string} is generated")
    public void aMessageIndicatingIsGenerated(String error) {
        assertEquals(error, errorMessage);
    }
}
