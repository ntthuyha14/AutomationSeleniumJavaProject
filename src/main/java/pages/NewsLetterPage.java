package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;

public class NewsLetterPage extends RootPage {
    public NewsLetterPage(WebDriver driver){
        super(driver);
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
