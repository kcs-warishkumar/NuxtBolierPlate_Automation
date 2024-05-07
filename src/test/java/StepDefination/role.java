package StepDefination;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class role {
	private static String randomRoleName;

	By roleModule = By.xpath("//i[@class='fa fa-id-card']");
	By roleTable = By.id("myTable");
	By roleTableRow = By.xpath(".//tbody/tr");
	By role = By.xpath(".//td[1]");
	By addRoleBtn = By.xpath("//*[@id=\"__nuxt\"]/div/div[2]/div/div/div/div[2]/div/div[1]/div/div/button");
	By AddRoleBox = By.xpath("//input[@name='role']");
	By SubmitBtn = By.xpath("//button[@type='submit']");
	By EditRoleIcon = By.xpath("//i[@class='fa fa-edit']");

	@When("User click on Role tab")
	public void user_click_on_role_tab() {
		WebDriverWait w = new WebDriverWait(browserSetups.driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(roleModule)).click();
	}

	@Then("User should see all the available roles in table")
	public void user_should_see_all_the_available_roles_in_table() {
		// Find the table element by its ID
		WebElement table = browserSetups.driver.findElement(roleTable);

		// Find all rows in the table body
		List<WebElement> rows = table.findElements(roleTableRow);

		// Iterate over each row and extract the role name
		for (WebElement row : rows) {
			// Get the role name from the first column of each row
			String roleName = row.findElement(role).getText();
			System.out.println("Role Name is : " + roleName);
		}
	}

	@When("User click on Add Role button")
	public void user_click_on_add_role_button() {
		browserSetups.driver.findElement(addRoleBtn).click();
	}

	// Step definition to generate a random role name
	@When("User Enter random role in role field")
	public void user_enter_in_role_field() {
		// Generate a random role name
		randomRoleName = generateRandomRoleName();

		// Find the role field element and enter the random role name
		browserSetups.driver.findElement(AddRoleBox).sendKeys(randomRoleName);
		System.out.println("Passed value is : " + randomRoleName);
	}

	// Static string for "Admin"
	private static final String ADMIN_PREFIX = "Aotomation_Admin_";

	public static void main(String[] args) {
		// Call the method to generate a random role name
		String randomRoleName = generateRandomRoleName();

		// Print the generated random role name
		System.out.println("Random Role Name: " + randomRoleName);
	}

	// Method to generate a random role name
	public static String generateRandomRoleName() {
		// Define the characters to be used in the random role name
		String characters = "0123456789"; // Only digits for the 3-digit number

		// Define the length of the random 3-digit number
		int length = 3;

		// Initialize a Random object
		Random random = new Random();

		// Create a StringBuilder to store the random role name
		StringBuilder roleNameBuilder = new StringBuilder();

		// Append the static "Admin" prefix
		roleNameBuilder.append(ADMIN_PREFIX);

		// Generate a random 3-digit number
		for (int i = 0; i < length; i++) {
			roleNameBuilder.append(characters.charAt(random.nextInt(characters.length())));
		}

		// Convert StringBuilder to String and return the random role name
		return roleNameBuilder.toString();
	}

	@When("User clicks on the Update button")
	public void user_clicks_on_the_update_button() {
		browserSetups.driver.findElement(SubmitBtn).click();
	}
	

	@Then("User should see generated roles in table")
	public void user_should_see_roles_in_table() {
		WebElement table = browserSetups.driver.findElement(roleTable);

		// Find all rows in the table body
		List<WebElement> rows = table.findElements(roleTableRow);

		// Flag to indicate if the role is found in the table
		boolean roleFound = false;

		// Iterate over each row and check if the role is present
		for (WebElement row : rows) {
			// Get the role name from the first column of each row
			String roleName = row.findElement(By.xpath(".//td[1]")).getText();
			if (roleName.equals(randomRoleName)) {
				roleFound = true;
				break;
			}
		}

		// Check if the role is found in the table
		if (roleFound) {
			System.out.println("The role '" + randomRoleName + "' is found in the table.");
		} else {
			System.out.println("The role '" + randomRoleName + "' is not found in the table.");
			// Handle the scenario accordingly, e.g., throw an assertion error
		}
	}

	@When("User click on the Edit role icon")
	public void user_click_on_the_edit_role_icon() {
		browserSetups.driver.findElement(EditRoleIcon).click();
	}

	@When("User update the role name")
	public void user_update_the_role_name() throws InterruptedException {
		browserSetups.driver.findElement(AddRoleBox).click();
		Thread.sleep(5000);
		WebElement roleField = browserSetups.driver.findElement(By.cssSelector("input[name='role']"));

		// Execute JavaScript to clear the input field's value
		JavascriptExecutor js = (JavascriptExecutor) browserSetups.driver;
		js.executeScript("arguments[0].value = '';", roleField);
		Thread.sleep(5000);
		browserSetups.driver.findElement(AddRoleBox).sendKeys("Updated_Role");

		browserSetups.driver.findElement(SubmitBtn).click();

		WebElement table = browserSetups.driver.findElement(roleTable);

		// Find all rows in the table body
		List<WebElement> rows = table.findElements(roleTableRow);

		// Flag to indicate if the role is found in the table
		boolean roleFound = false;

		// Iterate over each row and check if the role is present
		for (WebElement row : rows) {
			// Get the role name from the first column of each row

			String roleName = row.findElement(By.xpath(".//td[1]")).getText();
			if (roleName.equals("Updated_Role")) {
				roleFound = true;
				break;
			}
		}

		// Check if the role is found in the table
		if (roleFound) {
			System.out.println("The role '" + randomRoleName + "' is found in the table.");
		} else {
			System.out.println("The role '" + randomRoleName + "' is not found in the table.");
			// Handle the scenario accordingly, e.g., throw an assertion error
		}

	}
}
