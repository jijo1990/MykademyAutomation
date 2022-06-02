package mykademyTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import core.TestBase;


public class LearnerModule extends TestBase {
	
	 
	@Parameters({"username", "password"})
	@Test (priority = 1)
	public void Login_Check(String username, String password) {
//		getDriver().findElement(By.xpath(".//li[@class='d-flex align-items-center h-100']//a[text()='SIGN IN']")).click();
		getDriver().findElement(By.xpath(".//input[@id='login_email']")).sendKeys(username);
		getDriver().findElement(By.xpath(".//input[@id='login_password']")).sendKeys(password);
		getDriver().findElement(By.xpath(".//div[@class='form-group text-right']//a[text()='Sign In']")).click();
	}

	@Test(priority = 2)
	public void MoveToLearnerModule() throws InterruptedException{

		WebElement users = getDriver().findElement(By.xpath(".//ol[@class='sidebar-menu']//following-sibling::li//span[contains(text(),'Users')]"));
		users.click();
		WebElement learners = getDriver().findElement(By.xpath(".//ol[@class='sidebar-menu']//following-sibling::li//div//ul//li//a[text()='Learners']"));
		learners.click();
//		Actions actions = new Actions(getDriver());
//		actions.moveToElement(users);
//		actions.moveToElement(learners);
//		actions.click().build().perform();	
	}
	

	
	@Test (priority = 3, dependsOnMethods = {"MoveToLearnerModule"},  dataProvider = "Learner_Details")
	public void AddLearners(String name, String email, String phone, String password) {
		getDriver().findElement(By.id("add_new_users")).click();
		WebElement Learner_name = getDriver().findElement(By.id("student_name"));
		Learner_name.sendKeys(name);
		WebElement Learner_email = getDriver().findElement(By.id("student_email"));
		Learner_email.sendKeys(email);
		WebElement Learner_phone_number = getDriver().findElement(By.id("phone_number"));
		Learner_phone_number.sendKeys(phone);
		WebElement Learner_passowrd = getDriver().findElement(By.id("student_password"));
		Learner_passowrd.sendKeys(password);
		getDriver().findElement(By.id("student_institute")).click();
		getDriver().findElement(By.xpath("(.//select[@id='student_institute']//following-sibling::option)[7]")).click();
		getDriver().findElement(By.xpath(".//button[@id='create_box_ok']")).click();
		getDriver().findElement(By.id("advanced_confirm_box_ok")).click();
		
	}
	
	@DataProvider(name = "Learner_Details")
	public Object[][] getData(){
		Object [][] data = new Object[2][4];
		
		data[0][0] = "Jijo Joseph 6902";
		data[1][0] = "Jijo Joseph 6901";
		
		data[0][1] = "jijo.joseph+6902@mykademy.com";
		data[1][1] = "jijo.joseph+6901@mykademy.com";
		
		data[0][2] = "08988885479";
		data[1][2] = "08988885477";
		
		data[0][3] = "Passowrd@123";
		data[1][3] = "Passowrd@123";
		
		return data;			
	}
	
	@SuppressWarnings("deprecation")
	@Test(priority = 4, dependsOnMethods = "AddLearners")
	public void DeleteAlreadyAddedLearners() throws InterruptedException {
		
		WebElement search_users = getDriver().findElement(By.id("user_keyword"));
		search_users.sendKeys("Jijo Joseph 6902, Jijo Joseph 6901");
		
		Thread.sleep(2000);
		WebElement selectall = getDriver().findElement(By.xpath(".//input[@class='user-checkbox-parent']//ancestor::label[text()='select all']"));
		selectall.click();
		getDriver().findElement(By.xpath(".//div[@id='user_bulk']//span[contains(text(),'Bulk Action')]")).click();
		getDriver().findElement(By.xpath(".//div[@id='user_bulk']//following-sibling::a[contains(text(),'Delete')]")).click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok']")).click();
		Thread.sleep(300);
//		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		WebDriverWait wait = new WebDriverWait(getDriver(),1000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("advanced_confirm_box_ok")));
		getDriver().findElement(By.id("advanced_confirm_box_ok")).click();
		
		WebDriverWait wait = new WebDriverWait(getDriver(),2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchclear"))).click();
		Thread.sleep(2000);
	}
	
	@Test(priority = 5, dependsOnMethods = "MoveToLearnerModule")
	public void UploadLearners() throws InterruptedException {
		
		getDriver().findElement(By.xpath(".//a[@onclick='importUsers()']")).click();
		WebElement browseFileForUpload = getDriver().findElement(By.id("import_user"));
		browseFileForUpload.sendKeys("C:\\eclipse for java\\File Upload\\learnertemplate.csv");
		getDriver().findElement(By.id("student_institute_upload")).click();
		getDriver().findElement(By.xpath("(.//select[@id='student_institute_upload']//option)[7]")).click();
		getDriver().findElement(By.xpath(".//button[@class='btn btn-green' and contains(text(),'UPLOAD')]")).click();
				
		Thread.sleep(5000);
		
//		WebDriverWait wait = new WebDriverWait(getDriver(),6000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//div[@id='user_row_wrapper']//following-sibling::input[@class='user-checkbox'])[1]"))).click();
		getDriver().findElement(By.xpath("(.//div[@id='user_row_wrapper']//following-sibling::input[@class='user-checkbox'])[1]")).click();
		getDriver().findElement(By.xpath("(.//div[@id='user_row_wrapper']//following-sibling::input[@class='user-checkbox'])[2]")).click();
		
		getDriver().findElement(By.xpath(".//div[@id='user_bulk']//span[contains(text(),'Bulk Action')]")).click();
		getDriver().findElement(By.xpath(".//div[@id='user_bulk']//following-sibling::a[contains(text(),'Delete')]")).click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok']")).click();
		Thread.sleep(300);
		getDriver().findElement(By.id("advanced_confirm_box_ok")).click();
		
	}
	@Test(priority = 6, dependsOnMethods = "MoveToLearnerModule")
	public void ExportLearner() {
		
		getDriver().findElement(By.id("user-export")).click();
		getDriver().findElement(By.xpath(".//input[@class='field-checkbox-parent']")).click();
		getDriver().findElement(By.xpath(".//button[@id='learnerExportMoadalBtn']")).click();
		
	}
	
	@Test(priority = 7, dependsOnMethods = "MoveToLearnerModule")
	public void GoToHomePage() {
		getDriver().findElement(By.xpath(".//a[@class='logo']")).click();
	}
		
	@AfterTest
	public void teaDown() {
		if(getDriver()!=null) {
			getDriver().quit();
		}
	}

}
