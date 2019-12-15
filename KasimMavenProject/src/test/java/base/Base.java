package base;

import org.openqa.selenium.support.PageFactory;

public class Base {

	public static BasePage currentPage;
	public <TPage extends BasePage> TPage GetInstance(Class<TPage> page) {
		Object obj = PageFactory.initElements(DriverContext.driver, page);
		return page.cast(obj);
	}

}
