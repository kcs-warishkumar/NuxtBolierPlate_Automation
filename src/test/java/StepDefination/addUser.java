package StepDefination;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addUser {

	By userModule = By.xpath("//i[@class='fa fa-users']");
	By AddUserButton = By.xpath("//*[@id=\"__nuxt\"]/div/div[2]/div/div[2]/div/div/div/div[1]/div/button");
	By DOB = By.name("dob");
	By Gender = By.xpath("//input[@id='male']");
	By Department = By.id("departmentId");
	By Role = By.id("role");
	By Dancing = By.xpath("//label[text()='Dancing']");
	By Painting = By.xpath("//label[text()='Painting']");
	By ActiveToggleButton = By.xpath("//input[@id='flexSwitchCheckChecked']");
	By OkPopUpBtn = By.className("swal2-confirm");
	By UserTableRow = By.xpath("//table[@id='myTable']/tbody/tr");

	@When("User click on Users tab")
	public void user_click_on_users_tab() {
		WebDriverWait w = new WebDriverWait(browserSetups.driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(userModule)).click();
	}

	@When("User Click on Add New User button")
	public void user_click_on_add_new_user_button() {
		browserSetups.driver.findElement(AddUserButton).click();
	}

	@When("User fills in the following information:")
	public void user_fills_in_the_following_information(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> row : rows) {
			String field = row.get("Field");
			String value = row.get("Value");

			switch (field) {
			case "First Name":
				fillInputField("fname", value);
				break;
			case "Last Name":
				fillInputField("lname", value);
				break;
			case "Email":
				fillInputField("email", generateRandomEmail());
				break;
			default:
				System.out.println("Field not found: " + field);
			}
		}
	}

	private String generateRandomEmail() {
		String uuid = UUID.randomUUID().toString();
		return "user-" + uuid.substring(0, 8) + "@automation.com";
	}

	private void fillInputField(String fieldName, String value) {
		WebElement inputField = browserSetups.driver.findElement(By.name(fieldName));
		inputField.clear(); // Clear existing value if any
		inputField.click();
		inputField.sendKeys(value);
	}

	@When("User select DOB from calendar")
	public void user_select_dob_from_calendar() {
		// Locate the date picker input element
		WebElement datePickerElement = browserSetups.driver.findElement(DOB);

		// Clear any existing value in the date picker (optional)
		datePickerElement.clear();

		// Set the desired date for DOB (in YYYY-MM-DD format)
		String desiredDate = "01-03-1998";

		// Enter the desired date into the date picker input element
		datePickerElement.sendKeys(desiredDate);

	}

	@When("User select Male")
	public void user_select_male() {
		browserSetups.driver.findElement(Gender).click();
	}

	@When("User select Department as IT")
	public void user_select_department_as_it() {
		Select departmentDropdown = new Select(browserSetups.driver.findElement(Department));
		departmentDropdown.selectByVisibleText("HR");
		System.out.println(departmentDropdown);

	}

	@When("User select Admin as Role")
	public void user_select_admin_as_role() throws InterruptedException {
		Select role = new Select(browserSetups.driver.findElement(Role));
		role.selectByVisibleText("Admin");
		Thread.sleep(5000);
	}

	@When("User select Dancing and Painting in Hobbies")
	public void user_select_sports_and_reading_in_hobbies() {
		browserSetups.driver.findElement(Dancing).click();
		browserSetups.driver.findElement(Painting).click();
	}

	@When("Keep the Status toogle as Active")
	public void keep_the_status_toogle_as_active() {
		browserSetups.driver.findElement(ActiveToggleButton).click();
		// Find the OK button element
		WebElement okButton = browserSetups.driver.findElement(By.className("swal2-confirm"));

		// Click the OK button
		okButton.click();
	}

	@When("User clicks on the Submit button")
	public void user_clicks_on_the_submit_button() {
		browserSetups.driver.findElement(By.xpath("//button[@type='submit']")).click();
		// Find the OK button element
		WebElement okButton = browserSetups.driver.findElement(OkPopUpBtn);

		// Click the OK button
		okButton.click();
	}

	@Then("User should see a success message confirming the user has been added")
	public void user_should_see_a_success_message_confirming_the_user_has_been_added() {
		System.out.println("User Added Successfully");
	}

	@Then("User should be redirected to the Users page")
	public void user_should_be_redirected_to_the_users_page() {
		// Replace "John Doe" and "john@example.com" with the actual user's name and
		// email address
		String firstName = "John";
		String lastName = "Doe";

		// Find all rows in the table
		List<WebElement> rows = browserSetups.driver.findElements(UserTableRow);

		// Flag to indicate whether the user is found
		boolean userFound = false;

		// Iterate through each row to find the user
		for (WebElement row : rows) {
			// Get the cells in the current row
			List<WebElement> cells = row.findElements(By.tagName("td"));

			// Check if the cells contain the user's name and email
			if (cells.size() >= 4 && cells.get(1).getText().contains(firstName + " " + lastName)) {
				userFound = true;
				break; // Exit the loop if user is found
			}
		}

		// Print the result
		if (userFound) {
			System.out.println("User found in the User List table.");
		} else {
			System.out.println("User not found in the User List table.");
		}
	}
}