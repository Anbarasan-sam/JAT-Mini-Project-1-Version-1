package MiniProject1;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.ScreenshotUtil;

public class ProductCategoriesTest {
	private WebDriver driver;
	private HomePage homePage;

	@BeforeTest(groups = "Product")
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.demoblaze.com/");
		homePage = new HomePage(driver);
	}

	@Test(groups = "Product")
	public void testMenuItemsDisplayed() {
		Assert.assertTrue(homePage.isHomeMenuDisplayed(), "Home menu is not dispalyed on homepage.");
		Assert.assertTrue(homePage.isContactMenuDisplayed(), "Contact menu is not dispalyed on homepage.");
		Assert.assertTrue(homePage.isAboutusMenuDisplayed(), "About us menu is not dispalyed on homepage.");
		Assert.assertTrue(homePage.isCartmenuDispalyed(), "Cart menu is not dispalyed on homepage.");
		System.out.println("All Menu items are displayed on homepage. ");
	}
	@Test(groups = "Product")
	public void testCategoriesDisplayed() {
		List<String> expectedCategories = Arrays.asList("Phones", "Laptops", "Monitors");
		Assert.assertEquals(homePage.getCategories(), expectedCategories,"Categories displayed do not match expected categories.");
		System.out.println("Categories: " + homePage.getCategories());
		System.out.println("All Categories are displayed on the homepage.");
	}
	@Test(groups = "Product")
	public void testLogoDisplayed() {
		Assert.assertTrue(homePage.isLogoDisplayed(), "Logo is not displayed on the homepage");
		System.out.println("Logo is Displayed on the homepage");
	}
	
	 @AfterMethod(groups = "Product")
	    public void takeScreenshotOnFailure(ITestResult result) {
	        if (ITestResult.FAILURE == result.getStatus()) {
	            ScreenshotUtil.takeScreenshot(driver, result.getName());
	        }
	    }

	 @AfterTest(groups = "Product")
	    public void tearDown() {
	    	
				driver.quit();
		}
}
