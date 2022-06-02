package mykademyTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import core.TestBase;

public class FacilitatorModule extends TestBase{
	
	
	@Parameters({"username", "password"})
	@Test (priority = 1)
	public void Login_Check(String username, String password) {
//		getDriver().findElement(By.xpath(".//li[@class='d-flex align-items-center h-100']//a[text()='SIGN IN']")).click();
		getDriver().findElement(By.xpath(".//input[@id='login_email']")).sendKeys(username);
		getDriver().findElement(By.xpath(".//input[@id='login_password']")).sendKeys(password);
		getDriver().findElement(By.xpath(".//div[@class='form-group text-right']//a[text()='Sign In']")).click();
	}

	@Test(priority = 2, dependsOnMethods = "Login_Check")
	public void MoveToFacilitatorModule() {
		
		WebElement users = getDriver().findElement(By.xpath(".//ol[@class='sidebar-menu']//following-sibling::li//span[contains(text(),'Users')]"));
		users.click();
		WebElement learners = getDriver().findElement(By.xpath(".//ol[@class='sidebar-menu']//following-sibling::li//div//ul//li//a[text()='Facilitators']"));
		learners.click();
	}
	
	@SuppressWarnings("deprecation")
	@Test(priority = 3, dependsOnMethods = "MoveToFacilitatorModule")
	public void AddFacilitator() {
		getDriver().findElement(By.xpath(".//button[@class='pull-right btn btn-green' and contains(text(),'ADD FACILITATOR')]")).click();
		getDriver().manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		WebElement FacilitatorName = getDriver().findElement(By.id("faculty_name"));
		FacilitatorName.sendKeys("TutorJijo1");
		WebElement FacilitatorEmail = getDriver().findElement(By.id("faculty_email"));
		FacilitatorEmail.sendKeys("jijo.joseph+6910@mykademy.com");
		WebElement FacilitatorPassword = getDriver().findElement(By.id("faculty_password"));
		FacilitatorPassword.sendKeys("Password@123");
		getDriver().findElement(By.id("faculty_role")).click();
		getDriver().findElement(By.xpath("(.//select[@id='faculty_role']//following-sibling::option)[3]")).click();
		getDriver().findElement(By.id("create_box_ok")).click();
	}
	
	@Test(priority = 4, dependsOnMethods = "MoveToFacilitatorModule")
	public void AddToCourse() throws InterruptedException {
		Thread.sleep(2000);
		getDriver().findElement(By.xpath(".//input[@value='ADD TO COURSE']")).click();
		WebElement searchCourse = getDriver().findElement(By.id("search-course-in-add-to-course-modal"));
		searchCourse.sendKeys("Beta Release 1.1");
		getDriver().findElement(By.xpath(".//input[@class='course-all m-0 mr-1' and @value='all']")).click();
		getDriver().findElement(By.id("add_user_ok")).click();
		Thread.sleep(3000);
	}
	
	
	@Test(priority = 5, dependsOnMethods = "AddFacilitator")
	public void DeleteFacilitator() throws InterruptedException {
		getDriver().findElement(By.xpath("(.//span[@class='dropdown-tigger']//i[@class='icon icon-down-arrow'])[1]")).click();
		WebElement deleteAccount = getDriver().findElement(By.xpath(".//div[@class='btn-group lecture-control open']//ul//a[contains(text(),'Delete Account')]"));
		deleteAccount.click();
		getDriver().findElement(By.id("advanced_confirm_box_ok")).click();
		Thread.sleep(500);
		getDriver().findElement(By.id("advanced_confirm_box_ok")).click();
	}
	
	@Test(priority = 6, dependsOnMethods = "MoveToFacilitatorModule")
	public void GoToHomePage() {
		getDriver().findElement(By.xpath(".//a[@class='logo']")).click();
	}
	
	@Test(priority = 7, dependsOnMethods = "Login_Check")
	public void teaDown() {
		if(getDriver()!=null) {
			getDriver().quit();
		}
	}
	
}

