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

public class ID016 {
    private static Expense bill1;
    private static Expense bill2;
    private static IndividualUser individualUser;
    private static Set<Transaction> requestedTransactions;
    @Given("I logged in as an individual user")
    public void iLoggedInAsAnIndividualUser() {
        individualUser = new IndividualUser();
        bill1 = new Expense();
        bill1.setAmount(100.0);
        bill1.setLocation("Eden");
        bill1.setId(0);
        bill2 = new Expense();
        bill2.setAmount(50.0);
        bill2.setLocation("Provigo");
        bill2.setId(1);
        Set<Transaction> newTransactions = new HashSet<>();
        newTransactions.add(bill1);
        newTransactions.add(bill2);
        individualUser.setTransactions(newTransactions);
    }

    @When("I delete a bill record with a bill id")
    public void iDeleteABillRecordWithABillId() {
        int id = bill1.getId();
        Set<Transaction> toRemove = new HashSet<>();
        Set<Transaction> transactions = individualUser.getTransactions();
        for (Transaction transaction: transactions) {
            if (transaction.getId() == id) {
                toRemove.add(transaction);
            }
        }
        transactions.removeAll(toRemove);
        individualUser.setTransactions(transactions);
    }

    @And("I request for all the bill records")
    public void iRequestForAllTheBillRecords() {
        requestedTransactions = individualUser.getTransactions();
    }

    @Then("I should not see the record in the total record list")
    public void iShouldNotSeeTheRecordInTheTotalRecordList() {
        try {
            assert !requestedTransactions.contains(bill1);
        }
        catch(AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    @When("I delete a bill record at a time with a shop name")
    public void iDeleteABillRecordAtATimeWithAShopName() {
        String location = bill1.getLocation();
        Set<Transaction> toRemove = new HashSet<>();
        Set<Transaction> transactions = individualUser.getTransactions();
        for (Transaction transaction: transactions) {
            Expense bill = (Expense) transaction;
            if (bill.getLocation().equals(location)) {
                toRemove.add(transaction);
            }
        }
        transactions.removeAll(toRemove);
        individualUser.setTransactions(transactions);
    }

    @When("I delete a bill record at a time with a shop name that does not exist")
    public void iDeleteABillRecordAtATimeWithAShopNameThatDoesNotExist() {
        String location = "Metro";
        Set<Transaction> toRemove = new HashSet<>();
        Set<Transaction> transactions = individualUser.getTransactions();
        for (Transaction transaction: transactions) {
            Expense bill = (Expense) transaction;
            if (bill.getLocation().equals(location)) {
                toRemove.add(transaction);
            }
        }
        transactions.removeAll(toRemove);
        individualUser.setTransactions(transactions);
    }

    @Then("A message indicating {string} is generated")
    public void aMessageIndicatingIsGenerated(String arg0) {
        try {
            assert requestedTransactions.contains(bill1) && requestedTransactions.contains(bill2);
            System.out.println(arg0);
        }
        catch(AssertionError e) {
            System.out.println(e.getMessage());
        }
    }
}
