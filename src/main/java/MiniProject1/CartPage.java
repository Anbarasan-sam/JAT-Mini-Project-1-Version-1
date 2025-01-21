package MiniProject1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseClass;

import java.util.List;

public class CartPage extends BaseClass {
    private WebDriver driver;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//a[text()='Delete']")
    private List<WebElement> deleteButtons;

    @FindBy(id = "totalp")
    private WebElement totalPrice;

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrder;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getCartItemCount() {
        waitForElementsVisible(cartItems, 10); 
        return cartItems.size();
    }

    public boolean isProductInCart(String productName) {
        waitForElementsVisible(cartItems, 10); 
        for (WebElement item : cartItems) {
            if (item.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }

    public void deleteProduct(String productName) {
        waitForElementsVisible(cartItems, 10); 
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getText().contains(productName)) {
                deleteButtons.get(i).click();
                break;
            }
        }
    }

    public double getTotalPrice() {
        waitForElementVisible(totalPrice, 10); 
        return Double.parseDouble(totalPrice.getText().replace("$", "").trim());
    }

    public void clearCart() {
        while (cartItems.size() >0) {
            WebElement deleteButton = deleteButtons.get(0);
            waitForElementClickable(deleteButton, 10);
            deleteButton.click();
        }
    }

    public void clickPlaceOrder() {
        waitForElementClickable(placeOrder, 10);
        placeOrder.click();
    }
}
