package StepDefination;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class department {
	private static String randomDeprtName;

	By DepartmentsModule = By.xpath("//i[@class='fa fa-building-o']");
	By DepartmentTable = By.id("myTable");
	By DepartmentTableRow = By.xpath(".//tbody/tr");
	By Department = By.xpath(".//td[1]");
	By addDepartBtn = By.xpath("//button[@class ='add-new-user float-end']");

	By AddDepartBox = By.xpath("//input[@name='departmentName']");

	@When("User click on Department tab")
	public void user_click_on_department_tab() {
		WebDriverWait w = new WebDriverWait(browserSetups.driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(DepartmentsModule)).click();
	}

	@Then("User should see all the available Department in table")
	public void user_should_see_all_the_available_department_in_table() {
		// Find the table element by its ID
		WebElement table = browserSetups.driver.findElement(DepartmentTable);

		// Find all rows in the table body
		List<WebElement> rows = table.findElements(DepartmentTableRow);

		// Iterate over each row and extract the Department name
		for (WebElement row : rows) {
			// Get the Department name from the first column of each row
			String DepartName = row.findElement(Department).getText();
			System.out.println("Added Department is : " + DepartName);
		}
	}

	@When("User click on Add Dpartment button")
	public void user_click_on_add_dpartment_button() {
		browserSetups.driver.findElement(addDepartBtn).click();
	}

	@When("User Enter random Department in Department field")
	public void user_enter_random_department_in_department_field() {
		// Generate a random Department name
		randomDeprtName = generateRandomDepartName();

		// Find the Department field element and enter the random Department name
		browserSetups.driver.findElement(AddDepartBox).sendKeys(randomDeprtName);
		System.out.println("Passed Department value is : " + randomDeprtName);
	}

	// Static string for "Admin"
	private static final String ADMIN_PREFIX = "Aotomation_Department";

	public static void main(String[] args) {
		// Call the method to generate a random Department name
		String randomDepartName = generateRandomDepartName();

		// Print the generated random Department name
		System.out.println("Random Department Added: " + randomDepartName);
	}

	// Method to generate a random Department name
	public static String generateRandomDepartName() {
		// Define the characters to be used in the random Department name
		String characters = "0123456789"; // Only digits for the 3-digit number

		// Define the length of the random 3-digit number
		int length = 3;

		// Initialize a Random object
		Random random = new Random();

		// Create a StringBuilder to store the random Department name
		StringBuilder DepartNameBuilder = new StringBuilder();

		// Append the static "Admin" prefix
		DepartNameBuilder.append(ADMIN_PREFIX);

		// Generate a random 3-digit number
		for (int i = 0; i < length; i++) {
			DepartNameBuilder.append(characters.charAt(random.nextInt(characters.length())));
		}

		// Convert StringBuilder to String and return the random Department name
		return DepartNameBuilder.toString();
	}

	@Then("User should see generated Department in table")
	public void user_should_see_generated_department_in_table() {
		WebElement table = browserSetups.driver.findElement(DepartmentTable);

		// Find all rows in the table body
		List<WebElement> rows = table.findElements(DepartmentTableRow);

		// Flag to indicate if the hobbies is found in the table
		boolean hobbiesFound = false;

		// Iterate over each row and check if the hobbies is present
		for (WebElement row : rows) {
			// Get the hobbies name from the first column of each row
			String hobbiesName = row.findElement(By.xpath(".//td[1]")).getText();
			System.out.println("This is hobbiesName : " + hobbiesName);
			if (hobbiesName.equals(randomDeprtName)) {
				hobbiesFound = true;
				break;
			}
		}

		// Check if the hobbies is found in the table
		if (hobbiesFound) {
			System.out.println("The hobbies '" + randomDeprtName + "' is found in the table.");
		} else {
			System.out.println("The hobbies '" + randomDeprtName + "' is not found in the table.");
			// Handle the scenario accordingly, e.g., throw an assertion error
		}
	}
}
