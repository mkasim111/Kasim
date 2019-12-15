package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import base.BrowserTypes;
import base.DriverContext;
import base.FrameworkInitialize;
import config.ConfigReader;
import config.Settings;

public class BuyProductUsingCCPaymentTest extends FrameworkInitialize{

	@Parameters("browser")
	@BeforeTest
	public void setUp(String browser) {
		try {
			ConfigReader.PopulateSettings();
			
			if(browser.toLowerCase().equals("chrome")) {
				initializeBrowser(BrowserTypes.Chrome);
			}else
			{
				initializeBrowser(BrowserTypes.Firefox);
			}
			
			DriverContext.browser.GoToUrl(Settings.url);
			DriverContext.browser.ImplicitWait();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void tearDown() {
		if(DriverContext.driver != null) {
			DriverContext.driver.manage().deleteAllCookies();
			DriverContext.driver.quit();
		}
	}
	
	
	public void buyNowProductWithCCPaymentTest() {
		
	}

}
