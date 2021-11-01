package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class PizzaAppHomePage {

    public PizzaAppHomePage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "pizza1Pizza")
    public WebElement pizzaDropdown;

    @FindBy(xpath = "//select[@class='toppings1']")
    public WebElement toppings1;

    @FindBy(xpath = "//select[@class='toppings2']")
    public WebElement toppings2;

    @FindBy(id = "pizza1Qty")
    public WebElement quantity;

    @FindBy(id = "name")
    public WebElement name;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "phone")
    public WebElement phone;

    @FindBy(id = "ccpayment")
    public WebElement ccPayment;

    @FindBy(id = "cashpayment")
    public WebElement cashPayment;

    @FindBy(id = "placeOrder")
    public WebElement placeOrderButton;

    @FindBy(tagName = "p")
    public WebElement successMessage;

    @FindBy(id = "pizza1Cost")
    public WebElement cost;

}
