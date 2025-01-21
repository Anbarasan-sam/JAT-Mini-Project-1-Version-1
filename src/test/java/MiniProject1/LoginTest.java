package MiniProject1;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.ScreenshotUtil;

public class LoginTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeTest(groups="Login")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test( groups="Login")
    public void testLoginButtonVisibility() {
        Assert.assertTrue(homePage.isLoginButtonVisible(), "Login button is not visible on the homepage.");
    }
    @Test( groups="Login")
    public void testLoginButtonClickable() {
        Assert.assertTrue(homePage.isLoginButtonClickable(), "Login button is not clickable.");
        homePage.clickLoginButton();
    }
    @Test( groups="Login")
    public void testLoginWithValidCredentials() {
        loginPage.enterUsername("testusername00001");
        loginPage.enterPassword("testuser1");
        loginPage.clickLoginSubmit();
        Assert.assertTrue(homePage.isWelcomeUserDisplayed(), "Login failed. Welcome message not displayed.");
    }
    
    @AfterMethod( groups="Login")
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.takeScreenshot(driver, result.getName());
        }
    }

    @AfterTest( groups="Login")
    public void tearDown() {
			driver.quit();
    }
}
