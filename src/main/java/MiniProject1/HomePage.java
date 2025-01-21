package MiniProject1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.BaseClass;

import java.time.Duration;
import java.util.List;

public class HomePage extends BaseClass {
    
	private WebDriver driver;
	private WebDriverWait wait; 
	
    @FindBy(id = "signin2")
    private WebElement signUpButton;

    @FindBy(id = "login2")
    private WebElement loginButton;

    @FindBy(id = "logout2")
    private WebElement logoutButton;

    @FindBy(xpath = "//a[text()='Home ']")
    private WebElement homeMenu;

    @FindBy(xpath = "//a[text()='Contact']")
    private WebElement contactMenu;
    
    @FindBy(xpath="//li//a[text()='About us']")
	private WebElement aboutusMenu;
	
	@FindBy(id="cartur")
	private WebElement cartMenu;

    @FindBy(id = "nameofuser")
    private WebElement welcomeUser;

    @FindBy(xpath = "//a[@id='itemc']")
    private List<WebElement> categories;

    @FindBy(xpath = "//a[@id='nava']")
    private WebElement applicationLogo;
    
    @FindBy(xpath = "//a[@class='hrefch']")
    private List<WebElement> productLinks;
    
    @FindBy(xpath = "//div[@id='more-information']")
    private WebElement productDescription;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isSignUpButtonVisible() {
        return signUpButton.isDisplayed();
    }

    public void clickSignUpButton() {
        signUpButton.click();
    }
    
    public boolean isSignUpButtonClickable() {
        return signUpButton.isEnabled();
    }

    public boolean isLoginButtonClickable() {
        return loginButton.isEnabled();
    }

    public boolean isLoginButtonVisible() {
        return loginButton.isDisplayed();
    }

    public void clickLoginButton() {
        loginButton.click();
    }
    
    public boolean isLogoutButtonVisible() {
    	waitForElementVisible(logoutButton, 10);
    	return logoutButton.isDisplayed();
    }

    public void clickLogoutButton() {
    	waitForElementClickable(logoutButton, 10);
        logoutButton.click();
    }

    public boolean isWelcomeUserDisplayed() {
    	waitForElementVisible(welcomeUser, 10);
        return welcomeUser.isDisplayed();
    }

    public boolean isHomeMenuDisplayed() {
        return homeMenu.isDisplayed();
    }
    public void clickHomeMenu() {
    	waitForElementClickable(homeMenu, 10);
         homeMenu.click();
    }

    public boolean isContactMenuDisplayed() {
        return contactMenu.isDisplayed();
    }
    
    public boolean isAboutusMenuDisplayed() {
		waitForElementVisible(aboutusMenu, 10);
        return aboutusMenu.isDisplayed();
    }
	
	public boolean isCartmenuDispalyed() {
		waitForElementVisible(cartMenu, 10);
		return cartMenu.isDisplayed();
	}
	
	public void clickCart() {
		waitForElementClickable(cartMenu, 10);
		 cartMenu.click();
	}

    public List<String> getCategories() {
    	waitForElementVisible(homeMenu, 10);
    	 return categories.stream().map(WebElement::getText).toList();
    }

    public boolean isLogoDisplayed() {
    	waitForElementVisible(applicationLogo, 10);
        return applicationLogo.isDisplayed();
    }
    
    public String getProductDescription() {
    	waitForElementVisible(productDescription, 10);
        return productDescription.getText();
    }
    
    public void selectProduct(String productName) {
            wait.until(ExpectedConditions.visibilityOfAllElements(productLinks)); 
        for (WebElement product : productLinks) {
            if (product.getText().equalsIgnoreCase(productName)) {
                product.click();
                break;
            }
        }
    }
}
