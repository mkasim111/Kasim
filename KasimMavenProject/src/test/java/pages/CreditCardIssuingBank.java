package pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BasePage;
import base.DriverContext;

public class CreditCardIssuingBank extends BasePage{
	private WebDriverWait wait = new WebDriverWait(DriverContext.driver, 10);
	public CreditCardIssuingBank() {
	}
	
	
	
	@FindBy(xpath = "//input[@id='PaRes']") public WebElement passwordTextField;
	@FindBy(xpath = "//*[@id='txn_amount']") public WebElement amountValueField;
	@FindBy(xpath = "//button[@type='submit']") public WebElement okButton;
	@FindBy(xpath = "//*[@id='app']/div/div[3]/div/div/div/div/div/div[1]") public WebElement amountProcessed;
	@FindBy(xpath = "//*[@id='app']/div/div[3]/div/div/div/div/div/div[2]") public WebElement successMessage;
	
	
	
	public CreditCardIssuingBank enterPassword(String otp) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOf(passwordTextField));
		passwordTextField.clear();
		passwordTextField.sendKeys(otp);
		return this;
	}
	
	public void clickOKButton() {
		okButton.click();
	}
	
	public String getAmountProcessed() {
		wait.until(ExpectedConditions.elementToBeClickable(amountProcessed));
		String amount =amountProcessed.getText();
		return amount;
	}
	public String getSuccessMessage() {
		String message =successMessage.getText();
		return message;
	}
	
	
}
