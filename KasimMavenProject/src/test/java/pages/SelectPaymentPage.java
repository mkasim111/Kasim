package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectPaymentPage {
	
	public SelectPaymentPage() {
	}

	@FindBy(xpath = "//*[@id='payment-list']//a[@href='#/credit-card']")
	private WebElement crediCardPaymentLink;

	public CreditCardPage clickBuyNowButton() {
		crediCardPaymentLink.click();
		return new CreditCardPage();
	}

}
