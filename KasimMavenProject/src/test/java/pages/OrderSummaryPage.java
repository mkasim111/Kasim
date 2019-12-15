package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;
import base.DriverContext;

public class OrderSummaryPage extends BasePage{
	private WebDriverWait wait = new WebDriverWait(DriverContext.driver, 10);
	public OrderSummaryPage() {
	}

	@FindBy(xpath = "//*[@id='app']/div/div[1]") private WebElement continueButton;

	public SelectPaymentPage clickBuyNowButton() {
		try {
		wait.until(ExpectedConditions.visibilityOf(continueButton));
		continueButton.click();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return GetInstance(SelectPaymentPage.class);
	}	

}
