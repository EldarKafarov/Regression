package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HRAppCreateEmployeePage;
import pages.HRAppHomePage;
import pages.HRAppLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Map;

public class HRAppSteps {

    WebDriver driver = Driver.getDriver();
    HRAppLoginPage hrAppLoginPage=new HRAppLoginPage();
    HRAppHomePage hrAppHomePage = new HRAppHomePage();
    HRAppCreateEmployeePage hrAppCreateEmployeePage = new HRAppCreateEmployeePage();
    int numberOfEmployees;
    Map<String, Object> data;

    @Given("user navigates to HR Application")
    public void user_navigates_to_HR_Application() {
        driver.get(ConfigReader.getProperty("HRAppURL"));
    }

    @When("user logs in with username {string} password {string}")
    public void user_logs_in_with_username_password(String username, String password) {
        hrAppLoginPage.username.sendKeys(username);
        hrAppLoginPage.password.sendKeys(password);
        hrAppLoginPage.loginButton.click();
    }

    @When("user clicks on Create New Employee button")
    public void user_clicks_on_Create_New_Employee_button() throws InterruptedException {
        Thread.sleep(5000);
        numberOfEmployees = driver.findElements(By.xpath("//tbody")).size(); // 135
        hrAppHomePage.createNewEmployeeButton.click();
    }

    @When("user creates employee with data")
    public void user_creates_employee_with_data(DataTable dataTable) {
        // Converting dataTable to map
        data = dataTable.asMap(String.class, Object.class);

        if(data.containsKey("First name"))
            hrAppCreateEmployeePage.firstName.sendKeys(data.get("First name").toString());
        if(data.containsKey("Last name"))
            hrAppCreateEmployeePage.lastName.sendKeys(data.get("Last name").toString());

        BrowserUtils.selectByText(hrAppCreateEmployeePage.departmentDropdown, data.get("Department").toString());
        BrowserUtils.selectByText(hrAppCreateEmployeePage.jobTitleDropdown, data.get("Job title").toString());
        hrAppCreateEmployeePage.salaryBox.clear();
        hrAppCreateEmployeePage.salaryBox.sendKeys(data.get("Salary").toString());
        hrAppCreateEmployeePage.saveButton.click();
    }

    @Then("user validates that employee is in list of employees")
    public void user_validates_that_employee_is_in_list_of_employees() throws InterruptedException {
        Thread.sleep(5000);
        driver.navigate().refresh();
        numberOfEmployees++; // 136
        // //tbody[136]//td
        List<WebElement> newEmployeeRow = driver.findElements(By.xpath("//tbody["+numberOfEmployees+"]//td"));
        // 0 -> employeeid
        // 1 -> first name
        // 2 -> last name
        // 3 -> department
        Assert.assertEquals(data.get("First name").toString(), newEmployeeRow.get(1).getText());
        Assert.assertEquals(data.get("Last name").toString(), newEmployeeRow.get(2).getText());
        Assert.assertEquals(data.get("Department").toString(), newEmployeeRow.get(3).getText());
    }


    @Then("user validates error message in HR App {string}")
    public void userValidatesErrorMessageInHRApp(String errorMessage) {

    }
}
