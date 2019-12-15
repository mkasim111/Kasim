package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class HomePage extends BasePage{
	
	public HomePage() {}
	
	@FindBy(xpath="//a[contains(text(),'BUY NOW')]") private WebElement buyNowButton;
	
	
	public ShoppingCartPage clickBuyNowButton() {
		buyNowButton.click();
		return new ShoppingCartPage();
	}

}
