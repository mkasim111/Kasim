package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BrowserTypes;
import base.DriverContext;
import base.FrameworkInitialize;
import config.ConfigReader;
import config.Settings;
import pages.HomePage;
import pages.ShoppingCartPage;
import utilities.ExcelUtility;

public class BuyProductUsingCCPaymentTest extends FrameworkInitialize{

	ExcelUtility excel;
	
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
	
	
	@Test
	public void buyNowProductWithCCPaymentTest() throws InterruptedException {
		//
		//excel = new ExcelUtility("SuccessCCPayment");
		//excel = new ExcelUtility("FailedCCPayment");
		currentPage= GetInstance(HomePage.class);
		currentPage = currentPage.As(HomePage.class).clickBuyNowButton();
		Thread.sleep(4000);
		excel = new ExcelUtility("CustomerDetails");
		currentPage.As(ShoppingCartPage.class).enterName(excel.readCellData("Name"));
		currentPage.As(ShoppingCartPage.class).enterName(excel.readCellData("Email"));
		currentPage.As(ShoppingCartPage.class).enterName(excel.readCellData("Phone"));
		currentPage.As(ShoppingCartPage.class).enterName(excel.readCellData("City"));
		currentPage.As(ShoppingCartPage.class).enterName(excel.readCellData("Address"));
		currentPage.As(ShoppingCartPage.class).enterName(excel.readCellData("PostCode"));
		Thread.sleep(4000);
		currentPage = currentPage.As(ShoppingCartPage.class).clickCheckoutButton();
		
		
	}

}
