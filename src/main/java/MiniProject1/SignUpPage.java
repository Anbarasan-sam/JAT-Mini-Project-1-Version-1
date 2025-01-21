package MiniProject1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.BaseClass;

public class SignUpPage extends BaseClass{
    private WebDriver driver;

    @FindBy(id = "sign-username")
    private WebElement usernameField;

    @FindBy(id = "sign-password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Sign up']")
    private WebElement signUpSubmitButton;

    @FindBy(xpath = "(//div[@class='modal-content'])[2]")
    private WebElement signUpModal;
    
    @FindBy(xpath="(//button[text()='Close'])[2]")
	private WebElement closeSignUp;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isSignUpModalDisplayed() {
    	waitForElementVisible(signUpModal, 10);
        return signUpModal.isDisplayed();
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSignUpSubmit() {
        signUpSubmitButton.click();
    }
    
    public void closeSignUpPage() {
		 closeSignUp.click();
	 }
}
