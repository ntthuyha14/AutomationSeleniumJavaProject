package tutorialsninja.tests;

import Utils.CommonUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import tutorialsninja.base.Base;

import java.time.Duration;
import java.util.Properties;

public class ProductCompare extends Base {
    WebDriver driver;
    LandingPage landingPage;
    Properties prop;
    ProductComparisonPage productComparisonPage;
    SearchPage searchPage;
    ProductDisplayPage productDisplayPage;
    CategoryProductPage categoryProductPage;
    ProductPage productPage;

    @BeforeMethod
    public void setup() {
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);
    }

    //    @AfterMethod
//    public void tearDown(){
//        closeBrowser(driver);
//    }
//
    @Test(priority = 1)
    public void verifyAddProductComparisionFromProductDisplayPage() {
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

    @Test(priority = 2)
    public void verifyAddProductComparisionFromListViewOfSearchPage() {
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

    @Test(priority = 3)
    public void verifyAddProductComparisionFromGridViewOfSearchPage() {
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

    @Test(priority = 4)
    public void verifyAddProductComparisionFromListViewOfProductCategoryOrSubCategory() {
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

    @Test(priority = 5)
    public void verifyAddProductComparisionFromGridViewOfProductCategoryOrSubCategory() {
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

    @Test(priority = 6)
    public void verifyAddProductComparisionFromRelatedProduct() {
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        productDisplayPage = searchPage.clickOnImageProduct();
        productDisplayPage.clickOnButtonCompareRelatedProduct();
        String expectedWarningCompareProduct = "Success: You have added Apple Cinema 30\" to your product comparison!";
        Assert.assertEquals(productDisplayPage.getWarningMessageCompareProduct(), expectedWarningCompareProduct);
        productComparisonPage = productDisplayPage.clickOnProductRelatedComparison();
        String productName = "Apple Cinema 30\"";
        Assert.assertEquals(productComparisonPage.getProductNameOnComparePage(), productName);
        String productPrice = "$122.00$110.00";
        Assert.assertEquals(productComparisonPage.getPriceProduct(), productPrice);
    }

    @Test(priority = 7)
    public void verifyAddProductComparisionFromLandingPage() {
        landingPage.clickOnButtonCompareProductLandingPage();
        String expectedWarningCompareProduct = "Success: You have added MacBook to your product comparison!";
        Assert.assertEquals(landingPage.getWarningMessageCompareProduct(), expectedWarningCompareProduct);
        productComparisonPage = landingPage.clickOnProductComparison();
        String productName = "MacBook";
        Assert.assertEquals(productComparisonPage.getProductNameOnComparePage(), productName);
        String productPrice = "$602.00";
        Assert.assertEquals(productComparisonPage.getPriceProduct(), productPrice);
    }

    @Test(priority = 8)
    public void verifyNavigatingProductComparePageFromSearchResultPage() {
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        productComparisonPage = searchPage.selectProductCompareLink();
        Assert.assertTrue(productComparisonPage.didWeNavigatingToCompareProductPage());
    }

    @Test(priority = 9)
    public void verifyNavigatingProductComparePageFromCategoryPage() {
        landingPage.clickOnDesktopsOption();
        categoryProductPage = landingPage.clickOnShowAllDesktopsOption();
        productComparisonPage = categoryProductPage.selectProductCompareLink();
        Assert.assertTrue(productComparisonPage.didWeNavigatingToCompareProductPage());
    }

    @Test(priority = 10)
    public void verifyNoProductAddComparision() {
        landingPage.clickOnDesktopsOption();
        categoryProductPage = landingPage.clickOnShowAllDesktopsOption();
        productComparisonPage = categoryProductPage.selectProductCompareLink();
        Assert.assertTrue(productComparisonPage.didWeNavigatingToCompareProductPage());
        String expectedMessageNoProductCompare = "You have not chosen any products to compare.";
        Assert.assertEquals(productComparisonPage.getContentNoProductChoose(), expectedMessageNoProductCompare);
    }

    @Test(priority = 11)
    public void verifyButtonContinueOnProductComparisionPage() {
        landingPage.clickOnDesktopsOption();
        categoryProductPage = landingPage.clickOnShowAllDesktopsOption();
        productComparisonPage = categoryProductPage.selectProductCompareLink();
        Assert.assertTrue(productComparisonPage.didWeNavigatingToCompareProductPage());
        String expectedMessageNoProductCompare = "You have not chosen any products to compare.";
        Assert.assertEquals(productComparisonPage.getContentNoProductChoose(), expectedMessageNoProductCompare);
        landingPage = productComparisonPage.clickOnButtonContinue();
        Assert.assertEquals(getPageTitle(landingPage.getDriver()), "Your Store");
    }

    //Testcase 13
    @Test(priority = 12)
    public void verifyProductNameAndProductComparisionFromMessage() {
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        productDisplayPage = searchPage.clickOnImageProduct();
        productDisplayPage.clickOnButtonCompareProduct();
        String expectedWarningCompareProduct = "Success: You have added iMac to your product comparison!";
        Assert.assertEquals(productDisplayPage.getWarningMessageCompareProduct(), expectedWarningCompareProduct);
        productPage = productDisplayPage.clickOnProductNameFromProductSearchDetailPage();
        Assert.assertTrue(productPage.didWeNavigateToProductPage());
        navigateBack(driver);
        productComparisonPage = searchPage.clickOnProductComparison();
        Assert.assertTrue(productComparisonPage.didWeNavigatingToCompareProductPage());

    }

    //Testcase 14
    @Test(priority = 13)
    public void verifyOneProductAddComparision() {
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        productComparisonPage = searchPage.clickOnProductComparison();
        Assert.assertEquals(productComparisonPage.getQuantityProductCompare(), 1);
        Assert.assertEquals(productComparisonPage.getProductNameOnComparePage(), prop.getProperty("existingProductInSubCategory"));
        String productPrice = "$122.00";
        Assert.assertEquals(productComparisonPage.getPriceProduct(), productPrice);
        String productModel = "Product 14";
        Assert.assertEquals(productComparisonPage.getModelProduct(), productModel);
        String brandProduct = "Apple";
        Assert.assertEquals(productComparisonPage.getBrandProduct(), brandProduct);
        String statusProduct = "Out Of Stock";
        Assert.assertEquals(productComparisonPage.getStatusProduct(), statusProduct);
        Integer ratingProduct = 5;
        Assert.assertEquals(productComparisonPage.getQuantityRating(), ratingProduct);
        String summaryProduct = "Just when you thought iMac had everything, now there´s even more. More powerful Intel Core 2 Duo processors. And more memory standard. Combine this with Mac OS X Leopard and iLife ´08, and it´s mor..";
        Assert.assertEquals(productComparisonPage.getProductDetail(), summaryProduct);
        String weightProduct = "5.00kg";
        Assert.assertEquals(productComparisonPage.getWeightProduct(), weightProduct);
        String dimensionsProduct = "0.00cm x0.00cm x0.00cm";
        Assert.assertEquals(productComparisonPage.getDemensionProduct(), dimensionsProduct);
        Assert.assertTrue(productComparisonPage.didButtonAddToCartDisplay());
        Assert.assertTrue(productComparisonPage.didButtonRemoveDisplay());
    }


    @Test(priority = 14)
    public void verifyTwoProductAddComparision() {
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        landingPage.clearInputSearch();
        landingPage.enterProductNameInSearch(prop.getProperty("secondProductName"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        productComparisonPage = searchPage.clickOnProductComparison();
        Assert.assertEquals(productComparisonPage.getQuantityProductCompare(), 2);
        Assert.assertEquals(productComparisonPage.getProductNameOnComparePage(), prop.getProperty("existingProductInSubCategory"));
        Assert.assertEquals(productComparisonPage.getProductNameTwoOnComparePage(), prop.getProperty("secondProductName"));
    }

    @Test(priority = 15)
    public void verifyAddTwiceSameProductComparision() {
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        landingPage.clearInputSearch();
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        productComparisonPage = searchPage.clickOnProductComparison();
        Assert.assertEquals(productComparisonPage.getQuantityProductCompare(), 1);
        Assert.assertEquals(productComparisonPage.getProductNameOnComparePage(), prop.getProperty("existingProductInSubCategory"));

    }

    @Test(priority = 16)
    public void verifyThreeProductAddComparision() {
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        landingPage.clearInputSearch();
        landingPage.enterProductNameInSearch(prop.getProperty("secondProductName"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        landingPage.clearInputSearch();
        landingPage.enterProductNameInSearch(prop.getProperty("thirdProductName"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        productComparisonPage = searchPage.clickOnProductComparison();
        Assert.assertEquals(productComparisonPage.getQuantityProductCompare(), 3);
        Assert.assertEquals(productComparisonPage.getProductNameOnComparePage(), prop.getProperty("existingProductInSubCategory"));
        Assert.assertEquals(productComparisonPage.getProductNameTwoOnComparePage(), prop.getProperty("secondProductName"));
        Assert.assertEquals(productComparisonPage.getProductNameThreeOnComparePage(), prop.getProperty("thirdProductName"));

    }

    @Test(priority = 17)
    public void verifyFourProductAddComparision() {
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        landingPage.clearInputSearch();
        landingPage.enterProductNameInSearch(prop.getProperty("secondProductName"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        landingPage.clearInputSearch();
        landingPage.enterProductNameInSearch(prop.getProperty("thirdProductName"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        landingPage.clearInputSearch();
        landingPage.enterProductNameInSearch(prop.getProperty("fourthProductName"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        productComparisonPage = searchPage.clickOnProductComparison();
        Assert.assertEquals(productComparisonPage.getQuantityProductCompare(), 4);
        Assert.assertEquals(productComparisonPage.getProductNameOnComparePage(), prop.getProperty("existingProductInSubCategory"));
        Assert.assertEquals(productComparisonPage.getProductNameTwoOnComparePage(), prop.getProperty("secondProductName"));
        Assert.assertEquals(productComparisonPage.getProductNameThreeOnComparePage(), prop.getProperty("thirdProductName"));
        Assert.assertEquals(productComparisonPage.getProductNameFourthOnComparePage(), prop.getProperty("fourthProductName"));

    }

    @Test(priority = 18)
    public void verifyFiveProductAddComparision() {
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        landingPage.clearInputSearch();
        landingPage.enterProductNameInSearch(prop.getProperty("secondProductName"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        landingPage.clearInputSearch();
        landingPage.enterProductNameInSearch(prop.getProperty("thirdProductName"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        landingPage.clearInputSearch();
        landingPage.enterProductNameInSearch(prop.getProperty("fourthProductName"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        landingPage.clearInputSearch();
        landingPage.enterProductNameInSearch(prop.getProperty("fifthProductName"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        productComparisonPage = searchPage.clickOnProductComparison();
        Assert.assertEquals(productComparisonPage.getQuantityProductCompare(), 4);
        Assert.assertEquals(productComparisonPage.getProductNameOnComparePage(), prop.getProperty("secondProductName"));
        Assert.assertEquals(productComparisonPage.getProductNameTwoOnComparePage(), prop.getProperty("thirdProductName"));
        Assert.assertEquals(productComparisonPage.getProductNameThreeOnComparePage(), prop.getProperty("fourthProductName"));
        Assert.assertEquals(productComparisonPage.getProductNameFourthOnComparePage(),prop.getProperty("fifthProductName"));
    }

    @Test(priority = 19)
    public void verifyAddingProductToCartFromProductComparisionPage(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        productComparisonPage = searchPage.clickOnProductComparison();
        productComparisonPage.clickOnButtonAddToCart();
        String expectedWarningAddToCart = "Success: You have added iMac to your shopping cart!";
        Assert.assertEquals(productComparisonPage.getMessageProductSuccessful(), expectedWarningAddToCart);
    }

    @Test(priority = 20)
    public void verifyRemovingProductToCartFromProductComparisionPage(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        productComparisonPage = searchPage.clickOnProductComparison();
        productComparisonPage.clickOnButtonRemove();
        String expectedWarningAddToCart = "Success: You have modified your product comparison!";
        Assert.assertEquals(productComparisonPage.getMessageProductSuccessful(), expectedWarningAddToCart);
    }

    @Test (priority = 21)
    public void verifyPageTitlePageHeadingPageURLOfProductComparisionPage(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnButtonCompareProduct();
        productComparisonPage = searchPage.clickOnProductComparison();
        getPageTitle(driver);
        getURLPage(driver);
    }




}