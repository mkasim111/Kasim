package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderSummaryPage {

	public OrderSummaryPage() {
	}

	@FindBy(xpath = "//*[contains(text(),'Continue')]") private WebElement continueButton;

	public SelectPaymentPage clickBuyNowButton() {
		continueButton.click();
		return new SelectPaymentPage();
	}	

}
