package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class EtsyHomePage {

    public EtsyHomePage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "global-enhancements-search-query")
    public WebElement searchBox;

    @FindBy(id = "catnav-primary-link-10855")
    public WebElement jewerlyAndAccessories;

    @FindBy(id = "catnav-primary-link-891")
    public WebElement homeAndLiving;

    @FindBy(id = "catnav-primary-link-10923")
    public WebElement clothingAndShoes;

    @FindBy(id = "catnav-primary-link-10983")
    public WebElement weddingAndParty;

    @FindBy(id = "catnav-primary-link-11049")
    public WebElement toysAndEntertainment;

    @FindBy(id = "catnav-primary-link-66")
    public WebElement artAndCollectibles;


}
