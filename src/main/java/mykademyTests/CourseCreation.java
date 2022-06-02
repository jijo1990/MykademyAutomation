package mykademyTests;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import core.TestBase;

public class CourseCreation extends TestBase {

	String checkInDate = "5-Jun-2022";

	String live_class_day = checkInDate.split("-")[0];
	String live_class_month = checkInDate.split("-")[1];
	String live_class_year = checkInDate.split("-")[2];

	@Parameters({ "username", "password" })
	@Test(priority = 1)
	public void Login_Check(String username, String password) {
//		getDriver().findElement(By.xpath(".//li[@class='d-flex align-items-center h-100']//a[text()='SIGN IN']")).click();
		getDriver().findElement(By.xpath(".//input[@id='login_email']")).sendKeys(username);
		getDriver().findElement(By.xpath(".//input[@id='login_password']")).sendKeys(password);
		getDriver().findElement(By.xpath(".//div[@class='form-group text-right']//a[text()='Sign In']")).click();
	}

	@Test(priority = 2, dependsOnMethods = "Login_Check")
	public void MoveToProductsModule() {

		WebElement products = getDriver().findElement(
				By.xpath(".//ol[@class='sidebar-menu']//following-sibling::li//span[contains(text(),'Products')]"));
		products.click();
		WebElement courses = getDriver().findElement(By.xpath(
				".//ol[@class='sidebar-menu']//following-sibling::li//div//ul//li//a[@href='https://debug.mykademy.com/admin/course/']"));
		courses.click();
	}

	@Test(priority = 3)
	public void AddCourse() throws InterruptedException {

		getDriver().findElement(By.xpath(".//a[@id='course_create']")).click();
		WebElement course_name = getDriver().findElement(By.xpath(".//input[@id='course_name']"));
		course_name.sendKeys("Automation Custom Course 10.0");
		getDriver().findElement(By.xpath(".//button[@type='button' and text()='CREATE']")).click();

		// Add Batches
		getDriver()
				.findElement(
						By.xpath(".//ol[@class='nav offa-tab custom-sidemenu']//li//a[contains(text(),'BATCHES')]"))
				.click();
		getDriver().findElement(By.xpath(".//a[@class='btn btn-violet' and contains(text(),'ENROLL BATCH')]")).click();
		WebElement batch_enroll = getDriver().findElement(By.xpath(".//span[contains(text(),'Automation')]"));
		batch_enroll.click();
		WebElement enroll_batch_btn = getDriver()
				.findElement(By.xpath(".//a[@id='enroll_user_confirmed' and text()='Enroll Batches']"));
		enroll_batch_btn.click();
		WebElement confirm_add_btn = getDriver()
				.findElement(By.xpath(".//button[@class='custom-rounded-btn selected' and text()='ADD']"));
		confirm_add_btn.click();
		Thread.sleep(500);
		getDriver().findElement(By.id("advanced_confirm_box_ok")).click();

		// Assign Facilitator
		getDriver()
				.findElement(By.xpath(
						".//ol[@class='nav offa-tab custom-sidemenu']//li//a[contains(text(),'ASSIGN FACILITATOR')]"))
				.click();
		getDriver().findElement(By.xpath(".//a[@id='add-tutor-advanced']")).click();
		getDriver().findElement(By.xpath(".//span[text()='Tutor Jijo']/..//input")).click();
		getDriver().findElement(By.xpath(".//button[@class='btn btn-green' and contains(text(),'ASSIGN')]")).click();

		// Create Course
		getDriver()
				.findElement(By.xpath(
						".//ol[@class='nav offa-tab custom-sidemenu']//li//a[contains(text(),'COURSE CONTENT')]"))
				.click();
		getDriver().findElement(By.xpath(".//a[@class='btn btn-blue' and text()='ADD SECTION']")).click();
		getDriver().findElement(By.id("section_name_create")).sendKeys("New");
		getDriver().findElement(By.xpath(".//button[@id='add_section_save_ok' and text()='CREATE']")).click();

	}

