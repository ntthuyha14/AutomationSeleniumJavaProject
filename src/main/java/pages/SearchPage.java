package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.root.RootPage;

import java.time.Duration;
import java.util.List;

public class SearchPage extends RootPage {

    public SearchPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//ul[@class='breadcrumb']//a[text()='Search']")
    private WebElement searchBreadcrumb;
    public boolean didWeNavigateToSearchPage(){
        return searchBreadcrumb.isDisplayed();
    }
    
    @FindBy (linkText = "HP LP3065")
    private WebElement existingProduct;
    public boolean verifyExistingProductDisplay(){
        return existingProduct.isDisplayed();
    }
    
    @FindBy (xpath = "//input[@id='button-search']/following-sibling::p")
    private WebElement warningNonProduct;
    public String getWarningNonProduct(){
        return warningNonProduct.getText();
    }

    @FindBy(xpath = "//div[@class='product-thumb']")
    private List<WebElement> numberOfProduct;
    public int getNumbersProductInSearchResult(){
        return numberOfProduct.size();
    }
    
    
    
    @FindBy (xpath = "//input[@id='input-search']")
    private WebElement inputSearchCriteria;
    public String getPlaceHolderSearchCriteria(){
        return inputSearchCriteria.getDomProperty("placeholder");
    }

    public void  enterProductNameInSearchCriteria(String productName){
        inputSearchCriteria.sendKeys(productName);
    }
    
    @FindBy (xpath = "//input[@id='button-search']")
    private WebElement buttonSearchCriteria;
    public void clickButtonSearchCriteria(){
        buttonSearchCriteria.click();
    }

    @FindBy (xpath = "//input[@id='description']")
    private WebElement checkBoxDescription;
    public void selectCheckBoxDescription(){
        checkBoxDescription.click();
    }
    
    @FindBy (linkText = "iMac")
    private WebElement existingByDescriptionProduct;
    public boolean isProductInCategoryDisplayedInSearchResults(){
        return existingByDescriptionProduct.isDisplayed();
    }

    @FindBy(xpath = "//select[@name='category_id']")
    private WebElement categoryDropdownField;
    public void selectOptionFromCategoryDropdownFieldUsingIndex(int indexNumber) {
        Select select = new Select(categoryDropdownField);
        select.selectByIndex(indexNumber);
    }
    
    @FindBy (xpath = "//input[@name='sub_category']")
    private WebElement subCategory;
    public void selectSubCategory(){
        subCategory.click();
    }
    
    @FindBy (xpath = "//button[@id='list-view']")
    private WebElement listViewOption;
    public void clickOnListViewOption(){
        listViewOption.isSelected();
    }
    
    @FindBy (xpath = "//span[text()='Add to Cart']")
    private WebElement addToCartOption;
    public void clickOnAddToCartOption(){
        addToCartOption.click();
    }
    
    @FindBy (xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement warningMessageProduct;
    public String getMessageProductSuccessful(){
        String fullText = warningMessageProduct.getText();
        return fullText.substring(0, fullText.indexOf("!") + 1);
    }

    @FindBy (xpath = "//button[@data-original-title='Add to Wish List']")
    private WebElement buttonAddWishList;
    public void clickOnButtonAddWishList(){
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", buttonAddWishList);
    }

    @FindBy (xpath = "//button[@data-original-title='Compare this Product']")
    private WebElement buttonCompareProduct;
    public void clickOnButtonCompareProduct(){
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", buttonCompareProduct);
    }
    
    @FindBy (xpath = "//img[@class='img-responsive']")
    private WebElement imgProduct;
    public ProductDisplayPage clickOnImageProduct(){
        imgProduct.click();
        return new ProductDisplayPage(driver);
    }
    
    @FindBy (xpath = "//ul[@class='breadcrumb']//a[text()='iMac']")
    private WebElement breadcrumnProduct;
    public boolean didWeNavigativeToProductDetail(){
        return breadcrumnProduct.isDisplayed();
    }
 
    @FindBy (xpath = "//div[@class='caption']//a[text()='iMac']")
    private WebElement productName;
    public ProductDisplayPage clickNameProduct(){
        productName.click();
        return new ProductDisplayPage(driver);
    }

    @FindBy (xpath = "//button[@id='grid-view']")
    private WebElement gridViewOption;
    public void clickOnGridViewOption(){
        gridViewOption.isSelected();
    }

  
    @FindBy (id = "compare-total")
    private WebElement productCompareLink;
    public ProductComparisonPage selectProductCompareLink(){
        productCompareLink.click();
        return new ProductComparisonPage(driver);
    }
    
    @FindBy (id = "input-sort")
    private WebElement inputSort;
    public void selectSortOptionInResultSearch(int indexNumber){
        Select select = new Select(inputSort);
        select.selectByIndex(indexNumber);
    }
    
    @FindBy (xpath = "(//div[@class='caption']//h4//a)[1]")
    private WebElement firstProductInSearchResults;
    public String getNameFirstProductInSearchResults(){
        return firstProductInSearchResults.getText();
    }

    @FindBy (xpath = "(//div[@class='caption']//h4//a)[2]")
    private WebElement secondProductInSearchResults;
    public String getNameSecondProductInSearchResults(){
        return secondProductInSearchResults.getText();
    }

    @FindBy (xpath = "(//div[@class='caption']//h4//a)[3]")
    private WebElement thirdProductInSearchResults;
    public String getNameThirdProductInSearchResults(){
        return thirdProductInSearchResults.getText();
    }

    @FindBy (xpath = "(//div[@class='caption']//h4//a)[4]")
    private WebElement fourthProductInSearchResults;
    public String getNameFourthProductInSearchResults(){
        return fourthProductInSearchResults.getText();
    }
    
    @FindBy (id ="input-limit")
    private WebElement showProduct;

    public void selectValueShowInResultSearch(int indexNumber){
        Select select = new Select(showProduct);
        select.selectByIndex(indexNumber);
    }

    public String getValueCurrentShowOptionProduct() {
        Select select = new Select(showProduct);
        String selectedText = select.getFirstSelectedOption().getText();
        return selectedText.trim();
    }
    
    
    
    
   

}
