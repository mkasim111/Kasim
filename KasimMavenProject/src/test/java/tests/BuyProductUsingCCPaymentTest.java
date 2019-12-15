package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.TestInstance;
import org.testng.asserts.SoftAssert;

import base.BrowserTypes;
import base.DriverContext;
import base.FrameworkInitialize;
import config.ConfigReader;
import config.Settings;
import pages.CreditCardIssuingBank;
import pages.CreditCardPage;
import pages.HomePage;
import pages.OrderSummaryPage;
import pages.SelectPaymentPage;
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
			DriverContext.browser.MaximizeBrowser();
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
	
	/*
	 * Success Payment Test Case
	 */
	@Test
	public void buyNowProductWithCCPaymentTest() {
		SoftAssert softAssert = new SoftAssert();
		currentPage= GetInstance(HomePage.class);
		currentPage = currentPage.As(HomePage.class).clickBuyNowButton();
		excel = new ExcelUtility("CustomerDetails");
		currentPage.As(ShoppingCartPage.class).enterName(excel.readCellData("Name"));
		currentPage.As(ShoppingCartPage.class).enterEmail(excel.readCellData("Email"));
		currentPage.As(ShoppingCartPage.class).enterPhone(excel.readCellData("Phone"));
		currentPage.As(ShoppingCartPage.class).enterCity(excel.readCellData("City"));
		currentPage.As(ShoppingCartPage.class).enterAddress(excel.readCellData("Address"));
		currentPage.As(ShoppingCartPage.class).enterPost(excel.readCellData("PostCode"));
		String totalAmt =currentPage.As(ShoppingCartPage.class).getTotalAmountForPillow();
		currentPage = currentPage.As(ShoppingCartPage.class).clickCheckoutButton();
		//Switch to iframe
		DriverContext.driver.switchTo().frame("snap-midtrans");
		currentPage = currentPage.As(OrderSummaryPage.class).clickBuyNowButton();
		currentPage =  currentPage.As(SelectPaymentPage.class).clickCCPaymentOption();
		excel = new ExcelUtility("SuccessCCPayment");
		currentPage.As(CreditCardPage.class).enterCardNumber(excel.readCellData("CardNumber"));
		currentPage.As(CreditCardPage.class).enterExpiryDate(excel.readCellData("ExpiryDate"));
		currentPage.As(CreditCardPage.class).entercvvText(excel.readCellData("CVV"));
		softAssert.assertEquals(totalAmt, currentPage.As(CreditCardPage.class).getTotalAmountInCC());
		currentPage = currentPage.As(CreditCardPage.class).clickPayNow();
		//Switch to inner iframe
		DriverContext.driver.switchTo().frame(0);
		currentPage.As(CreditCardIssuingBank.class).enterPassword(excel.readCellData("BANKOTP"));
		currentPage.As(CreditCardIssuingBank.class).clickOKButton();
		//Switch back to outer iframe
		DriverContext.driver.switchTo().parentFrame();
		String amountProcessed =currentPage.As(CreditCardIssuingBank.class).getAmountProcessed();
		softAssert.assertEquals("Rp 20,000", amountProcessed);
		String successMessage =currentPage.As(CreditCardIssuingBank.class).getSuccessMessage();
		softAssert.assertEquals("Transaction successful", successMessage);
		softAssert.assertAll();
	}
	
	
	/*
	 * Failed Payment Test Case
	 */
	@Test
	public void buyNowProductWithCCPaymentFailedTest() {
		SoftAssert softAssert = new SoftAssert();
		currentPage= GetInstance(HomePage.class);
		currentPage = currentPage.As(HomePage.class).clickBuyNowButton();
		excel = new ExcelUtility("CustomerDetails");
		currentPage.As(ShoppingCartPage.class).enterName(excel.readCellData("Name"));
		currentPage.As(ShoppingCartPage.class).enterEmail(excel.readCellData("Email"));
		currentPage.As(ShoppingCartPage.class).enterPhone(excel.readCellData("Phone"));
		currentPage.As(ShoppingCartPage.class).enterCity(excel.readCellData("City"));
		currentPage.As(ShoppingCartPage.class).enterAddress(excel.readCellData("Address"));
		currentPage.As(ShoppingCartPage.class).enterPost(excel.readCellData("PostCode"));
		currentPage = currentPage.As(ShoppingCartPage.class).clickCheckoutButton();
		DriverContext.driver.switchTo().frame("snap-midtrans");
		currentPage = currentPage.As(OrderSummaryPage.class).clickBuyNowButton();
		currentPage =  currentPage.As(SelectPaymentPage.class).clickCCPaymentOption();
		excel = new ExcelUtility("FailedCCPayment");
		currentPage.As(CreditCardPage.class).enterCardNumber(excel.readCellData("CardNumber"));
		currentPage.As(CreditCardPage.class).enterExpiryDate(excel.readCellData("ExpiryDate"));
		currentPage.As(CreditCardPage.class).entercvvText(excel.readCellData("CVV"));
		String error =currentPage.As(CreditCardPage.class).getInvalidErrorMessage();
		softAssert.assertEquals("Invalid card number", error);
		softAssert.assertAll();
	}

}
