package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.compress.archivers.cpio.CpioArchiveInputStream;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.PizzaAppHomePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.PipedInputStream;
import java.util.Map;

public class PizzaAppSteps {

    WebDriver driver = Driver.getDriver();
    PizzaAppHomePage pizzaAppHomePage = new PizzaAppHomePage();
    String cost;

    @Given("user navigates to pizza application")
    public void user_navigates_to_pizza_application() {
        driver.get(ConfigReader.getProperty("PizzaAppPath"));
    }

    @When("user creates pizza order with data")
    public void user_creates_pizza_order_with_data(DataTable dataTable) {
        Map<String, Object> data = dataTable.asMap(String.class, Object.class);
        // Using data map print all values in console.
        for(Object object : data.values()){
            System.out.println(object);
        }

        BrowserUtils.selectByValue(pizzaAppHomePage.pizzaDropdown, data.get("Pizza").toString() );
        BrowserUtils.selectByValue(pizzaAppHomePage.toppings1, data.get("Toppings 1").toString());
        BrowserUtils.selectByValue(pizzaAppHomePage.toppings2, data.get("Toppings 2").toString());
        pizzaAppHomePage.quantity.sendKeys(data.get("Quantity").toString());
        pizzaAppHomePage.name.sendKeys(data.get("Name").toString());
        pizzaAppHomePage.email.sendKeys(data.get("Email").toString());
        pizzaAppHomePage.phone.sendKeys(data.get("Phone").toString());
        if(data.get("Payment Type").toString().equalsIgnoreCase("Cash on Pickup")){
            pizzaAppHomePage.cashPayment.click();
        }else if(data.get("Payment Type").toString().equalsIgnoreCase("Credit Card")){
            pizzaAppHomePage.ccPayment.click();
        }
        cost = pizzaAppHomePage.cost.getAttribute("value");
        pizzaAppHomePage.placeOrderButton.click();

    }

    @Then("user validates that order is created with success message {string} for {string} {string}")
    public void userValidatesThatOrderIsCreatedWithSuccessMessageFor(String success, String quantity, String pizza) {

        // Thank you for your order! TOTAL: (cost) Large 10 Slices - 2 toppings
        String expectedSuccessMessage = success + cost +" " + pizza;
        String actualMessage = pizzaAppHomePage.successMessage.getText();
        Assert.assertEquals(expectedSuccessMessage, actualMessage);

    }
}
