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

import javax.swing.*;

public class addBillManually extends SpringIntegrationTest {

    String errorMessage = "";

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private BillMakerService billMakerService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private IndividualUserRepository individualUserRepository;
/*
    @Before
    public void deleteAll() {
        individualUserRepository.deleteAll();
    }*/

    @After
    public void clearDatabase() {
        errorMessage = "";
    }


    @When("user add bill information into the system")
    public void user_add_bill_information_into_the_system() {
        try {
            int id = 1;
            double amount = 85.0;
            String location = "McGill";
            String individualUserName = "YIBO";
            Date date = new Date(2021, 2, 14);
            String description = "test";
            Set<Category> categories = new HashSet<>();
            Category Food = new Category();
            categories.add(Food);

            billMakerService.createIndividualUser(individualUserName, "asd", "asdasd");
            expenseService.createExpense(id, amount, location, individualUserName, date, description, categories);

        } catch (NullPointerException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("user could find this bill under bill section")
    public void user_could_find_this_bill_under_bill_section() {
        try {
            Expense expense = expenseService.getExpenseById(1);
            assertEquals(expense.getIndividualUser().getUsername(),"YIBO");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @When("user add bill information into the system with no location information")
    public void user_add_bill_information_into_the_system_with_no_location_information() {
        try {
            int id = 1;
            double amount = 85.0;
            String location = null;
            String individualUserName = "YIBO";
            Date date = new Date(2021, 2, 14);
            String description = "test";
            Set<Category> categories = new HashSet<>();
            Category Food = new Category();
            categories.add(Food);

            billMakerService.createIndividualUser(individualUserName, "asd", "asdasd");
            expenseService.createExpense(id, amount, location, individualUserName, date, description, categories);

        } catch (NullPointerException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("user add bill information with negative amount")
    public void user_add_bill_information_with_negative_amount() {
        try {
            int id = 1;
            double amount = -85.0;
            String location = "McGill";
            String individualUserName = "YIBO";
            Date date = new Date(2021, 2, 14);
            String description = "test";
            Set<Category> categories = new HashSet<>();
            Category Food = new Category();
            categories.add(Food);
            expenseService.createExpense(id, amount, location, individualUserName, date, description, categories);

        } catch (NullPointerException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("the bill instance cannot be created")
    public void the_bill_instance_cannot_be_created() {
        assertEquals("No such individual user",errorMessage);
    }

}