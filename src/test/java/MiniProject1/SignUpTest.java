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


public class SignUpTest {
    private WebDriver driver;
    private HomePage homePage;
    private SignUpPage signUpPage;

    @BeforeTest(groups = "signUp")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        homePage = new HomePage(driver);
        signUpPage = new SignUpPage(driver);
    }

    @Test(groups = "signUp")
    public void testSignUpButtonVisibility() {
        Assert.assertTrue(homePage.isSignUpButtonVisible(), "Sign up button is not visible.");
    }
    @Test(groups = "signUp")
    public void testSignUpButtonClickable() {
        Assert.assertTrue(homePage.isSignUpButtonClickable(), "Sign Up button is not clickable.");
    }
    @Test(groups = "signUp")
    public void testSignUpButtonModalVisible() {
        homePage.clickSignUpButton();
        Assert.assertTrue(signUpPage.isSignUpModalDisplayed(), "Sign up modal did not open.");
        signUpPage.closeSignUpPage();
    }

    
    @AfterMethod(groups = "signUp")
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.takeScreenshot(driver, result.getName());
        }
    }

    @AfterTest(groups = "signUp")
    public void tearDown() {
    	
    	driver.quit();
    }
}
