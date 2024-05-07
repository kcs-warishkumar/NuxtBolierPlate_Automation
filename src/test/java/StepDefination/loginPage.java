package StepDefination;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginPage {

	By Email = By.id("email");
	By Password = By.id("password");
	By SubmitBtn = By.id("liveToastBtn");
	By errorMessage11 = By.xpath("//span[@class='error-message']");
	By errorMessage12 = By.xpath("//div[@class='alert alert-danger']");
	By DashBoardText = By.xpath("//span[@class='welcome-tag']");

	@Given("User goes to the Nuxt login page")
	public void user_goes_to_the_nuxt_login_page() {
		System.out.println("User redirected to the Login Page of Nuxt Boilerplate");
	}
	
	@When("User enters {string} in the username field")
	public void user_enters_in_the_username_field(String string) throws InterruptedException {
		browserSetups.driver.findElement(Email).click();
		Thread.sleep(1000);
		browserSetups.driver.findElement(Email).sendKeys(string);
	}

	@When("User enters {string} in the password field")
	public void user_enters_in_the_password_field(String string) throws InterruptedException {
		browserSetups.driver.findElement(Password).click();
		Thread.sleep(1000);
		browserSetups.driver.findElement(Password).sendKeys(string);
	}

	@When("User clicks on the SignIn button")
	public void user_clicks_on_the_sign_in_button() {
		browserSetups.driver.findElement(SubmitBtn).click();
	}

	@Then("User should redirect to the Dashboard")
	public void user_should_redirect_to_the_dashboard() {
		WebElement dashboard;
		try {
			dashboard = browserSetups.driver.findElement(DashBoardText);
			System.out.println(dashboard);
		} catch (NoSuchElementException e) {
			dashboard = null;
		}

		if (dashboard != null && dashboard.isDisplayed()) {
			// Element is visible, meaning successful login
			System.out.println("User Successfully redirected to the Dashboard.");
		} else {
			// User is not able to login Successfully
			System.out.println("User is not able to login Successfully.");
			throw new AssertionError("User is not able to login Successfully.");
		}
	}

	@Then("User should see an error message displayed")
	public void user_should_see_an_error_message_displayed() {

		WebElement errorMessage1, errorMessage2;
		try {
			errorMessage1 = browserSetups.driver.findElement(errorMessage11);
		} catch (NoSuchElementException e) {
			errorMessage1 = null;
		}

		try {
			errorMessage2 = browserSetups.driver.findElement(errorMessage12);
		} catch (NoSuchElementException e) {
			errorMessage2 = null;
		}

		if (errorMessage1 != null && errorMessage1.isDisplayed()) {
			// Error message 1 displayed as expected
			System.out.println("Error Message Is : " + errorMessage1.getText());
		} else if (errorMessage2 != null && errorMessage2.isDisplayed()) {
			// Error message 2 displayed as expected
			System.out.println("Error Message Is : " + errorMessage2.getText());
		} else {
			// Error message not displayed
			System.out.println("Error message is not displayed.");
		}
	}
}
