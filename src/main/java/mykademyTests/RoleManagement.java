package mykademyTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import core.TestBase;

public class RoleManagement extends TestBase{

	@Parameters({"username", "password"})
	@Test (priority = 1)
	public void Login_Check(String username, String password) {
//		getDriver().findElement(By.xpath(".//li[@class='d-flex align-items-center h-100']//a[text()='SIGN IN']")).click();
		getDriver().findElement(By.xpath(".//input[@id='login_email']")).sendKeys(username);
		getDriver().findElement(By.xpath(".//input[@id='login_password']")).sendKeys(password);
		getDriver().findElement(By.xpath(".//div[@class='form-group text-right']//a[text()='Sign In']")).click();
	}

	@Test(priority = 2, dependsOnMethods = "Login_Check")
	public void MoveToRoleManagement() {
		
		WebElement users = getDriver().findElement(By.xpath(".//ol[@class='sidebar-menu']//following-sibling::li//span[contains(text(),'Users')]"));
		users.click();
		WebElement learners = getDriver().findElement(By.xpath(".//ol[@class='sidebar-menu']//following-sibling::li//div//ul//li//a[text()='Role Management']"));
		learners.click();
	}
	
	@Test(priority = 3, dependsOnMethods = "MoveToRoleManagement")
	public void CreateNewRole() throws InterruptedException {
		
		getDriver().findElement(By.xpath(".//a[@data-target='#create_role' and contains(text(),'CREATE NEW ROLE')]")).click();
		WebElement roleName = getDriver().findElement(By.id("role_name"));
		
		roleName.sendKeys("New Test Role7");
		getDriver().findElement(By.xpath(".//button[@id='create_box_ok' and contains(text(),'CREATE')]")).click();
		
		
		getDriver().findElement(By.xpath(".//input[@id='view']")).click();
		getDriver().findElement(By.xpath(".//input[@id='add']")).click();
		getDriver().findElement(By.xpath(".//input[@id='edit']")).click();
		getDriver().findElement(By.xpath(".//input[@id='delete']")).click();
		getDriver().findElement(By.xpath(".//input[@class='access5 module37 all-access-other']")).click();
		getDriver().findElement(By.xpath(".//input[@class='access5 module44 all-access-other']")).click();
		getDriver().findElement(By.xpath(".//button[@type='submit']")).click();
		Thread.sleep(2000);
		getDriver().findElement(By.id("advanced_confirm_box_ok")).click();
		
//		getDriver().findElement(By.xpath("(.//a[contains(text(),'Role')])[2]")).click();
		
		getDriver().findElement(By.xpath(".//button[@type='button' and contains(text(),'Back')]")).click();
	}
	
	@Test(priority = 4 , dependsOnMethods = "CreateNewRole")
	public void DeleteRole() throws InterruptedException {
		
		getDriver().findElement(By.xpath(".//a[@class='text-capitalize' and contains(text(),'role 133')]//ancestor::div[@class='rTableRow']//following-sibling::div//span[@class='dropdown-tigger']")).click();
		getDriver().findElement(By.xpath(".//div[@data-title='role 133']//following-sibling::div[@class='btn-group lecture-control open']//a[contains(text(),'Delete')]")).click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok']")).click();
		Thread.sleep(300);
		getDriver().findElement(By.id("advanced_confirm_box_ok")).click();
		
		
	}
	
	@Test(priority = 5, dependsOnMethods = "DeleteRole")
	public void ActivateRole() throws InterruptedException {
		
		getDriver().findElement(By.xpath("(.//a[@class='text-capitalize' and contains(text(),'role 133')]//ancestor::div[@class='rTableRow']//following-sibling::div//span[@class='dropdown-tigger'])[1]")).click();
		getDriver().findElement(By.xpath(".//a[@class='text-capitalize' and contains(text(),'role 133')]//ancestor::div[@class='rTableRow']//following-sibling::div//a[contains(text(),'Restore')]")).click();
		getDriver().findElement(By.id("advanced_confirm_box_ok")).click();
		Thread.sleep(500);
		getDriver().findElement(By.id("advanced_confirm_box_ok")).click();
		
	}
	
	@Test(priority = 6, dependsOnMethods = "MoveToRoleManagement")
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