	@Test(priority = 4, dataProvider = "Course_Upload")
	public void importFile(String document_type, String description) throws InterruptedException {

		WebElement chooseFile = getDriver().findElement(By.xpath(".//input[@id='lecture_file_upload_manual']"));
		chooseFile.sendKeys(
				"C:\\Users\\Enfin\\eclipse-FrameWork-workspace\\mykademy_bms\\uploadedFiles\\" + document_type + "");

		// Choose File
		WebElement lecture_title = getDriver().findElement(By.id("lecture_name"));
		lecture_title.sendKeys(description);
		WebElement section_title = getDriver().findElement(By.xpath("(.//select[@id='section_id']//option)[2]"));
		section_title.click();
		WebElement section_description = getDriver().findElement(By.xpath(".//textarea[@id='lecture_description']"));
		section_description.sendKeys(description);
		WebElement upload_btn = getDriver().findElement(By.xpath(".//button[@id='save_lecture']"));
		upload_btn.click();
		WebElement activate_btn = getDriver().findElement(By.xpath(".//a[text()='ACTIVATE']"));
		activate_btn.click();
		WebElement confirm_activate_btn = getDriver().findElement(By.xpath(".//button[text()='ACTIVATE']"));
		confirm_activate_btn.click();
		WebElement ok_btn = getDriver()
				.findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and text()='OK']"));
		ok_btn.click();
		WebElement back_btn_from_content_page = getDriver().findElement(By.xpath(".//button[@id='back_button']"));
		back_btn_from_content_page.click();
		Thread.sleep(3000);

	}

	// Live Class

	@Test(priority = 5)
	public void LiveClassCreation() throws InterruptedException {

		WebElement live_class = getDriver()
				.findElement(By.xpath(".//span[@class='lecture-type-holder' and contains(text(),'LIVE')]"));
		live_class.click();
		WebElement live_title = getDriver().findElement(By.id("samba_live_lecture_name"));
		live_title.sendKeys("Live Session 1.0");
		Thread.sleep(200);
		WebElement section_title = getDriver()
				.findElement(By.xpath("(.//select[@id='section_id_samba_live']//option)[2]"));
		section_title.click();
		WebElement batch_select = getDriver().findElement(
				By.xpath(".//select[@id='samba_live_course_batches']//option[contains(text(),'Automation')]"));
		batch_select.click();

		WebElement live_date = getDriver().findElement(By.xpath(".//span[@id='liveclass-date_label']"));
		live_date.click();

		WebElement date_picker = getDriver().findElement(By.xpath(".//div[@id='ui-datepicker-div']"));

		if (date_picker.isDisplayed()) {

			WebElement live_month = getDriver()
					.findElement(By.xpath(".//select[@class='ui-datepicker-month']//option[@selected='selected']"));
			String live_month_name = live_month.getText().trim();

//			WebElement Live_year = getDriver().findElement(By.xpath(".//select[@class='ui-datepicker-year']//option[@selected='selected']"));
//			String Live_year_display = Live_year.getText().trim();

			while (!live_month_name.equals(live_class_month) /* && !Live_year_display.equals(Live_Class_Year) */) {
				WebElement next_arrow = getDriver()
						.findElement(By.xpath(".//span[@class='ui-icon ui-icon-circle-triangle-e' and text()='Next']"));
				next_arrow.click();
				live_month = getDriver()
						.findElement(By.xpath(".//select[@class='ui-datepicker-month']//option[@selected='selected']"));
				live_month_name = live_month.getText().trim();

//				Live_year = getDriver().findElement(By.xpath(".//select[@class='ui-datepicker-year']//option[@selected='selected']"));
//				Live_year_display = Live_year.getText().trim();

			}

			getDriver().findElement(By.xpath(".//a[@class='ui-state-default' and text()='" + live_class_day + "']"))
					.click();

		}

		getDriver().findElement(By.xpath(".//select[@id='samba_live_hour']//option[@value='10']")).click();
		getDriver().findElement(By.xpath(".//select[@id='samba_live_minitutes']//option[@value='30']")).click();
		getDriver().findElement(By.xpath(".//select[@id='samba_live_period']//option[contains(text(),'AM')]")).click();
		getDriver().findElement(By.xpath(".//button[@id='schedule-btn']")).click();

		// Live Class Next Page

		WebElement session_tag = getDriver()
				.findElement(By.xpath(".//input[@class='custom-input form-control' and @placeholder='Sample tag']"));
		session_tag.sendKeys("Automation Tag One");

		WebElement session_agenda = getDriver().findElement(By.xpath(".//textarea[@name='agenda']"));
		session_agenda.sendKeys("Automation Test Live Agenda");

		WebElement session_description = getDriver().findElement(By.xpath(".//textarea[@name='description']"));
		session_description.sendKeys("Automation session description");

		WebElement live_class_Integration = getDriver()
				.findElement(By.xpath(".//select[@id='ll_integration']//option[contains(text(),'Mykademy Live')]"));
		live_class_Integration.click();

		WebElement select_room = getDriver()
				.findElement(By.xpath(".//select[@id='ll_room_id']//option[contains(text(),'Room 12')]"));
		select_room.click();

		getDriver().findElement(By.xpath(".//button[@id='showAddLearnersPage']")).click();

		WebElement add_speaker = getDriver().findElement(By.xpath(".//input[@id='add-speaker']"));
		add_speaker.click();

		WebElement add_speaker_to_live = getDriver().findElement(By.xpath(
				".//ul[@id='add-speakerautocomplete-list']//following-sibling::li//span[contains(text(),'jijo.joseph+1@mykademy.com')]//following-sibling::span[contains(text(),'Add')]"));
		add_speaker_to_live.click();

		getDriver().findElement(By.xpath(".//button[@id='create-live-submit-button']")).click();

		getDriver().findElement(By.xpath(".//div[@id='closesuccesspopup']")).click();

		getDriver().navigate().back();
		Thread.sleep(200);
		getDriver().navigate().back();

	}

	@Test(priority = 6)
	public void AssessmentCreation() throws InterruptedException {

		WebElement assessment_test = getDriver()
				.findElement(By.xpath(".//span[@class='lecture-type-holder' and contains(text(),'Test')]"));
		assessment_test.click();

		WebElement test_name = getDriver().findElement(By.id("assesment_name"));
		test_name.sendKeys("Automation Test 1");

		WebElement select_section = getDriver()
				.findElement(By.xpath("(.//select[@id='section_id_assesment']//option)[2]"));
		select_section.click();

		WebElement test_description = getDriver().findElement(By.xpath(".//textarea[@id='assesment_description']"));
		test_description.sendKeys("Automation assessment test 1");

		getDriver().findElement(By.xpath(".//a[@id='createAssesmentConfirmed']")).click();

		// STEP1

		WebElement test_mark = getDriver().findElement(By.id("test_mark"));
		test_mark.sendKeys("10");

		WebElement save_next_btn = getDriver().findElement(By.xpath(".//button[@id='saveNext_button']"));
		save_next_btn.click();

		WebElement continue_btn = getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok']"));
		continue_btn.click();

		// STEP2

		WebElement import_question_from_pool = getDriver()
				.findElement(By.xpath(".//a[@class='btn full-width-btn import-quespool-btn']"));
		import_question_from_pool.click();

		List<WebElement> checkbox_tick = getDriver()
				.findElements(By.xpath(".//div[@id='generate_test_wrapper']//following-sibling::div//input"));

		for (int i = 0; i < 5; i++) {

			checkbox_tick.get(i).click();
		}

		getDriver().findElement(By.xpath(".//a[@id='import_question_confirmed']")).click();
		WebElement btn_ok = getDriver()
				.findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'OK')]"));
		btn_ok.click();
		WebElement save_next_btn2 = getDriver().findElement(By.xpath(".//button[@id='saveNext_button']"));
		save_next_btn2.click();

		// STEP3

		Thread.sleep(6000);

		getDriver().findElement(By.xpath(".//input[@id='test_question_grouping']")).click();
		getDriver().findElement(By.xpath(".//input[@id='test_question_shuffling']")).click();
		getDriver().findElement(By.xpath(".//input[@id='test_question_navigate']")).click();
		getDriver().findElement(By.xpath(".//input[@id='test_attend_all']")).click();
		getDriver().findElement(By.xpath(".//input[@id='test_submit_response']")).click();
		WebElement test_submission_text = getDriver().findElement(By.xpath(".//textarea[@id='test_submit_message']"));
		test_submission_text.sendKeys("Thank You For Attending The Assessment");
		getDriver().findElement(By.xpath(".//input[@id='test_show_mark']")).click();
		getDriver().findElement(By.xpath(".//input[@id='test_has_passfail']")).click();
		WebElement pass_mark_percentage = getDriver().findElement(By.xpath(".//input[@id='test_pass_percentage']"));

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,250)", "");

		pass_mark_percentage.clear();
		pass_mark_percentage.sendKeys("10");
		getDriver().findElement(By.xpath(".//input[@id='test_passfail_response']")).click();
		js.executeScript("window.scrollBy(0,250)", "");
		WebElement pass_message = getDriver().findElement(By.xpath(".//textarea[@id='test_pass_message']"));
		pass_message.sendKeys("Pass");
		WebElement fail_message = getDriver().findElement(By.xpath(".//textarea[@id='test_fail_message']"));
		fail_message.sendKeys("fail");
		getDriver().findElement(By.xpath(".//input[@id='test_que_report']")).click();
		getDriver().findElement(By.xpath(".//input[@id='test_right_report']")).click();

		WebElement save_next_btn3 = getDriver().findElement(By.xpath(".//button[@id='saveNext_button']"));
		save_next_btn3.click();

		// STEP4

		Thread.sleep(6000);

		getDriver().findElement(By.xpath(".//input[@name='cl_limited' and @value='1']")).click();
		WebElement limited_access = getDriver().findElement(By.xpath(".//input[@name='cl_limited_access']"));
		limited_access.sendKeys("2");
		getDriver().findElement(By.xpath(".//button[@id='save_button' and @value='SAVE']")).click();

		getDriver().findElement(By.xpath(".//button[@id='back_button']")).click();

	}

	@Test(priority = 7)
	public void AssignmentCreation() throws InterruptedException {

		WebElement assignment_creation = getDriver()
				.findElement(By.xpath(".//span[@class='lecture-type-holder' and contains(text(),'Assignment')]"));
		assignment_creation.click();

		WebElement assignment_title = getDriver().findElement(By.xpath(".//input[@id='descriptive_test_name']"));
		assignment_title.sendKeys("Automation Assignment title");

		getDriver().findElement(By.xpath("(.//select[@id='section_id_descriptive']//option)[2]")).click();

		WebElement assignment_description = getDriver()
				.findElement(By.xpath(".//textarea[@id='descriptive_description']"));
		assignment_description.sendKeys("Automation Assignment description");

		WebElement total_mark = getDriver().findElement(By.xpath(".//input[@id='total_mark']"));
		total_mark.sendKeys("30");

		WebElement words_limit = getDriver().findElement(By.xpath(".//input[@id='descriptive_words_limit']"));
		words_limit.sendKeys("25000");

		getDriver().findElement(By.xpath(".//span[@id='descriptive_submission_date_label']")).click();

		WebElement date_picker = getDriver().findElement(By.xpath(".//div[@id='ui-datepicker-div']"));

		if (date_picker.isDisplayed()) {

			WebElement live_month = getDriver()
					.findElement(By.xpath(".//select[@class='ui-datepicker-month']//option[@selected='selected']"));
			String live_month_name = live_month.getText().trim();

			while (!live_month_name.equals(live_class_month)) {
				WebElement next_arrow = getDriver()
						.findElement(By.xpath(".//span[@class='ui-icon ui-icon-circle-triangle-e' and text()='Next']"));
				next_arrow.click();
				live_month = getDriver()
						.findElement(By.xpath(".//select[@class='ui-datepicker-month']//option[@selected='selected']"));
				live_month_name = live_month.getText().trim();

			}

			getDriver().findElement(By.xpath(".//a[@class='ui-state-default' and text()='" + live_class_day + "']"))
					.click();

		}
		Thread.sleep(1000);
		getDriver().findElement(By.xpath(".//a[@id='createDescriptiveTest']")).click();

		getDriver().findElement(By.xpath(".//input[@id='resubmission_check']")).click();
		WebElement resubmission_count = getDriver().findElement(By.xpath(".//input[@id='resubmission_no']"));
		resubmission_count.clear();
		resubmission_count.sendKeys("2");

		getDriver().findElement(By.xpath("(.//button[@class='btn btn-green' and contains(text(),'SAVE')])[1]")).click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'OK')]")).click();

		getDriver().findElement(By.xpath(".//a[@class='btn btn-green' and contains(text(),'ACTIVATE')]")).click();
		getDriver().findElement(By.xpath(".//button[@class='custom-rounded-btn selected lc-launch']")).click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'OK')]")).click();

		getDriver().findElement(By.xpath(".//button[@id='back_button']")).click();

	}

	@Test(priority = 8)
	public void HTMLLecture() throws InterruptedException {

		WebElement html_lecture = getDriver()
				.findElement(By.xpath(".//span[@class='lecture-type-holder' and contains(text(),'HTML')]"));
		html_lecture.click();

		WebElement html_lecture_title = getDriver().findElement(By.xpath(".//input[@id='html_name']"));
		html_lecture_title.sendKeys("Automation Html lecture 1");

		getDriver().findElement(By.xpath("(.//select[@id='section_id_html']//option)[2]")).click();

		WebElement html_description = getDriver().findElement(By.xpath(".//textarea[@id='html_description']"));
		html_description.sendKeys("Automation HTML lecture content description.");

		getDriver().findElement(By.xpath(".//button[@id='createHtmlConfirmed']")).click();

		WebElement html_image_icon = getDriver().findElement(By.xpath(".//i[@class='re-icon-image']"));
		html_image_icon.click();

		WebElement html_image_upload = getDriver()
				.findElement(By.xpath(".//input[@type='file' and @multiple='multiple']"));
		html_image_upload
				.sendKeys("C:\\Users\\Enfin\\eclipse-FrameWork-workspace\\mykademy_bms\\uploadedFiles\\JPG.jpg");

		getDriver().findElement(By.xpath(".//button[@class='btn btn-green float-r1']")).click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'OK')]")).click();

		getDriver().findElement(By.xpath(".//a[@class='btn btn-green' and contains(text(),'ACTIVATE')]")).click();
		getDriver()
				.findElement(By.xpath(
						".//button[@class='custom-rounded-btn selected lc-launch' and contains(text(),'ACTIVATE')]"))
				.click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'OK')]")).click();

		getDriver().findElement(By.xpath(".//button[@id='back_button']")).click();
	}

	@Test(priority = 9)
	public void ScormCreation() throws InterruptedException {

		WebElement scorm_lecture = getDriver().findElement(By.xpath(".//input[@id='lecture_file_upload_scrom']"));
		scorm_lecture.sendKeys(
				"C:\\Users\\Enfin\\eclipse-FrameWork-workspace\\mykademy_bms\\uploadedFiles\\SL360_LMS_SCORM_2004.zip");

		WebElement scorm_title = getDriver().findElement(By.xpath(".//input[@id='lecture_name']"));
		scorm_title.sendKeys("Automation Scorm Lecture");

		getDriver().findElement(By.xpath("(.//select[@id='section_id']//option)[2]")).click();

		WebElement scorm_description = getDriver().findElement(By.xpath(".//textarea[@id='lecture_description']"));
		scorm_description.sendKeys("Automation Scorm Lecture content");

		getDriver().findElement(By.xpath(".//button[@id='save_lecture']")).click();

		getDriver().findElement(By.xpath(".//button[@id='back_button']")).click();

		Thread.sleep(20000);

		getDriver().findElement(By.xpath(".//span[text()='Automation Scorm Lecture']")).click();
		getDriver().findElement(By.xpath(".//a[@class='btn btn-green' and contains(text(),'ACTIVATE')]")).click();
		getDriver()
				.findElement(By.xpath(
						".//button[@class='custom-rounded-btn selected lc-launch' and contains(text(),'ACTIVATE')]"))
				.click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'OK')]")).click();
		getDriver().findElement(By.xpath(".//button[@id='back_button']")).click();

	}

	@Test(priority = 10)
	public void YoutubeLecture() throws InterruptedException {

		WebElement youtube_lecture = getDriver()
				.findElement(By.xpath(".//span[@class='lecture-type-holder' and contains(text(),'Youtube / Vimeo')]"));
		youtube_lecture.click();

		WebElement youtube_video_title = getDriver().findElement(By.xpath(".//input[@id='youtube_name']"));
		youtube_video_title.sendKeys("Automation YouTube Lecture");

		getDriver().findElement(By.xpath("(.//select[@id='section_id_youtube'])//option[2]")).click();

		WebElement youtube_lecture_description = getDriver()
				.findElement(By.xpath(".//textarea[@id='youtube_description']"));
		youtube_lecture_description.sendKeys("Automation Youtube Video Lecture");

		WebElement youtube_url = getDriver().findElement(By.xpath(".//input[@id='youtube_url']"));
		youtube_url.sendKeys("https://www.youtube.com/watch?v=mOAXEQevCAE");

		getDriver().findElement(By.xpath(".//button[@id='create_btn']")).click();
		getDriver().findElement(By.xpath(".//a[@class='btn btn-green' and contains(text(),'ACTIVATE')]")).click();
		getDriver()
				.findElement(By.xpath(
						".//button[@class='custom-rounded-btn selected lc-launch' and contains(text(),'ACTIVATE')]"))
				.click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'OK')]")).click();
		getDriver().findElement(By.xpath(".//button[@id='back_button']")).click();

	}

	@Test(priority = 11)
	public void SurveyLecture() throws InterruptedException {

		WebElement survey_creation = getDriver()
				.findElement(By.xpath(".//span[@class='lecture-type-holder' and contains(text(),'Survey')]"));
		survey_creation.click();

		WebElement survey_title = getDriver().findElement(By.xpath(".//input[@id='survey_name']"));
		survey_title.sendKeys("Automation Survey");

		getDriver().findElement(By.xpath("(.//select[@id='section_id_survey']//option)[2]")).click();

		WebElement survey_description = getDriver().findElement(By.xpath(".//textarea[@id='survey_description']"));
		survey_description.sendKeys("Automation Survey description.");

		getDriver().findElement(By.xpath(".//a[@id='createSurveyConfirmed']")).click();

		// Add Questions to Survey

		getDriver().findElement(By.xpath(".//button[@id='add_question']")).click();

		WebElement question1 = getDriver().findElement(By.xpath(".//textarea[@id='question_0']"));
		question1.sendKeys("Rate the Course out of Five.");

		WebElement add_options = getDriver().findElement(By.xpath(".//button[@id='add_option_btn_0']"));

		for (int i = 0; i <= 2; i++) {
			add_options.click();
		}

		List<WebElement> options = getDriver().findElements(By.xpath(
				".//input[@name='lecture_name' and @placeholder='eg: Option 1' or @placeholder='eg:Option 1' ]"));

		int number = 1;
		for (WebElement option : options) {

			String num = Integer.toString(number);

			option.sendKeys(num);
			number++;
		}

		getDriver().findElement(By.xpath(".//button[@class='btn btn-success']")).click();

		getDriver().findElement(By.xpath(".//a[@class='btn btn-green' and contains(text(),'ACTIVATE')]")).click();
		getDriver()
				.findElement(By.xpath(
						".//button[@class='custom-rounded-btn selected lc-launch' and contains(text(),'ACTIVATE')]"))
				.click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'OK')]")).click();
		getDriver().findElement(By.xpath(".//button[@id='back_button']")).click();

		WebElement facilitator_survey_creation = getDriver()
				.findElement(By.xpath(".//span[@class='lecture-type-holder' and contains(text(),'Survey')]"));
		facilitator_survey_creation.click();

		WebElement facilitator_survey_title = getDriver().findElement(By.xpath(".//input[@id='survey_name']"));
		facilitator_survey_title.sendKeys("Automation Facilitator Survey");

		getDriver().findElement(By.xpath("(.//select[@id='section_id_survey']//option)[2]")).click();

		getDriver().findElement(By.xpath(".//input[@id='survey_tutor']")).click();
		getDriver().findElement(By.xpath(".//select[@id='survey_tutor_list']//option[contains(text(),'Tutor Jijo')]"))
				.click();

		WebElement facilitator_survey_description = getDriver()
				.findElement(By.xpath(".//textarea[@id='survey_description']"));
		facilitator_survey_description.sendKeys("Automation Facilitator Survey description.");

		getDriver().findElement(By.xpath(".//a[@id='createSurveyConfirmed']")).click();

		getDriver().findElement(By.xpath(".//button[@id='add_question']")).click();
		WebElement question2 = getDriver().findElement(By.xpath(".//textarea[@id='question_0']"));
		question2.sendKeys("Rate the Facilitator out of Five.");

		WebElement add_facilitator_options = getDriver().findElement(By.xpath(".//button[@id='add_option_btn_0']"));

		for (int i = 0; i <= 2; i++) {
			add_facilitator_options.click();
		}

		List<WebElement> facilitator_options = getDriver().findElements(By.xpath(
				".//input[@name='lecture_name' and @placeholder='eg: Option 1' or @placeholder='eg:Option 1' ]"));

		int fac_number = 1;
		for (WebElement option : facilitator_options) {

			String num = Integer.toString(fac_number);

			option.sendKeys(num);
			fac_number++;
		}

		getDriver().findElement(By.xpath(".//button[@class='btn btn-success']")).click();

		getDriver().findElement(By.xpath(".//a[@class='btn btn-green' and contains(text(),'ACTIVATE')]")).click();
		getDriver()
				.findElement(By.xpath(
						".//button[@class='custom-rounded-btn selected lc-launch' and contains(text(),'ACTIVATE')]"))
				.click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'OK')]")).click();
		getDriver().findElement(By.xpath(".//button[@id='back_button']")).click();

	}

	@Test(priority = 12)
	public void CourseSettings() throws InterruptedException {

		getDriver().findElement(By.xpath(".//a[@class='btn btn-red lecture-back-btn']//i[@class='icon icon-left']"))
				.click();
		getDriver().findElement(By.xpath(".//a[text()='Settings']")).click();

		WebElement course_image_upload = getDriver().findElement(By.xpath(".//input[@id='site_logo_btn']"));
		Actions action = new Actions(getDriver());
		action.moveToElement(course_image_upload).perform();

		WebElement image_upload = getDriver().findElement(By.xpath(".//input[@id='site_logo_btn']"));

		image_upload.sendKeys("C:\\Users\\Enfin\\eclipse-FrameWork-workspace\\mykademy_bms\\uploadedFiles\\JPG.jpg");
		Thread.sleep(500);
		getDriver().findElement(By.xpath(".//div[@id='crop_image_save']")).click();

		getDriver().findElement(By.xpath("(.//button[@title='None selected'])[1]")).click();
		getDriver()
				.findElement(
						By.xpath("((.//button[@title='None selected'])[1]//following-sibling::ul//label//input)[2]"))
				.click();

		getDriver().findElement(By.xpath(".//button[@title='Care Services']")).click();

		Thread.sleep(500);
//		
//		getDriver().findElement(By.xpath("(.//button[@title='None selected'])[2]")).click();
//		getDriver().findElement(By.xpath("((.//button[@title='None selected'])[2]//following-sibling::ul//label//input)[2]")).click();
//		
//		getDriver().findElement(By.xpath(".//button[@title='US English']")).click();
//		
//		Thread.sleep(500);

		// scroll window down
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(0,250)", "");

		WebElement short_description = getDriver().findElement(By.xpath(".//textarea[@id='cb_short_description']"));
		short_description.sendKeys("This is an Automation course short Description");

		getDriver().findElement(By.xpath(".//a[text()='Course Description * :']")).click();

		WebElement course_description = getDriver().findElement(By.xpath(".//div[@role='presentation']"));
		course_description.sendKeys("This is a Course Description of the Course.");

		getDriver().findElement(By.xpath(".//a[text()='Course Short Description * :']")).click();

		getDriver().findElement(By.xpath(".//input[@id='b2benabled']")).click();
		getDriver().findElement(By.xpath(".//button[@class='btn btn-green' and contains(text(),'OK')]")).click();

		WebElement course_price = getDriver().findElement(By.xpath(".//input[@id='cb_price']"));
		course_price.clear();
		course_price.sendKeys("20");
		getDriver().findElement(By.xpath(".//select[@id='cb_app_price']//option[contains(text(),'Free')]")).click();

		WebElement save_and_Next_btn = getDriver().findElement(By.xpath(".//a[@id='course_savenext_button']"));
		save_and_Next_btn.click();

		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'OK')]")).click();

	}

	@Test(priority = 13)
	public void ActivateCourse() throws InterruptedException {

		getDriver()
				.findElement(By.xpath(
						".//ol[@class='nav offa-tab custom-sidemenu']//li//a[contains(text(),'COURSE CONTENT')]"))
				.click();

		getDriver().findElement(By.xpath("(.//span[@class='dropdown-tigger'])[2]")).click();
		getDriver().findElement(By.xpath(
				"(.//span[@class='dropdown-tigger'])[2]//following-sibling::ul//a[contains(text(),'Activate all')]"))
				.click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'ACTIVATE')]"))
				.click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'OK')]")).click();

		WebElement course_drop_down = getDriver()
				.findElement(By.xpath("(.//span[@class='dropdown-tigger']//i[@class='icon icon-down-arrow'])[1]"));
		course_drop_down.click();

		getDriver().findElement(By.xpath(".//a[text()='Activate']")).click();
		getDriver().findElement(By
				.xpath(".//button[@class='btn custom-rounded-btn selected lc-launch' and contains(text(),'Activate')]"))
				.click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'OK')]")).click();

		getDriver().findElement(By.xpath(".//a[@class='btn btn-red lecture-back-btn']//i[@class='icon icon-left']"))
				.click();

		getDriver().findElement(By.xpath("(.//a[@href='https://debug.mykademy.com/admin/course/'])[2]")).click();
	}

	@Test(priority = 14)
	public void DeleteCourse() throws InterruptedException {

		WebElement search_bar = getDriver().findElement(By.xpath(".//input[@id='course_keyword']"));
		search_bar.sendKeys("Automation Custom Course 10.0");

		Thread.sleep(1000);
		getDriver().findElement(By.xpath(".//i[@class='icon icon-down-arrow']")).click();
		getDriver().findElement(By.xpath(".//a[text()='Delete']")).click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok']")).click();
		getDriver().findElement(By.xpath(".//button[@id='advanced_confirm_box_ok' and contains(text(),'OK')]")).click();

	}

	@DataProvider(name = "Course_Upload")
	public Object[][] getData() {
		Object[][] data = new Object[7][2];

		data[0][0] = "DOC.doc";
		data[0][1] = "New Document Doc 1";

		data[1][0] = "DOCX.docx";
		data[1][1] = "New Document Docx 1";

		data[2][0] = "pptx2.pptx";
		data[2][1] = "New Document Pptx 1";

		data[3][0] = "Sample pdf.pdf";
		data[3][1] = "New Document Pdf 1";

		data[4][0] = "Sample100.xls";
		data[4][1] = "New Document xls 1";

		data[5][0] = "XLSX.xlsx";
		data[5][1] = "New Document xlsx 1";

		data[6][0] = "MP3.mp3";
		data[6][1] = "New Document Mp3 1";

		return data;
	}

}
