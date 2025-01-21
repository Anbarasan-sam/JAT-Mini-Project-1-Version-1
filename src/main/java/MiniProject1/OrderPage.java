package MiniProject1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.BaseClass;

public class OrderPage extends BaseClass{
    private WebDriver driver;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement creditCardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseButton;

    @FindBy(xpath = "//div[contains(@class,'sweet-alert')]")
    private WebElement confirmationModal;

    @FindBy(xpath = "//h2[text()='Thank you for your purchase!']")
    private WebElement confirmationMessage;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public boolean isNameFieldVisible() {
		return nameField.isDisplayed();
    }
    
    public void enterName(String name) {
    	waitForElementVisible(nameField, 10);
        nameField.sendKeys(name);
    }
    
    public boolean isCountryFieldVisible() {
		return countryField.isDisplayed();
    }
    
    public void enterCountry(String country) {
    	waitForElementVisible(countryField, 10);
        countryField.sendKeys(country);
    }
    
    public boolean isCityFieldVisible() {
		return cityField.isDisplayed();
    }
    
    public void enterCity(String city) {
    	waitForElementVisible(cityField, 10);
        cityField.sendKeys(city);
    }

    public boolean isCreditCardFieldVisible() {
		return creditCardField.isDisplayed();
    }
    
    public void enterCreditCard(String cardNumber) {
    	waitForElementVisible(creditCardField, 10);
        creditCardField.sendKeys(cardNumber);
    }

    public boolean isMonthFieldVisible() {
		return monthField.isDisplayed();
    }
    
    public void enterMonth(String month) {
    	waitForElementVisible(monthField, 10);
        monthField.sendKeys(month);
    }

    public boolean isYearFieldVisible() {
		return yearField.isDisplayed();
    }
    
    public void enterYear(String year) {
    	waitForElementVisible(yearField, 10);
        yearField.sendKeys(year);
    }
    
    
    public void clickPurchaseButton() {
    	waitForElementClickable(purchaseButton, 10);
        purchaseButton.click();
    }

    public boolean isConfirmationModalDisplayed() {
    	waitForElementVisible(confirmationModal, 10);
        return confirmationModal.isDisplayed();
    }

    public String getConfirmationMessage() {
    	waitForElementVisible(confirmationMessage, 10);
        return confirmationMessage.getText();
    }
}
