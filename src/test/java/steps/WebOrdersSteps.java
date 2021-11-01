package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.WebOrdersHomePage;
import pages.WebOrdersLoginPage;
import pages.WebOrdersOrderPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Map;

public class WebOrdersSteps {

    WebDriver driver = Driver.getDriver();
    WebOrdersLoginPage webOrdersLoginPage = new WebOrdersLoginPage();
    WebOrdersHomePage webOrdersHomePage = new WebOrdersHomePage();
    WebOrdersOrderPage webOrdersOrderPage = new WebOrdersOrderPage();
    int numberOfRows;
    List<Map<String, Object>> data;

    @Given("user navigates to weborders application")
    public void user_navigates_to_weborders_application() {
        driver.get(ConfigReader.getProperty("WebOrdersURL"));
    }

    @When("user provides username {string} and password {string} and clicks on login")
    public void user_provides_username_and_password_and_clicks_on_login(String username, String password) {
        webOrdersLoginPage.username.sendKeys(username);
        webOrdersLoginPage.password.sendKeys(password);
        webOrdersLoginPage.loginButton.click();
    }

    @Then("user validates application is logged in")
    public void user_validates_application_is_logged_in() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Web Orders";
        Assert.assertEquals(expectedTitle,actualTitle);
        driver.quit();
    }

    @Then("user validates error message {string}")
    public void user_validates_error_message(String errorMessage) {
        String actualErrorMessage = webOrdersLoginPage.errorMessage.getText();
        Assert.assertEquals(errorMessage, actualErrorMessage);
        driver.quit();
    }

    @And("user clicks on Order module")
    public void userClicksOnOrderModule() {
        webOrdersHomePage.ordersModule.click();
    }

    @And("user selects {string} product with {int} quantity")
    public void userSelectsProductWithQuantity(String product, int quantity) throws InterruptedException {
        // product = "MyMoney"
        // quantity = 1
        BrowserUtils.selectByValue(webOrdersOrderPage.productDropdown,product);
        webOrdersOrderPage.quantityBox.sendKeys(Keys.BACK_SPACE);
        webOrdersOrderPage.quantityBox.sendKeys(quantity+""+Keys.ENTER);
    }

    @Then("user validates total is calculated correctly for quantity {int}")
    public void userValidatesTotalIsCalculatedCorrectlyForQuantity(int quantity) {
        String pricePerUnit = webOrdersOrderPage.pricePerUnit.getAttribute("value");
        System.out.println("Price per unit is "+pricePerUnit);

        String discountAmount = webOrdersOrderPage.discountBox.getAttribute("value");
        int discountAmountInt = Integer.parseInt(discountAmount);

        int expectedTotal=0;
        if(discountAmountInt==0){
            expectedTotal = quantity * Integer.parseInt(pricePerUnit);
        }else{
            expectedTotal = quantity * Integer.parseInt(pricePerUnit);
            // 1000 -> 8% ---> 1000 - 1000*8/100
            expectedTotal = expectedTotal - expectedTotal*discountAmountInt/100;
        }

        String actualTotalStr = webOrdersOrderPage.total.getAttribute("value");
        int actualTotal = Integer.parseInt(actualTotalStr);
        Assert.assertEquals(expectedTotal,actualTotal);
    }

    @When("user creates order with data")
    public void user_creates_order_with_data(DataTable dataTable) {
        // Convert dataTable to list of maps
        data = dataTable.asMaps(String.class, Object.class);
//        System.out.println(data.get(0).get("name")); // John Doe
//        System.out.println(data.get(2).get("quantity")); // 1
//        System.out.println(data.get(3).get("expire date")); // 12/21
//        System.out.println(data.get(1).get("order")); // MyMoney
        BrowserUtils.selectByValue(webOrdersOrderPage.productDropdown, data.get(0).get("order").toString());
        webOrdersOrderPage.quantityBox.sendKeys(Keys.BACK_SPACE);
        webOrdersOrderPage.quantityBox.sendKeys(data.get(0).get("quantity").toString());
        webOrdersOrderPage.name.sendKeys(data.get(0).get("name").toString());
        webOrdersOrderPage.street.sendKeys(data.get(0).get("address").toString());
        webOrdersOrderPage.city.sendKeys(data.get(0).get("city").toString());
        webOrdersOrderPage.state.sendKeys(data.get(0).get("state").toString());
        webOrdersOrderPage.zip.sendKeys(data.get(0).get("zip").toString());
        webOrdersOrderPage.visaCheckbox.click();
        webOrdersOrderPage.cardNumber.sendKeys(data.get(0).get("cc").toString());
        webOrdersOrderPage.expireDate.sendKeys(data.get(0).get("expire date").toString());
        webOrdersOrderPage.processButton.click();
    }


    @Then("user validates success message {string}")
    public void userValidatesSuccessMessage(String expectedMessage) {
        String actualSuccessMessage = webOrdersOrderPage.successMessage.getText();
        Assert.assertEquals(expectedMessage, actualSuccessMessage);
    }

    @And("user validates order added to List Of Orders")
    public void userValidatesOrderAddedToListOfOrders() {
        webOrdersHomePage.viewAllOrdersModule.click();
        int numberOfRowsAfterOrderCreation = webOrdersHomePage.numberOfRows.size();
        Assert.assertEquals(numberOfRowsAfterOrderCreation-numberOfRows, 1);
        String actualName = webOrdersHomePage.firstRowName.getText();
        Assert.assertEquals(data.get(0).get("name").toString(),actualName);
        // Do the validation for the rest of the data.
    }


    @And("user counts number of orders in table")
    public void userCountsNumberOfOrdersInTable() {
        numberOfRows = webOrdersHomePage.numberOfRows.size();
    }
}











