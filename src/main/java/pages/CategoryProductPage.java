package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;

public class CategoryProductPage extends RootPage {
    
    public  CategoryProductPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    
    @FindBy (xpath = "//button[@id='list-view']")
    private WebElement buttonListView;
    public void clickOnButtonListView(){
        buttonListView.click();
    }

    @FindBy (xpath = "//button[@id='grid-view']")
    private WebElement buttonGridView;
    public void clickOnButtonGridView(){
        buttonGridView.click();
    }

    @FindBy(xpath = "(//button[@data-original-title='Compare this Product'])[1]")
    private WebElement firstCompareButton;
    public void clickOnButtonCompareProduct(){
        firstCompareButton.click();
    }

    @FindBy (xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement warningMessageCompareProduct;
    public String getWarningMessageCompareProduct(){
        String fullText = warningMessageCompareProduct.getText();
        return fullText.substring(0, fullText.indexOf("!") + 1);
    }

    @FindBy (linkText = "product comparison")
    private WebElement buttonProductComparison;
    public ProductComparisonPage clickOnProductComparison(){
        buttonProductComparison.click();
        return new ProductComparisonPage(driver);
    }
    
    
}
