package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

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
import utilities.ExtentManager;

public class BuyProductUsingCCPaymentTest extends FrameworkInitialize {

	ExcelUtility excel;
	public ExtentReports extent;
	public ExtentTest test;
	ExtentManager extentManager;
	private static Logger log = LogManager.getLogger(BuyProductUsingCCPaymentTest.class);

	@Parameters("browser")
	@BeforeTest
	public void setUp(String browser) {
		try {
			ConfigReader.PopulateSettings();
			if (browser.toLowerCase().equals("chrome")) {
				initializeBrowser(BrowserTypes.Chrome);
				log.info("Chrome browser initialized");
			} else if (browser.toLowerCase().equals("firefox")) {
				initializeBrowser(BrowserTypes.Firefox);
				log.info("Firefox browser initialized");
			}

			DriverContext.browser.GoToUrl(Settings.url);
			DriverContext.browser.MaximizeBrowser();
			DriverContext.browser.ImplicitWait();
			extentManager = new ExtentManager();

			log.info("URL opened and browser maximized");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * quitting the driver after test
	 */
	@AfterTest
	public void tearDown() {
		if (DriverContext.driver != null) {
			extent.flush();
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
		extent = extentManager.createInstance();
		test = extent.createTest("Buy Pillow with Valid Credit card Test");
		currentPage = GetInstance(HomePage.class);
		currentPage = currentPage.As(HomePage.class).clickBuyNowButton();
		log.info("Buy Button Clicked");
		test.createNode("Buy Button Clicked");
		excel = new ExcelUtility("CustomerDetails");
		currentPage.As(ShoppingCartPage.class).enterName(excel.readCellData("Name"));
		currentPage.As(ShoppingCartPage.class).enterEmail(excel.readCellData("Email"));
		currentPage.As(ShoppingCartPage.class).enterPhone(excel.readCellData("Phone"));
		currentPage.As(ShoppingCartPage.class).enterCity(excel.readCellData("City"));
		currentPage.As(ShoppingCartPage.class).enterAddress(excel.readCellData("Address"));
		currentPage.As(ShoppingCartPage.class).enterPost(excel.readCellData("PostCode"));
		log.info("Details filled in shopping cart page");
		test.createNode("Details filled in shopping cart page");
		String totalAmt = currentPage.As(ShoppingCartPage.class).getTotalAmountForPillow();
		log.info("Get total amount on Shopping cart page " + totalAmt);
		test.createNode("Get total amount on Shopping cart page " + totalAmt);
		currentPage = currentPage.As(ShoppingCartPage.class).clickCheckoutButton();
		log.info("Checkout button clicked");
		test.createNode("Checkout button clicked");
		// Switch to iframe
		DriverContext.driver.switchTo().frame("snap-midtrans");
		currentPage = currentPage.As(OrderSummaryPage.class).clickContinueButton();
		log.info("Continue Button Clicked");
		test.createNode("Checkout button clicked");

		currentPage = currentPage.As(SelectPaymentPage.class).clickCCPaymentOption();
		log.info("CC payment option clicked");
		test.createNode("CC payment option clicked");

		excel = new ExcelUtility("SuccessCCPayment");
		currentPage.As(CreditCardPage.class).enterCardNumber(excel.readCellData("CardNumber"));
		currentPage.As(CreditCardPage.class).enterExpiryDate(excel.readCellData("ExpiryDate"));
		currentPage.As(CreditCardPage.class).entercvvText(excel.readCellData("CVV"));
		log.info("Details entered for a valid CC");
		test.createNode("Details entered for a valid CC");

		softAssert.assertEquals(totalAmt, currentPage.As(CreditCardPage.class).getTotalAmountInCC());
		log.info("Amount displayed for the credit card");
		test.createNode("Amount displayed for the credit card");

		currentPage = currentPage.As(CreditCardPage.class).clickPayNow();
		log.info("Pay Now Button clicked");
		test.createNode("Pay Now Button clicked");
		// Switch to inner iframe
		DriverContext.driver.switchTo().frame(0);
		currentPage.As(CreditCardIssuingBank.class).enterPassword(excel.readCellData("BANKOTP"));
		log.info("Bank OTP entered");
		test.createNode("Bank OTP entered");
		currentPage.As(CreditCardIssuingBank.class).clickOKButton();
		// Switch back to outer iframe
		DriverContext.driver.switchTo().parentFrame();
		String amountProcessed = currentPage.As(CreditCardIssuingBank.class).getAmountProcessed();
		softAssert.assertEquals("Rp " + totalAmt, amountProcessed);
		log.info("Amount processed captured and asserted");
		test.createNode("Amount processed captured and asserted");
		String successMessage = currentPage.As(CreditCardIssuingBank.class).getSuccessMessage();
		softAssert.assertEquals("Transaction successful", successMessage);
		log.info("Message captured for successful transactionsa and asserted");
		test.createNode("Message captured for successful transactionsa and asserted");
		softAssert.assertAll();
	}

	/*
	 * Failed Payment Test Case
	 */
	@Test
	public void buyNowProductWithCCPaymentFailedTest() {
		SoftAssert softAssert = new SoftAssert();
		extent = extentManager.createInstance();
		test = extent.createTest("Buy Pillow with Invalid Credit card Test");
		currentPage = GetInstance(HomePage.class);
		currentPage = currentPage.As(HomePage.class).clickBuyNowButton();
		log.info("Buy Button Clicked");
		test.createNode("Buy Button Clicked");
		excel = new ExcelUtility("CustomerDetails");
		currentPage.As(ShoppingCartPage.class).enterName(excel.readCellData("Name"));
		currentPage.As(ShoppingCartPage.class).enterEmail(excel.readCellData("Email"));
		currentPage.As(ShoppingCartPage.class).enterPhone(excel.readCellData("Phone"));
		currentPage.As(ShoppingCartPage.class).enterCity(excel.readCellData("City"));
		currentPage.As(ShoppingCartPage.class).enterAddress(excel.readCellData("Address"));
		currentPage.As(ShoppingCartPage.class).enterPost(excel.readCellData("PostCode"));
		log.info("Details filled in shopping cart page");
		test.createNode("Details filled in shopping cart page");
		currentPage = currentPage.As(ShoppingCartPage.class).clickCheckoutButton();
		log.info("Checkout button clicked");
		test.createNode("Checkout button clicked");
		DriverContext.driver.switchTo().frame("snap-midtrans");
		currentPage = currentPage.As(OrderSummaryPage.class).clickContinueButton();
		log.info("Continue Button Clicked");
		test.createNode("Checkout button clicked");
		currentPage = currentPage.As(SelectPaymentPage.class).clickCCPaymentOption();
		log.info("Payment option is clicked");
		test.createNode("Payment option is clicked");
		excel = new ExcelUtility("FailedCCPayment");
		currentPage.As(CreditCardPage.class).enterCardNumber(excel.readCellData("CardNumber"));
		currentPage.As(CreditCardPage.class).enterExpiryDate(excel.readCellData("ExpiryDate"));
		currentPage.As(CreditCardPage.class).entercvvText(excel.readCellData("CVV"));
		String error = currentPage.As(CreditCardPage.class).getInvalidErrorMessage();
		softAssert.assertEquals("Invalid card number", error);
		log.info("Error mesage validated after in correct CC " + error);
		test.createNode("Error mesage validated after in correct CC " + error);
		softAssert.assertAll();
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		System.out.println(result.getStatus());
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			String screenshotPath = TakeScreenshot(DriverContext.driver, result.getName());
			test.fail("Test Case Failed Snapshot is below " + test.addScreenCaptureFromPath(screenshotPath));

		}

		else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
			String screenshotPath = TakeScreenshot(DriverContext.driver, result.getName());
			test.pass("Test Case Passed Snapshot is below " + test.addScreenCaptureFromPath(screenshotPath));
		}

	}

	public static String TakeScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
