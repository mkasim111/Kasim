package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class SelectPaymentPage extends BasePage{
	
	public SelectPaymentPage() {
	}

	@FindBy(xpath = "//*[@id='payment-list']//a[@href='#/credit-card']")
	private WebElement crediCardPaymentLink;

	public CreditCardPage clickBuyNowButton() {
		crediCardPaymentLink.click();
		return new CreditCardPage();
	}

}
