package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FrameworkInitialize extends Base {

	public void initializeBrowser(BrowserTypes browserType) {
		WebDriver driver = null;

		switch (browserType) {

		case Chrome:
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
					+ "\\src\\test\\resources\\drivers\\chromedriver_win32\\chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			driver = new ChromeDriver(chromeOptions);
			
			break;
		case Firefox:
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")
					+ "\\src\\test\\resources\\drivers\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			driver = new FirefoxDriver(firefoxOptions);
			break;
		default:
			System.out.println("Invalid Browser");

		}
		
		DriverContext.setDriver(driver);
		DriverContext.browser= new Browser(driver);

	}
}
