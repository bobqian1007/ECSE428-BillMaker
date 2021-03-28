package com.ecse428.billmaker;

import com.ecse428.billmaker.model.Category;
import com.ecse428.billmaker.model.IndividualUser;
import com.ecse428.billmaker.service.BillMakerService;
import com.ecse428.billmaker.service.CategoryService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IndividualUserAddsNewCategory extends SpringIntegrationTest{

    @Autowired
    private BillMakerService billMakerService;

    @Autowired
    private CategoryService categoryService;

    private IndividualUser individualUser = new IndividualUser();
    private Category category = new Category();
    private List<Category> categories;
    private String errorMessage = "";

    @Given("I am sign in an individual user")
    public void iAmAnIndividualUser() {
        individualUser = billMakerService.createIndividualUser("Alex","abc","Alex@gmail.com");
    }

    @When("I add a new category with a name in string")
    public void iAddANewCategoryWithANameInString() {
        try {
            category = categoryService.createCategory("Food");
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("I should see the newly added category in the list of all categories")
    public void iShouldSeeTheNewlyAddedCategoryInTheListOfAllCategories() {
        try {
            categories = categoryService.getAllCategories();
            assertEquals(categories.get(0).getName(),category.getName());
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("I add a new category with a name in number")
    public void iAddANewCategoryWithANameInNumber() {
        try {
            category = categoryService.createCategory(String.valueOf(53231323));
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @When("I add a category without a name")
    public void iAddACategoryWithoutAName() {
        try {
            category = categoryService.createCategory("");
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }

    }

    @Then("I should not be able to create this category")
    public void iShouldNotBeAbleToCreateThisCategory() {
        assertEquals(errorMessage, "Name cannot be empty!");
    }
}
