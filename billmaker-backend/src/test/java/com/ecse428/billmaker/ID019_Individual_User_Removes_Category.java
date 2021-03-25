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

public class ID019_Individual_User_Removes_Category extends SpringIntegrationTest{

    @Autowired
    private BillMakerService billMakerService;

    @Autowired
    private CategoryService categoryService;

    private IndividualUser individualUser = new IndividualUser();
    private Category category = new Category();
    private List<Category> categories;
    private String errorMessage = "";

    @Given("I am an individual user")
    public void iAmAnIndividualUser() {
        individualUser = billMakerService.createIndividualUser("Alex","abc","Alex@gmail.com");
    }

    @When("I remove a category by specifying a name in string")
    public void iRemoveCategoryBySpecifyingANameInString() {
        try {
            categoryService.removeCategory("Sport");
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("I should see the specified category removed from the list of all categories")
    public void iShouldSeeTheSpecifiedCategoryRemovedFromTheListOfAllCategories() {
        try {
            categories = categoryService.getAllCategories();
            assertEquals(categories.contains(categoryService.getCategoriesByName("Sport")), Boolean.FALSE);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }


    @When("I remove a category without specifying a name")
    public void iRemoveACategoryWithoutAName() {
        try {
            categoryService.removeCategory("");
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }

    }

    @Then("I should not be able to remove this category")
    public void iShouldNotBeAbleToRemoveThisCategory() {
        assertEquals(errorMessage, "Name cannot be empty!");
    }
}