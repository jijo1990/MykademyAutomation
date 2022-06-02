package mykademyTests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SignUp extends core.TestBase{
//	String countryNames;
//	String code = "India";
	
	@Parameters({"username", "email", "phone", "password"})
	@Test(priority = 1)
	public void signUpTest(String username, String email, String phone, String password) {
		
		getDriver().findElement(By.xpath(".//a[@id='signup']")).click();
		getDriver().findElement(By.xpath(".//div[@class='form-group ']//input[@placeholder='Full name']")).sendKeys(username);
		getDriver().findElement(By.xpath(".//div[@class='form-group ']//input[@placeholder='Email address']")).sendKeys(email);
		getDriver().findElement(By.id("country_code")).click();
		getDriver().findElement(By.xpath(".//select[@id='country_code']//option[@value='+91']")).click();
		
//		List<WebElement> county_code = getDriver().findElements(By.xpath(".//select[@id='country_code']//option"));
//		for(int i=0;i<county_code.size();i++) {
//			countryNames = county_code.get(i).getText();
//			System.out.println(countryNames);
//		}
		
		String expected_captcha = "Captcha cannot be empty";
		getDriver().findElement(By.id("number")).sendKeys(phone);
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.xpath(".//div[@class='form-group text-right']/a[text()='Join']")).click();
		String captcha_Error = getDriver().findElement(By.xpath(".//span[@id='error_message_captcha']")).getText();
		captcha_Error.trim();
		if(expected_captcha.equals(captcha_Error)) {
			System.err.println("SignUp page Error check - PASSED");
		}else {
			System.out.println("SignUp page Error check - FAILED");
		}
		
	}
	
	@AfterTest
	public void teaDown() {
		if(getDriver()!=null) {
			getDriver().quit();
		}
	}
}

