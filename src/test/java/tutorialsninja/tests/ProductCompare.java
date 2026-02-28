package tutorialsninja.tests;

import Utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import tutorialsninja.base.Base;

import java.util.Properties;

public class ProductCompare extends Base {
    WebDriver driver;
    LandingPage landingPage;
    Properties prop;
    ProductComparisonPage productComparisonPage;
    SearchPage searchPage;
    ProductDisplayPage productDisplayPage;
    CategoryProductPage categoryProductPage;
    
    @BeforeMethod
    public void setup(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);
    }

//    @AfterMethod
//    public void tearDown(){
//        closeBrowser(driver);
//    }
    
    @Test (priority = 1)
    public void verifyAddProductComparisionFromProductDisplayPage(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        productDisplayPage = searchPage.clickOnImageProduct();
        productDisplayPage.clickOnButtonCompareProduct();
        String expectedWarningCompareProduct = "Success: You have added iMac to your product comparison!";
        Assert.assertEquals(productDisplayPage.getWarningMessageCompareProduct(), expectedWarningCompareProduct);
        System.out.println(productDisplayPage.getWarningMessageCompareProduct());
        productComparisonPage = productDisplayPage.clickOnProductComparison();
        String productDetail = "Just when you thought iMac had everything, now there´s even more. More powerful Intel Core 2 Duo processors. And more memory standard. Combine this with Mac OS X Leopard and iLife ´08, and it´s mor..";
        String productPrice = "$122.00";
        Assert.assertEquals(productComparisonPage.getPriceProduct(), productPrice);
        Assert.assertEquals(productComparisonPage.getProductDetail(), productDetail);
        Assert.assertEquals(productComparisonPage.getProductNameOnComparePage(), prop.getProperty("existingProductInSubCategory"));
    }
    
    @Test (priority = 2)
    public void verifyAddProductComparisionFromListViewOfSearchPage(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        String expectedWarningCompareProduct = "Success: You have added iMac to your product comparison!";
        Assert.assertEquals(searchPage.getMessageProductSuccessful(), expectedWarningCompareProduct);
        productComparisonPage = searchPage.clickOnProductComparison();
        String productDetail = "Just when you thought iMac had everything, now there´s even more. More powerful Intel Core 2 Duo processors. And more memory standard. Combine this with Mac OS X Leopard and iLife ´08, and it´s mor..";
        String productPrice = "$122.00";
        Assert.assertEquals(productComparisonPage.getPriceProduct(), productPrice);
        Assert.assertEquals(productComparisonPage.getProductDetail(), productDetail);
        Assert.assertEquals(productComparisonPage.getProductNameOnComparePage(), prop.getProperty("existingProductInSubCategory"));
    }

    @Test (priority = 3)
    public void verifyAddProductComparisionFromGridViewOfSearchPage(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnGridViewOption();
        searchPage.clickOnButtonCompareProduct();
        String expectedWarningCompareProduct = "Success: You have added iMac to your product comparison!";
        Assert.assertEquals(searchPage.getMessageProductSuccessful(), expectedWarningCompareProduct);
        productComparisonPage = searchPage.clickOnProductComparison();
        String productDetail = "Just when you thought iMac had everything, now there´s even more. More powerful Intel Core 2 Duo processors. And more memory standard. Combine this with Mac OS X Leopard and iLife ´08, and it´s mor..";
        String productPrice = "$122.00";
        Assert.assertEquals(productComparisonPage.getPriceProduct(), productPrice);
        Assert.assertEquals(productComparisonPage.getProductDetail(), productDetail);
        Assert.assertEquals(productComparisonPage.getProductNameOnComparePage(), prop.getProperty("existingProductInSubCategory"));
    }
    
    @Test (priority = 4)
    public void verifyAddProductComparisionFromListViewOfProductCategoryOrSubCategory(){
        landingPage.clickOnDesktopsOption();
        categoryProductPage = landingPage.clickOnShowAllDesktopsOption();
        categoryProductPage.clickOnButtonListView();
        categoryProductPage.clickOnButtonCompareProduct();
        String expectedWarningCompareProduct = "Success: You have added Apple Cinema 30\" to your product comparison!";
        Assert.assertEquals(categoryProductPage.getWarningMessageCompareProduct(), expectedWarningCompareProduct);
        productComparisonPage = categoryProductPage.clickOnProductComparison();
        String productName = "Apple Cinema 30\"";
        Assert.assertEquals(productComparisonPage.getProductNameOnComparePage(), productName);
        String productPrice = "$122.00$110.00";
        Assert.assertEquals(productComparisonPage.getPriceProduct(), productPrice);
    }

    @Test (priority = 5)
    public void verifyAddProductComparisionFromGridViewOfProductCategoryOrSubCategory(){
        landingPage.clickOnDesktopsOption();
        categoryProductPage = landingPage.clickOnShowAllDesktopsOption();
        categoryProductPage.clickOnButtonGridView();
        categoryProductPage.clickOnButtonCompareProduct();
        String expectedWarningCompareProduct = "Success: You have added Apple Cinema 30\" to your product comparison!";
        Assert.assertEquals(categoryProductPage.getWarningMessageCompareProduct(), expectedWarningCompareProduct);
        productComparisonPage = categoryProductPage.clickOnProductComparison();
        String productName = "Apple Cinema 30\"";
        Assert.assertEquals(productComparisonPage.getProductNameOnComparePage(), productName);
        String productPrice = "$122.00$110.00";
        Assert.assertEquals(productComparisonPage.getPriceProduct(), productPrice);
    }
    
}
