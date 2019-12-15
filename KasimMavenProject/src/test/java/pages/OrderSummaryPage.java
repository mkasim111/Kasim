package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class OrderSummaryPage extends BasePage{

	public OrderSummaryPage() {
	}

	@FindBy(xpath = "//*[contains(text(),'Continue')]") private WebElement continueButton;

	public SelectPaymentPage clickBuyNowButton() {
		continueButton.click();
		return new SelectPaymentPage();
	}	

}
