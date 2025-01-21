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

public class CartTest {

    private WebDriver driver;
    private HomePage homePage;
    private ProductDetailsPage productPage;
    private CartPage cartPage;

    @BeforeTest(groups = "Cart")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        homePage = new HomePage(driver);
        productPage = new ProductDetailsPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test(groups = "Cart")
    public void testTotalPriceCalculationAfterModification() throws InterruptedException {
        homePage.selectProduct("Samsung Galaxy S6");
        productPage.clickAddToCart();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        homePage.clickHomeMenu();
        homePage.selectProduct("Nokia lumia 1520");
        productPage.clickAddToCart();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        homePage.clickCart();
        double totalPrice = cartPage.getTotalPrice();
        Thread.sleep(2000);
        Assert.assertEquals(totalPrice, 1180, "Total price calculation is incorrect.");
    }
    
    @Test(groups = "Cart",priority=2)
    public void testDeleteProductFromCart() throws InterruptedException {
        Thread.sleep(2000);
        cartPage.deleteProduct("Samsung galaxy s6");

        Thread.sleep(2000);
        cartPage.deleteProduct("Nokia lumia 1520");
        
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart was not cleared successfully.");
    }

    @AfterMethod(groups = "Cart")
    public void takeScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtil.takeScreenshot(driver, result.getName());
        }
    }

    @AfterTest(groups = "Cart")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
