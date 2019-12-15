package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;
import base.DriverContext;

public class ShoppingCartPage extends BasePage{
	private WebDriverWait wait = new WebDriverWait(DriverContext.driver, 10);
	public ShoppingCartPage() {
	}

	@FindBy(xpath = "//*[@class='input-label' and contains(text(),'Name')]//following-sibling::td/input")
	public WebElement nameTextField;

	@FindBy(xpath = "//*[@class='input-label' and contains(text(),'Email')]//following-sibling::td/input")
	private WebElement emailTextField;
	
	@FindBy(xpath = "//*[@class='input-label' and contains(text(),'Phone no')]//following-sibling::td/input")
	private WebElement phoneTextField;
	
	@FindBy(xpath = "//*[@class='input-label' and contains(text(),'City')]//following-sibling::td/input")
	private WebElement cityTextField;
	
	@FindBy(xpath = "//*[@class='input-label' and contains(text(),'Address')]//following-sibling::td/textarea")
	private WebElement addressTextField;
	
	@FindBy(xpath = "//*[@class='input-label' and contains(text(),'Postal Code')]//following-sibling::td/input")
	private WebElement postTextField;
	
	@FindBy(xpath="//div[contains(text(),'CHECKOUT')]") private WebElement checkOutButton;
	@FindBy(xpath="(//*[@class='amount'])[2]") private WebElement totalAmount;
	
	
	
	
	public ShoppingCartPage enterName(String name) {
		wait.until(ExpectedConditions.visibilityOf(nameTextField));
		nameTextField.clear();
		nameTextField.sendKeys(name);
		return this;
	}
	
	public ShoppingCartPage enterEmail(String email) {
		emailTextField.clear();
		emailTextField.sendKeys(email);
		return this;
	}
	
	public ShoppingCartPage enterPhone(String phone) {
		phoneTextField.clear();
		phoneTextField.sendKeys(phone);
		return this;
	}
	
	public ShoppingCartPage enterCity(String city) {
		cityTextField.clear();
		cityTextField.sendKeys(city);
		return this;
	}
	
	public ShoppingCartPage enterAddress(String address) {
		addressTextField.clear();
		addressTextField.sendKeys(address);
		return this;
	}
	public ShoppingCartPage enterPost(String postCode) {
		postTextField.clear();
		postTextField.sendKeys(postCode);
		return this;
	}
	
	public OrderSummaryPage clickCheckoutButton() {
		checkOutButton.click();
		return GetInstance(OrderSummaryPage.class);
	}

	public String getTotalAmountForPillow() {
		return totalAmount.getText();
	}

}
