package tutorialsninja.tests;

import Utils.CommonUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import tutorialsninja.base.Base;

import javax.swing.*;
import java.util.Properties;

public class Search extends Base {
    public WebDriver driver;
    Properties prop;
    LandingPage landingPage;
    SearchPage searchPage;
    LoginPage loginPage;
    AccountPage accountPage;
    HeaderOptions headerOptions;
    ProductComparisonPage productComparisonPage;
    SiteMapPage siteMapPage;
    
    @BeforeMethod
    public void setup(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);
    }

    @AfterMethod
    public void tearDown(){
        closeBrowser(driver);
    }

    @Test(priority = 1)
    public void verifySearchExistingProductName(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProduct"));
        searchPage = landingPage.clickOnSearchIconOption();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
        Assert.assertTrue(searchPage.verifyExistingProductDisplay());
        
    }
    
    @Test (priority = 2)
    public void verifySearchNonExistingProductName(){
        landingPage.enterProductNameInSearch(prop.getProperty("nonExistingProduct"));
        searchPage = landingPage.clickOnSearchIconOption();
        String expectedMessage = "There is no product that matches the search criteria.";
        Assert.assertEquals(searchPage.getWarningNonProduct(), expectedMessage);
        
    }
    
    @Test (priority = 3)
    public void verifySearchWithoutEnteringAnyProduct(){
        landingPage.clickOnSearchIconOption();
        String expectedMessage = "There is no product that matches the search criteria.";
        Assert.assertEquals(searchPage.getWarningNonProduct(), expectedMessage);
    }
    
    @Test (priority = 4)
    public void verifySearchExistingProductAfterLoggin(){
        landingPage.clickOnMyAccount();
        loginPage = landingPage.selectLoginOption();
        loginPage.enterInputEmailField(prop.getProperty("emailLogin"));
        loginPage.enterInputPassWordField(prop.getProperty("passwordLogin"));
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        accountPage = loginPage.clickOnButtonLogin();
        driver = accountPage.getDriver();
        headerOptions = new HeaderOptions(driver);
        headerOptions.enterProductNameInSearch(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickOnSearchIconOption();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
        Assert.assertTrue(searchPage.verifyExistingProductDisplay());
    }
    
    @Test (priority = 5)
    public void verifySearchResearchMultipleProduct(){
        landingPage.enterProductNameInSearch(prop.getProperty("searchTermResultMultipleProducts"));
        searchPage = landingPage.clickOnSearchIconOption();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
        Assert.assertTrue(searchPage.getNumbersProductInSearchResult() > 1);
    }

    @Test (priority = 6)
    public void verifyNameAndPlaceHolderSearchInput(){
        Assert.assertEquals(landingPage.getPlaceHolderInputSearchField(), "Search");
        searchPage = landingPage.clickOnSearchIconOption();
        Assert.assertEquals(searchPage.getPlaceHolderSearchCriteria(), "Keywords");
    }
    
    @Test (priority = 7)
    public void verifySearchExistingProductNameInSearchCriteria(){
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.enterProductNameInSearchCriteria(prop.getProperty("existingProduct"));
        searchPage.clickButtonSearchCriteria();
        Assert.assertTrue(searchPage.verifyExistingProductDisplay());
    }
    
    @Test (priority = 8)
    public void verifyEnterDescriptionProductInSearchCriteria(){
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.enterProductNameInSearchCriteria(prop.getProperty("termInProductDescription"));
        searchPage.selectCheckBoxDescription();
        searchPage.clickButtonSearchCriteria();
        Assert.assertTrue(searchPage.isProductInCategoryDisplayedInSearchResults());
    }
    
    @Test(priority = 9)
    public void verifySearchBySelectingSubCategory() {

        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.enterProductNameInSearchCriteria(
                prop.getProperty("exitingProuductInSubCategory"));
        searchPage.selectOptionFromCategoryDropdownFieldUsingIndex(3);
        searchPage.clickButtonSearchCriteria();
        Assert.assertTrue(searchPage.isProductInCategoryDisplayedInSearchResults());
       
    }
    
    @Test (priority = 10)
    public void  verifySearchByUsingParentCategoryAndSearchInSubCategoriesOption(){
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.enterProductNameInSearchCriteria(prop.getProperty("exitingProuductInSubCategory"));
        searchPage.selectOptionFromCategoryDropdownFieldUsingIndex(1);
        searchPage.clickButtonSearchCriteria();
        String expectedMessage = "There is no product that matches the search criteria.";
        Assert.assertEquals(searchPage.getWarningNonProduct(), expectedMessage);
        searchPage.selectSubCategory();
        searchPage.clickButtonSearchCriteria();
        Assert.assertTrue(searchPage.isProductInCategoryDisplayedInSearchResults());
    }
    
    @Test(priority = 11)
    public void verifyUsingListViewAndGridViewInResultSearchProduct() throws InterruptedException {
        landingPage.enterProductNameInSearch(prop.getProperty("exitingProuductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        Assert.assertTrue(searchPage.isProductInCategoryDisplayedInSearchResults());
        searchPage.clickOnListViewOption();
        searchPage.clickOnAddToCartOption();
        String expectedMessageAddToCart = "Success: You have added iMac to your shopping cart!";
        Assert.assertEquals(searchPage.getMessageProductSuccessful(), expectedMessageAddToCart );
        searchPage.clickOnButtonAddWishList();
        Thread.sleep(2000);
        String expectedMessageAddWishList = "You must login or create an account to save iMac to your wish list!";
        Assert.assertEquals(searchPage.getMessageProductSuccessful(), expectedMessageAddWishList);
        searchPage.clickOnButtonCompareProduct();
        Thread.sleep(2000);
        String expectedMessageCompareProduct = "Success: You have added iMac to your product comparison!";
        Assert.assertEquals(searchPage.getMessageProductSuccessful(), expectedMessageCompareProduct);
        searchPage.clickOnImageProduct();
        Assert.assertTrue(searchPage.didWeNavigativeToProductDetail());
        driver = navigateBack(driver);
        searchPage.clickNameProduct();
        Assert.assertTrue(searchPage.didWeNavigativeToProductDetail());
        driver = navigateBack(driver);
        landingPage.clearInputSearch();
        landingPage.enterProductNameInSearch(prop.getProperty("exitingProuductInSubCategory"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.clickOnGridViewOption();
        searchPage.clickOnAddToCartOption();
        String expectedMessageAddToCart1 = "Success: You have added iMac to your shopping cart!";
        Assert.assertEquals(searchPage.getMessageProductSuccessful(), expectedMessageAddToCart1 );
        searchPage.clickOnButtonAddWishList();
        Thread.sleep(2000);
        String expectedMessageAddWishList2 = "You must login or create an account to save iMac to your wish list!";
        Assert.assertEquals(searchPage.getMessageProductSuccessful(), expectedMessageAddWishList2);
        searchPage.clickOnButtonCompareProduct();
        Thread.sleep(2000);
        String expectedMessageCompareProduct3 = "Success: You have added iMac to your product comparison!";
        Assert.assertEquals(searchPage.getMessageProductSuccessful(), expectedMessageCompareProduct3);
        searchPage.clickOnImageProduct();
        Assert.assertTrue(searchPage.didWeNavigativeToProductDetail());
        driver = navigateBack(driver);
        searchPage.clickNameProduct();
        Assert.assertTrue(searchPage.didWeNavigativeToProductDetail());
        
    }
    
    @Test (priority = 12)
    public void verifyUsingListViewAndGridViewInResultMultipleProductSearch(){
        landingPage.enterProductNameInSearch(prop.getProperty("searchTermResultMultipleProducts"));
        searchPage = landingPage.clickOnSearchIconOption();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
        Assert.assertTrue(searchPage.getNumbersProductInSearchResult() > 1);
        searchPage.clickOnListViewOption();
        Assert.assertTrue(searchPage.getNumbersProductInSearchResult() > 1);
        searchPage.clickOnGridViewOption();
        Assert.assertTrue(searchPage.getNumbersProductInSearchResult() > 1);
        
    }
    
    @Test (priority = 13)
    public void verifyNavigationComparisonProduct(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProduct"));
        searchPage = landingPage.clickOnSearchIconOption();
        productComparisonPage = searchPage.selectProductCompareLink();
        Assert.assertTrue(productComparisonPage.didWeNavigatingToCompareProductPage());
        
    }
    
    @Test (priority = 14)
    public void verifySortingMultipleProductInSearchResult(){
        landingPage.enterProductNameInSearch(prop.getProperty("searchTermResultMultipleProducts"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.selectSortOptionInResultSearch(1);
        Assert.assertEquals(searchPage.getNameFirstProductInSearchResults(), "iMac");
        Assert.assertEquals(searchPage.getNameSecondProductInSearchResults(), "MacBook");
        Assert.assertEquals(searchPage.getNameThirdProductInSearchResults(), "MacBook Air");
        Assert.assertEquals(searchPage.getNameFourthProductInSearchResults(), "MacBook Pro");
        searchPage.selectSortOptionInResultSearch(2);
        Assert.assertEquals(searchPage.getNameFirstProductInSearchResults(), "MacBook Pro");
        Assert.assertEquals(searchPage.getNameSecondProductInSearchResults(), "MacBook Air");
        Assert.assertEquals(searchPage.getNameThirdProductInSearchResults(), "MacBook");
        Assert.assertEquals(searchPage.getNameFourthProductInSearchResults(), "iMac");
        searchPage.selectSortOptionInResultSearch(3);
        Assert.assertEquals(searchPage.getNameFirstProductInSearchResults(), "iMac");
        Assert.assertEquals(searchPage.getNameSecondProductInSearchResults(), "MacBook");
        Assert.assertEquals(searchPage.getNameThirdProductInSearchResults(), "MacBook Air");
        Assert.assertEquals(searchPage.getNameFourthProductInSearchResults(), "MacBook Pro");
        searchPage.selectSortOptionInResultSearch(4);
        Assert.assertEquals(searchPage.getNameFirstProductInSearchResults(), "MacBook Pro");
        Assert.assertEquals(searchPage.getNameSecondProductInSearchResults(), "MacBook Air");
        Assert.assertEquals(searchPage.getNameThirdProductInSearchResults(), "MacBook");
        Assert.assertEquals(searchPage.getNameFourthProductInSearchResults(), "iMac");
        
    }
    
    @Test(priority = 15)
    public void verifyValueSelectShowQuatityInSearchResult(){
        landingPage.enterProductNameInSearch(prop.getProperty("searchTermResultMultipleProducts"));
        searchPage = landingPage.clickOnSearchIconOption();
        searchPage.selectValueShowInResultSearch(0);
        Assert.assertEquals(searchPage.getValueCurrentShowOptionProduct(), "20");
        searchPage.selectValueShowInResultSearch(1);
        Assert.assertEquals(searchPage.getValueCurrentShowOptionProduct(), "25");
        searchPage.selectValueShowInResultSearch(2);
        Assert.assertEquals(searchPage.getValueCurrentShowOptionProduct(), "50");
        searchPage.selectValueShowInResultSearch(3);
        Assert.assertEquals(searchPage.getValueCurrentShowOptionProduct(), "75");
        searchPage.selectValueShowInResultSearch(4);
        Assert.assertEquals(searchPage.getValueCurrentShowOptionProduct(), "100");
        ;
    }
    
    @Test (priority = 16)
    public void verifyNavigatingToSearchPageFromSiteMap(){
        siteMapPage = landingPage.clickOnSiteMap();
        searchPage = siteMapPage.clickOnButtonSearch();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
    }
    
    @Test (priority = 17)
    public void verifyBreadCrumbSearchPage(){
        landingPage.enterProductNameInSearch(prop.getProperty("existingProduct"));
        searchPage = landingPage.clickOnSearchIconOption();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
    }
    
    @Test (priority = 18)
    public void verifyUsingTabAndEnterToSearchFunctional(){
        driver = presskeyMultipleTimes(driver, Keys.TAB, 8);
        Actions actions = new Actions(driver) ;
        actions.sendKeys("iMac").perform();
        // TAB tới nút Search (ví dụ cần thêm 1 TAB nữa)
        actions.sendKeys(Keys.TAB).perform();
        // ENTER = click button Search
        actions.sendKeys(Keys.ENTER).perform();
        searchPage = new SearchPage(driver);
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
        Assert.assertEquals(searchPage.getNumbersProductInSearchResult(), 1);
        driver = presskeyMultipleTimes(driver, Keys.TAB, 21);
        actions.sendKeys(Keys.DELETE).sendKeys(prop.getProperty("existingSampleTermResultingInMultipleProducts"));
        for(int i = 0; i < 3; i++){
            actions.sendKeys(Keys.TAB);
        }
        actions.sendKeys(Keys.ENTER).perform();
        Assert.assertEquals(searchPage.getNumbersProductInSearchResult(), 4);
    }
    
    @Test (priority = 19)
    public void verifyPageHeadingPageURLPageTitleOfSearchPage(){
        searchPage = landingPage.clickOnSearchIconOption();
        Assert.assertEquals(searchPage.getSearchPageHeading(), prop.getProperty("searchPageHeading"));
        Assert.assertEquals(getPageTitle(searchPage.getDriver()), prop.getProperty("searchPageTitle"));
        Assert.assertEquals(getURLPage(searchPage.getDriver()), prop.getProperty("searchPageURL"));
    }
    
    @Test (priority = 20)
    public void verifySearchPageUI(){
        searchPage = landingPage.clickOnSearchIconOption();
        CommonUtils.takeScreenshot(searchPage.getDriver(), "\\Screenshots\\actualUISearchPage.png");
        Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir") + "\\Screenshots\\actualUISearchPage.png", System.getProperty("user.dir") + "\\Screenshots\\expectedUISearchPage.png"));
    }
    }
    
    
    
