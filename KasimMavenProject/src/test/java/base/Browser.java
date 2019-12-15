package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import utilities.PropertyManager;

public class Browser extends Base{
	public BrowserTypes type;
	private WebDriver driver;
	
	public Browser(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void GoToUrl(String url) {
		driver.get(url);
	}
	
	public void MaximizeBrowser() {
		driver.manage().window().maximize();
	}
	
	
	public void ImplicitWait() {
		driver.manage().timeouts().implicitlyWait( Integer.parseInt(PropertyManager.getInstance().getImplicitTime()), TimeUnit.SECONDS);
	}
}
