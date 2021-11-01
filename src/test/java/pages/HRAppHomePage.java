package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HRAppHomePage {

    public HRAppHomePage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@href='/employee/-1']")
    public WebElement createNewEmployeeButton;

}
