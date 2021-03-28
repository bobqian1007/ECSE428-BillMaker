package com.ecse428.billmaker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.ErrorManager;

import com.ecse428.billmaker.dao.CategoryRepository;
import com.ecse428.billmaker.model.Category;
import com.ecse428.billmaker.service.BillMakerService;
import com.ecse428.billmaker.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EditCategory extends SpringIntegrationTest {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BillMakerService billMakerService;

    @Autowired
    private CategoryRepository categoryRepository;

    private String error = "";
    private Category category;

    @After
    public void clearDatabase() {
        error = "";
        categoryRepository.deleteAll();
    }

    @Given("I am logged in as an individual user")
    public void iAmLoggedInAsAnIndividualUser() {
        try {
            billMakerService.createIndividualUser("Jasper", "jeianw748", "Jasper@gmail.com");
        } catch (Exception e) {
            error = e.getMessage();
        }
    }

    @When("I choose an existing category")
    public void iChooseAnExistingCategory() {
        error = "";
        try {
            Category gifts = new Category();
            gifts.setName("Gifts");
            categoryRepository.save(gifts);
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    @And("edit its name") 
    public void editItsName() {
        try {
            category = categoryService.editCategory("Gifts", "Toiletries");
        } catch (Exception e) {
            error += e.getMessage();
        }
    }
    
    @Then("the category's name should be the new name")
    public void theCategorysNameShouldBeTheNewName() {
        Category Toiletries = new Category();
        Toiletries.setName("Toiletries");
        assertEquals("Toiletries", Toiletries.getName());
        assertEquals("", error);
        
    }

    @When("I choose a existing category")
    public void iChooseAExistingCategory() {
        error = "";
        try {
            Category essentials = new Category();
            essentials.setName("Essentials");
            categoryRepository.save(essentials);
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    @And("I edit its name") 
    public void iEditItsName() {
        try {
            category = categoryService.editCategory("Essentials", "Essentials");
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    @Then("the category's name should be unchanged")
    public void theCategorysNameShouldBeUnchanged() {
        assertEquals("Essentials", category.getName());
        assertEquals("", error);
    }

    @When("I choose an non-existing category") 
    public void iChooseAnNonExistingCategory() {
        error = "";
        try {
            categoryRepository.findCategoryByName("efasss");
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    @And("I try to edit its name")
    public void iTryToEditItsName() {
        try {
            category = categoryService.editCategory("efasss", "Food");
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    @Then("the {string} error message appears")
    public void theErrorMessageAppears(String shownError){
        assertEquals(shownError, error);
    }

    @And("I edit its name to an existing category's name")
    public void iEditItsNameToAnExistingCategorysName() {
        Category foods = new Category();
        foods.setName("Food");
        categoryRepository.save(foods);

        try {
            category = categoryService.editCategory("Gifts", "Food");
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    @Then("the {string} error message will appear") 
    public void theErrorMessageWillAppear (String shownError) {
        assertEquals(shownError, error);
    }

    @When("I choose a category")
    public void iChooseACategory () {
        error = "";
        try {
            Category stationery = new Category();
            stationery.setName("Stationery");
            categoryRepository.save(stationery);
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    @And("I edit its name with an empty string")
    public void iEditItsNameWithAnEmptyString() {
        try {
            category = categoryService.editCategory("Stationery", "");
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    @Then("the {string} error message will be shown")
    public void theErrorMessageWillBeShown(String shownError) {
        assertEquals(shownError, error);
    }
}
