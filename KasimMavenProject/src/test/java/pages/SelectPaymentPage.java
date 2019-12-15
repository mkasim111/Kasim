package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;
import base.DriverContext;

public class SelectPaymentPage extends BasePage{
	private WebDriverWait wait = new WebDriverWait(DriverContext.driver, 10);
	public SelectPaymentPage() {
	}

	@FindBy(xpath = "//*[@id='payment-list']//a[@href='#/credit-card']")
	private WebElement crediCardPaymentLink;

	public CreditCardPage clickCCPaymentOption() {
		wait.until(ExpectedConditions.visibilityOf(crediCardPaymentLink));
		crediCardPaymentLink.click();
		return GetInstance(CreditCardPage.class);
	}

}
