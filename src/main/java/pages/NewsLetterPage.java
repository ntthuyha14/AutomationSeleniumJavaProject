package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v136.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewsLetterPage {
    WebDriver driver;
    public NewsLetterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy (xpath= "//ul[@class='breadcrumb']//a[text()='Newsletter']")
    private WebElement newsletterBreadcrumb;
    public boolean didWeNavigateToNewsletterPage(){
        return newsletterBreadcrumb.isDisplayed();
    }

    @FindBy (xpath= "//input[@name='newsletter'][@value='1']")
    private WebElement yesNewsletterOption;
    public boolean isYesNewsletterOptionSelected(){
        return yesNewsletterOption.isSelected();
    }

    @FindBy (xpath= "//input[@name='newsletter'][@value='0']")
    private WebElement noNewsletterOption;
    public boolean isNoNewsletterOptionSelected(){
        return noNewsletterOption.isSelected();
    }


}
