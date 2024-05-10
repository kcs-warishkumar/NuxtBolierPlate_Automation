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

public class Hobbies {
	private static String randomHobbiesName;

	By hobbiesModule = By.xpath("//i[@class='fa fa-magic']");
	By hobbiesTable = By.id("myTable");
	By hobbiesTableRow = By.xpath(".//tbody/tr");
	By Hobbies = By.xpath(".//td[1]");
	By SubmitBtn = By.xpath("//button[@type='submit']");
	
	By addHobbiesBtn = By.xpath("//button[@class ='add-new-user float-end']");
	By AddHobbiesBox = By.xpath("//input[@name='hobbie']");
	By EditHobbiesIcon = By.xpath("//i[@class='fa fa-edit']");
	By nextPageButton = By.xpath("//a[@id='myTable_next']");
	
	@When("User click on Hobbies tab")
	public void user_click_on_hobbies_tab() {
		WebDriverWait w = new WebDriverWait(browserSetups.driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(hobbiesModule)).click();
	}

	@Then("User should see all the available Hobbies in table")
	public void user_should_see_all_the_available_hobbies_in_table() {
		// Find the table element by its ID
		WebElement table = browserSetups.driver.findElement(hobbiesTable);

		// Find all rows in the table body
		List<WebElement> rows = table.findElements(hobbiesTableRow);

		// Iterate over each row and extract the hobbies name
		for (WebElement row : rows) {
			// Get the hobbies name from the first column of each row
			String hobbiesName = row.findElement(Hobbies).getText();
			System.out.println("Added Hobbies is : " + hobbiesName);
		}
	}

	@When("User click on Add Hobbies button")
	public void user_click_on_add_hobbies_button() {
		browserSetups.driver.findElement(addHobbiesBtn).click();
	}

	@When("User Enter random Hobbies in Hobbies field")
	public void user_enter_random_hobbies_in_hobbies_field() {
		// Generate a random hobbies name
		randomHobbiesName = generateRandomHobbiesName();

		// Find the hobbies field element and enter the random hobbies name
		browserSetups.driver.findElement(AddHobbiesBox).sendKeys(randomHobbiesName);
		System.out.println("Passed hobbies value is : " + randomHobbiesName);
	}

	// Static string for "Admin"
	private static final String ADMIN_PREFIX = "Aotomation_Hobbies";

	public static void main(String[] args) {
		// Call the method to generate a random Hobbies name
		String randomHobbiesName = generateRandomHobbiesName();

		// Print the generated random hobbies name
		System.out.println("Random Hobbies Added: " + randomHobbiesName);
	}

	// Method to generate a random hobbies name
	public static String generateRandomHobbiesName() {
		// Define the characters to be used in the random Hobbies name
		String characters = "0123456789"; // Only digits for the 3-digit number

		// Define the length of the random 3-digit number
		int length = 3;

		// Initialize a Random object
		Random random = new Random();

		// Create a StringBuilder to store the random hobbies name
		StringBuilder HobbiesNameBuilder = new StringBuilder();

		// Append the static "Admin" prefix
		HobbiesNameBuilder.append(ADMIN_PREFIX);

		// Generate a random 3-digit number
		for (int i = 0; i < length; i++) {
			HobbiesNameBuilder.append(characters.charAt(random.nextInt(characters.length())));
		}

		// Convert StringBuilder to String and return the random hobbies name
		return HobbiesNameBuilder.toString();
	}

	@When("User click on Submit button")
	public void user_click_on_submit_button() throws InterruptedException {
		Thread.sleep(5000);
		browserSetups.driver.findElement(SubmitBtn).click();
		
	}

	@Then("User should see generated Hobbies in table")
	public void user_should_see_generated_hobbies_in_table() {
		WebElement table = browserSetups.driver.findElement(hobbiesTable);

		// Find all rows in the table body
		List<WebElement> rows = table.findElements(hobbiesTableRow);

		// Flag to indicate if the hobbies is found in the table
		boolean hobbiesFound = false;

		// Iterate over each row and check if the hobbies is present
		for (WebElement row : rows) {
			// Get the hobbies name from the first column of each row
			String hobbiesName = row.findElement(By.xpath(".//td[1]")).getText();
			System.out.println("This is hobbiesName : " + hobbiesName);
			if (hobbiesName.equals(randomHobbiesName)) {
				hobbiesFound = true;
				break;
			}
		}

		// Check if the hobbies is found in the table
		if (hobbiesFound) {
			System.out.println("The hobbies '" + randomHobbiesName + "' is found in the table.");
		} else {
			System.out.println("The hobbies '" + randomHobbiesName + "' is not found in the table.");
			// Handle the scenario accordingly, e.g., throw an assertion error
		}
	}

	@When("User click on the Edit Hobbies icon")
	public void user_click_on_the_edit_hobbies_icon() {
		browserSetups.driver.findElement(EditHobbiesIcon).click();
	}
	
	@When("User update the Hobbies name")
	public void user_update_the_hobbies_name() throws InterruptedException {
		browserSetups.driver.findElement(AddHobbiesBox).click();
		Thread.sleep(5000);
		WebElement hobbiesField = browserSetups.driver.findElement(By.cssSelector("input[name='hobbie']"));

		// Execute JavaScript to clear the input field's value
		JavascriptExecutor js = (JavascriptExecutor) browserSetups.driver;
		js.executeScript("arguments[0].value = '';", hobbiesField);
		Thread.sleep(5000);
		browserSetups.driver.findElement(AddHobbiesBox).sendKeys("Updated_Hobbies");
		browserSetups.driver.findElement(SubmitBtn).click();

		WebElement table = browserSetups.driver.findElement(hobbiesTable);

		// Find all rows in the table body
		List<WebElement> rows = table.findElements(hobbiesTableRow);

		// Flag to indicate if the hobbies is found in the table
		boolean hobbiesFound = false;

		// Iterate over each row and check if the hobbies is present
		for (WebElement row : rows) {
		    // Get the hobbies name from the first column of each row
		    String hobbiesName = row.findElement(By.xpath(".//td[1]")).getText();
		    if (hobbiesName.equals("Updated_Role")) {
		        hobbiesFound = true;
		        break;
		    }
		}

		// Check if the hobbies is not found and there is a next page
		if (!hobbiesFound && browserSetups.driver.findElement(nextPageButton).isEnabled()) {
		    // Click on next page button
		    browserSetups.driver.findElement(nextPageButton).click();

		    // Reset the rows list after clicking next page button
		    List<WebElement> newRows = browserSetups.driver.findElement(hobbiesTable).findElements(hobbiesTableRow);

		    // Iterate over each row again and check if the hobbies is present
		    for (WebElement row : newRows) {
		        // Get the hobbies name from the first column of each row
		        String hobbiesName = row.findElement(By.xpath(".//td[1]")).getText();
		        if (hobbiesName.equals("Updated_Role")) {
		            hobbiesFound = true;
		            break;
		        }
		    }
		}


		// Check if the hobbies is found in the table
		if (hobbiesFound) {
		    System.out.println("The hobbies 'Updated_Role' is found in the table.");
		} else {
		    System.out.println("The hobbies 'Updated_Role' is not found in the table.");
		    
		    // Handle the scenario accordingly
		}

	}

}
