package core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;




public class TestBase {
	private WebDriver driver;

	@Parameters("browserName")
	@BeforeSuite /* (groups = {"sanity","regression","smoke"}) */
	public void initBrowser(String browserName) {
		DriverFactory factory = new DriverFactory();
		driver = factory.getDriver(browserName);
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	@BeforeTest
	public void getMykademy() {
		if (driver != null)
			driver.get("https://debug.mykademy.com");
	}

//	@AfterSuite /* (groups = {"sanity","regression","smoke"}) */
//	public void cleanUp() {
//		if (driver != null)
//			driver.quit();
//	}
}
