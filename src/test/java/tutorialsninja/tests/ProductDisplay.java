package tutorialsninja.tests;

import Utils.CommonUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.ProductDisplayPage;
import pages.SearchPage;
import tutorialsninja.base.Base;

import java.time.Duration;
import java.util.Properties;

public class ProductDisplay extends Base {
    WebDriver driver;
    Properties prop;
    LandingPage landingPage;
    SearchPage searchPage;
    ProductDisplayPage productDisplayPage;

    @BeforeMethod
    public void setup(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);
    }


    @AfterMethod
    public void tearDown() {
        closeBrowser(driver);
    }

    @Test(priority = 1)
    public void verifyDisplayThumbNailOnProductDisplayPage(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        productDisplayPage = searchPage.clickOnImageProduct();
        productDisplayPage.clickOnImgProduct();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        CommonUtils.takeScreenshot(driver, "\\Screenshots\\actualImgProductFullScreen.png");
        Assert.assertFalse(CommonUtils.compareTwoScreenshots(
                System.getProperty("user.dir") +"\\Screenshots\\actualImgProductFullScreen.png",
                System.getProperty("user.dir") +"\\Screenshots\\expectedImgProductFullScreen.png" ));
        Assert.assertTrue(productDisplayPage.isButtonLeftArrowDisplay());
        Assert.assertTrue(productDisplayPage.isButtonRightArrowDisplay());
        productDisplayPage.clickOnButtonRightArrowImg();
        System.out.println(productDisplayPage.getCounterImg());
        Assert.assertEquals(productDisplayPage.getCounterImg(), "2 of 3");
        productDisplayPage.clickOnButtonLeftArrowImg();
        productDisplayPage.clickOnButtonLeftArrowImg();
        Assert.assertEquals(productDisplayPage.getCounterImg(), "3 of 3");
        productDisplayPage.clickOnButtonCloseThumbNail();
        CommonUtils.takeScreenshot(driver, "\\Screenshots\\actualImgCloseThumbNail.png");
        Assert.assertFalse(CommonUtils.compareTwoScreenshots(
                System.getProperty("user.dir") +"\\Screenshots\\actualImgCloseThumbNail.png",
                System.getProperty("user.dir") +"\\Screenshots\\expectedImgCloseThumbNail.png" ));

    }

    @Test (priority = 2)
    public void verifyNameBrandAndCodeProduct(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        productDisplayPage = searchPage.clickOnImageProduct();
        Assert.assertEquals(productDisplayPage.getProductName(), "iMac");
        Assert.assertEquals(productDisplayPage.getBrandNameProduct(), "Apple");
        Assert.assertEquals(productDisplayPage.getProductCode(), "Product Code:Product 14");
    }

    @Test (priority = 3)
    public void verifyStatusProduct(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        productDisplayPage = searchPage.clickOnImageProduct();
        Assert.assertEquals(productDisplayPage.getStatusProduct(), "Availability:Out Of Stock");
    }

    @Test (priority = 4)
    public void verifyPriceAndExtaxPriceProduct(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        productDisplayPage = searchPage.clickOnImageProduct();
        Assert.assertEquals(productDisplayPage.getPriceProduct(), "$122.00");
        Assert.assertEquals(productDisplayPage.getPriceExTax(), "Ex Tax:$100.00");
    }

    @Test (priority = 5)
    public void verifyQuantityProduct(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        productDisplayPage = searchPage.clickOnImageProduct();
        Assert.assertEquals(productDisplayPage.getInputQuantityProduct(), "1");
        productDisplayPage.inputQuantityProduct("2");
        Assert.assertEquals(productDisplayPage.getInputQuantityProduct(), "2");
        Assert.assertTrue(productDisplayPage.isButtonAddToCartDisplay());
        productDisplayPage.clickOnButtonAddToCart();
        System.out.println(productDisplayPage.getMessageProductSuccessful());
        Assert.assertEquals(productDisplayPage.getMessageProductSuccessful(), "Success: You have added iMac to your shopping cart!");
    }

    @Test (priority = 6)
    public void verifyAddNullQuantityProductToCart(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        productDisplayPage = searchPage.clickOnImageProduct();
        productDisplayPage.inputQuantityProduct("0");
        productDisplayPage.clickOnButtonAddToCart();
        Assert.assertFalse(productDisplayPage.isMessageWarningDisplay());
    }

    @Test (priority = 7)
    public void verifyAddReviewProduct(){
        landingPage.enterProductNameInSearch("Apple Cinema 30\"");
        searchPage = landingPage.clickOnSearchIconOption();
        productDisplayPage = searchPage.clickOnImageProduct();
        productDisplayPage.clickOnButtonReview();
        productDisplayPage.enterCustomerNameReviewProduct("Anna");
        String contentReview = "This product is very good and easy to use. The design looks nice and the quality feels solid. It works well and matches the description on the website. I am satisfied with my purchase and would recommend it to others. Great value for the price.";
        productDisplayPage.enterContentReviewProduct(contentReview);
        productDisplayPage.selectRatingReviewProduct();
        productDisplayPage.clickOnButtonSendReview();
        System.out.println(productDisplayPage.getMessageReviewsuccessful());
        Assert.assertEquals(productDisplayPage.getMessageReviewsuccessful(), "Thank you for your review. It has been submitted to the webmaster for approval.");
    }



}
