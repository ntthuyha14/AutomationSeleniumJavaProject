package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.root.RootPage;

import java.time.Duration;

public class ProductDisplayPage extends RootPage {
    public ProductDisplayPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy (xpath = "//button[@data-original-title = 'Compare this Product']")
    private WebElement buttonCompareProduct;
    public void clickOnButtonCompareProduct(){
        buttonCompareProduct.click();
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

    public ProductComparisonPage clickOnProductRelatedComparison() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.linkText("product comparison"))
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);

        return new ProductComparisonPage(driver);
    }

    @FindBy (xpath = "(//button[@data-original-title = 'Compare this Product'])[2]")
    private WebElement buttonCompareRelatedProduct;
    public void clickOnButtonCompareRelatedProduct(){
        buttonCompareRelatedProduct.click();
    }
    
    @FindBy (linkText = "iMac")
    private WebElement productName;
    public ProductPage clickOnProductNameFromProductSearchDetailPage(){
            productName.click();
            return new ProductPage(driver);
    }

    @FindBy (xpath = "//a[@class='thumbnail']//img")
    private WebElement imgProduct;
    public void clickOnImgProduct(){
        imgProduct.click();
    }

    @FindBy (xpath = "//button[@class='mfp-arrow mfp-arrow-left mfp-prevent-close']")
    private WebElement buttonLeftArrow;
    public Boolean isButtonLeftArrowDisplay(){
        return buttonLeftArrow.isDisplayed();
    }

    public void clickOnButtonLeftArrowImg(){
        buttonLeftArrow.click();
    }

    @FindBy (xpath = "//div[@class='mfp-counter']")
    private WebElement counterImg;
    public String getCounterImg(){
        return counterImg.getText();
    }

    @FindBy (xpath = "//button[@class='mfp-arrow mfp-arrow-right mfp-prevent-close']")
    private WebElement buttonRightArrow;
    public Boolean isButtonRightArrowDisplay(){
        return buttonRightArrow.isDisplayed();
    }

    public void clickOnButtonRightArrowImg(){
        buttonRightArrow.click();
    }

    @FindBy (xpath = "//button[@class='mfp-close']")
    private WebElement buttonCloseImage;
    public void clickOnButtonCloseThumbNail(){
        buttonCloseImage.click();
    }



    @FindBy (xpath = "//div[@id='content']//h1")
    private WebElement productNameFromSearchPage;
    public String getProductName(){
        return productNameFromSearchPage.getText();
    }

    @FindBy (xpath = "//div[@id='content']//ul[@class='list-unstyled'][1]/li[1]/a")
    private WebElement brandNameProduct;
    public String getBrandNameProduct(){
        return brandNameProduct.getText();
    }

    @FindBy (xpath = "//div[@id='content']//ul[@class='list-unstyled'][1]/li[2]")
    private WebElement productCode;
    public String getProductCode(){
        return productCode.getText();
    }

    @FindBy (xpath = "//div[@id='content']//ul[@class='list-unstyled'][1]/li[3]")
    private WebElement productStatus;
    public String getStatusProduct(){
        return productStatus.getText();
    }

    @FindBy (xpath = "//div[@id='content']//ul[@class='list-unstyled'][2]//h2")
    private WebElement priceProduct;
    public String getPriceProduct(){
        return priceProduct.getText();
    }

    @FindBy (xpath = "//div[@id='content']//ul[@class='list-unstyled'][2]//li[2]")
    private WebElement priceExTax;
    public String getPriceExTax(){
        return priceExTax.getText();
    }

    @FindBy (xpath = "//input[@id='input-quantity']")
    private WebElement inputQuantityProduct;
    public String  getInputQuantityProduct(){
        return inputQuantityProduct.getAttribute("value");
    }

    public void inputQuantityProduct(String quantity){
        inputQuantityProduct.clear();
        inputQuantityProduct.sendKeys(quantity);
    }

    @FindBy (xpath = "//button[@id='button-cart']")
    private WebElement buttonCart;
    public void clickOnButtonAddToCart(){
        buttonCart.click();
    }

    public Boolean isButtonAddToCartDisplay(){
        return buttonCart.isDisplayed();
    }

    @FindBy (xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement warningMessageProduct;
    public String getMessageProductSuccessful(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String fullText = warningMessageProduct.getText();
        return fullText.substring(0, fullText.indexOf("!") + 1);
    }

    public Boolean isMessageWarningDisplay(){
        return warningMessageProduct.isDisplayed();
    }

    @FindBy (xpath = "//ul[@class='nav nav-tabs']//li[3]//a")
    private WebElement reviewProduct;
    public void clickOnButtonReview(){
        reviewProduct.click();
    }

    @FindBy (xpath = "//input[@id='input-name']")
    private WebElement customerNameReview;
    public void enterCustomerNameReviewProduct(String nameCustomer){
        customerNameReview.sendKeys(nameCustomer);
    }

    @FindBy (xpath = "//textarea[@id='input-review']")
    private WebElement contentReview;
    public void enterContentReviewProduct(String contentReviewProduct){
        contentReview.sendKeys(contentReviewProduct);
    }

    @FindBy (xpath = "//button[@id='button-review']")
    private WebElement buttonReview;
    public void clickOnButtonSendReview(){
        buttonReview.click();
    }

    @FindBy (xpath = "//input[@name='rating' and @value='4']")
    private WebElement ratingReview;
    public void selectRatingReviewProduct(){
        ratingReview.click();
    }

    public String getMessageReviewsuccessful(){
       return warningMessageCompareProduct.getText();
    }







}
