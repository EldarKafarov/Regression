package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HRAppCreateEmployeePage {

    public HRAppCreateEmployeePage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@name='firstName']")
    public WebElement firstName;

    @FindBy(xpath = "//input[@name='lastName']")
    public WebElement lastName;

    @FindBy(id = "department")
    public WebElement departmentDropdown;

    @FindBy(id = "job")
    public WebElement jobTitleDropdown;

    @FindBy(name = "salary")
    public WebElement salaryBox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;


}
