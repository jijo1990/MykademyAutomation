package mykademyTests;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login extends core.TestBase{


	@Test (priority = 1) /* (groups = {"regression"}) */
	public void Login_Error_Check() {
		
//		getDriver().findElement(By.xpath(".//li[@class='d-flex align-items-center h-100']//a[text()='SIGN IN']")).click();
		getDriver().findElement(By.xpath(".//div[@class='form-group text-right']//a[text()='Sign In']")).click();
		String username_error = getDriver().findElement(By.xpath(".//span[@id='email_message_holder']")).getText();
		System.out.println(username_error);
		String password_error = getDriver().findElement(By.xpath(".//span[@id='password_message_holder']")).getText();
		String actual_Usernam_Error = "Enter your Email id or Phone number";
		String actual_Password_Error = "Password cannot be empty";
		
		if(actual_Usernam_Error.equals(username_error) && actual_Password_Error.equals(password_error)) {
			System.out.println("The Username and Password Error Check - PASSED");
		}else {
			System.out.println("The Username and Password Error Check - FAILED");
		}	
	}
	
	@Parameters({"username", "password"})
	@Test (priority = 2)
	public void Login_Check(String username, String password) {
		getDriver().findElement(By.xpath(".//input[@id='login_email']")).sendKeys(username);
		getDriver().findElement(By.xpath(".//input[@id='login_password']")).sendKeys(password);
		getDriver().findElement(By.xpath(".//div[@class='form-group text-right']//a[text()='Sign In']")).click();
	}
	
	@Test(priority = 3, dependsOnMethods = "Login_Check")
	public void teaDown() {
		if(getDriver()!=null) {
			getDriver().quit();
		}
	}
	
}
