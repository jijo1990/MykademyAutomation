package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	protected WebDriver driver = null;
	
	@SuppressWarnings("deprecation")
	public WebDriver getDriver(String browser) {
		
		switch(browser.toLowerCase()) {
		
		case "chrome" :
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			break;
		
		case "firefox" : 
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
			
		case "edge" :
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
			
		default :
			System.out.println("Incorrect browser name provided" + browser);
			System.out.println("Hence running in Chrome ");
			driver = new ChromeDriver();
			break;
		}
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);  // One time config done for the whole project
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS); // One time config
			
		return driver;
	}
	
}
