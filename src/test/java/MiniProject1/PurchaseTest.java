package MiniProject1;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.ScreenshotUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PurchaseTest {
	private WebDriver driver;
	private HomePage homePage;
	private ProductDetailsPage productPage;
	private CartPage cartPage;
	private OrderPage orderPage;

	@BeforeTest(groups = "Purchase")
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.demoblaze.com/");
		homePage = new HomePage(driver);
		productPage = new ProductDetailsPage(driver);
		cartPage = new CartPage(driver);
		orderPage = new OrderPage(driver);
	}

	@Test(groups = "Purchase")
	public void testRedirectionToPlaceOrderWindow() throws InterruptedException {

		homePage.selectProduct("Samsung galaxy s6");
		productPage.clickAddToCart();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();

		homePage.clickCart();
		Thread.sleep(2000);
		cartPage.clickPlaceOrder();
	}

	@Test(groups = "Purchase",priority=2)
	public void VerifyThePresenceOfTheUserDetailsSections() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertTrue(orderPage.isNameFieldVisible(), "Name field is not visible.");
		Assert.assertTrue(orderPage.isCountryFieldVisible(), "Country field is not visible.");
		Assert.assertTrue(orderPage.isCityFieldVisible(), "City field is not visible.");
	}

	@Test(groups = "Purchase",priority=3)
	public void testPurchaseByEnteringValidDetails() throws InterruptedException {

		orderPage.enterName("John Doe");
		orderPage.enterCountry("USA");
		orderPage.enterCity("New York");
		orderPage.enterCreditCard("1234 5678 9012 3456");
		orderPage.enterMonth("12");
		orderPage.enterYear("2025");

		orderPage.clickPurchaseButton();
	}

	@Test(groups = "Purchase",priority=4)
	public void verifySuccessfullPurchase() throws InterruptedException {

		Assert.assertTrue(orderPage.isConfirmationModalDisplayed(), "Confirmation modal not displayed.");
		String confirmation = orderPage.getConfirmationMessage();
		Thread.sleep(2000);
		Assert.assertTrue(confirmation.contains("Thank you for your purchase!"),
				"Purchase confirmation not successful.");
	}

	@AfterMethod(groups = "Purchase")
	public void takeScreenshotOnFailure(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			ScreenshotUtil.takeScreenshot(driver, result.getName());
		}
	}

	@AfterTest(groups = "Purchase")
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
