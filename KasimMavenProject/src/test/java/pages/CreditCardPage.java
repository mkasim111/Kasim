package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;
import base.DriverContext;

public class CreditCardPage extends BasePage{
	private WebDriverWait wait = new WebDriverWait(DriverContext.driver, 10);
	public CreditCardPage() {}
	
	
	@FindBy(name="cardnumber") private WebElement cardNumberTextField;
	@FindBy(xpath="(//*[@name='cardnumber']//following::input)[1]") private WebElement expiryDateTextField;
	@FindBy(xpath="(//*[@name='cardnumber']//following::input)[2]") private WebElement cvvTextField;
	@FindBy(xpath = "//*[@id='app']/div/div[1]") private WebElement payNowButton;
	@FindBy(xpath = "(//*[@class='card-container'])[1]/div/div/span") private WebElement errorMessageForInvalidCard;
	@FindBy(xpath = "//*[@class='text-amount-amount']") private WebElement amountAfterEnteringCard;
	
	
	
	
	public CreditCardPage enterCardNumber(String cardNumber) {
		wait.until(ExpectedConditions.visibilityOf(cardNumberTextField));
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
	
	public CreditCardIssuingBank clickPayNow() {
		payNowButton.click();
		return GetInstance(CreditCardIssuingBank.class);
	}
	
	public String getInvalidErrorMessage() {
		wait.until(ExpectedConditions.visibilityOf(cardNumberTextField));
		return errorMessageForInvalidCard.getText();
	}
	
	public String getTotalAmountInCC() {
		return amountAfterEnteringCard.getText();
	}

}
