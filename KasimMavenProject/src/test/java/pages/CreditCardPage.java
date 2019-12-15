package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreditCardPage {
	
	public CreditCardPage() {}
	
	
	@FindBy(name="cardnumber") private WebElement cardNumberTextField;
	@FindBy(xpath="(//*[@name='cardnumber']//following::input)[1]") private WebElement expiryDateTextField;
	@FindBy(xpath="(//*[@name='cardnumber']//following::input)[2]") private WebElement cvvTextField;
	@FindBy(xpath = "//*[contains(text(),'Pay Now')]") private WebElement payNowButton;
	
	
	public CreditCardPage enterCardNumber(String cardNumber) {
		cardNumberTextField.clear();
		cardNumberTextField.sendKeys(cardNumber);
		return this;
	}
	public CreditCardPage enterExpiryDate(String expiryDate) {
		expiryDateTextField.clear();
		expiryDateTextField.sendKeys(expiryDate);
		return this;
	}
	public CreditCardPage entercvvText(String cvvText) {
		cvvTextField.clear();
		cvvTextField.sendKeys(cvvText);
		return this;
	}
	

	
	

}
