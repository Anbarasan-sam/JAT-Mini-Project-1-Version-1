package MiniProject1;

import MiniProject1.HomePage;
import MiniProject1.LoginPage;
import utils.ScreenshotUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LogoutTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeTest(groups = "Logout")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test(groups = "Logout")
    public void testLogoutButtonVisibility() {

    	homePage.clickLoginButton();
    	 loginPage.enterUsername("testusername00001");
         loginPage.enterPassword("testuser1");
        loginPage.clickLoginSubmit();
        
        Assert.assertTrue(homePage.isLogoutButtonVisible(), "Log out button is not visible after login.");
    }

    @Test(groups = "Logout", dependsOnMethods = "testLogoutButtonVisibility")
    public void testLogoutRedirectionToHomePage() throws InterruptedException {

    	homePage.clickLogoutButton();
    	Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        Thread.sleep(2000);
        Assert.assertTrue(homePage.isLoginButtonVisible(), "Login button is not visible after logout.");
        System.out.println("Current url ::"+ currentUrl);
    }
    
    @AfterMethod(groups = "Logout")
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.takeScreenshot(driver, result.getName());
        }
    }

    @AfterTest(groups = "Logout")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
