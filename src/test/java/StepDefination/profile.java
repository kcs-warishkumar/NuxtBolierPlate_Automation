package StepDefination;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class profile {

	By viewProfile = By.xpath("//i[@title='View User']");
	By ImageElement = By.xpath("//*[@id=\"exampleModal\"]/div/div/div[2]/div/div/div/img");
	By uName = By.xpath("//span[@class='name mt-3']");
	By uEmail = By.xpath("//span[@class='idd']");
	By uRole = By.xpath("//span[@class='number']");
	By uJoinDate = By.xpath("//span[@class='join']");
	By EditProfileIcon = By.xpath("//i[@class='fa fa-edit']");
	By DeleteProfileIcon = By.xpath("//i[@class='fa fa-trash-o']");
	By FirstUserName = By.xpath("//div[@class='flex-grow-1']");
	By YesDeleteBtn = By.xpath("//button[@class='swal2-confirm swal2-styled swal2-default-outline']");
	By OkPopUpBtn = By.className("swal2-confirm");

	@When("User clikc on the view profile icone")
	public void user_clikc_on_the_view_profile_icone() {
		browserSetups.driver.findElement(viewProfile).click();
	}

	@Then("User should see the user name along with other details")
	public void user_should_see_the_user_name_along_with_other_details() {
		// Find the element containing the image
		WebElement imageElement = browserSetups.driver.findElement(ImageElement);

		// Get the source URL of the image
		String imageURL = imageElement.getAttribute("src");

		// Download the image
		try {
			// Create a File object to store the image
			File imageFile = new File("image.jpg");

			// Copy the image from the source URL to the local file
			Files.copy(new java.net.URL(imageURL).openStream(), imageFile.toPath(),
					StandardCopyOption.REPLACE_EXISTING);

			System.out.println("Image downloaded successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		String UserName = browserSetups.driver.findElement(uName).getText();
		System.out.println("The name of user is : " + UserName);

		String UserEmail = browserSetups.driver.findElement(uEmail).getText();
		System.out.println("The Email of user is : " + UserEmail);

		String Role = browserSetups.driver.findElement(uRole).getText();
		System.out.println("The Role of user is : " + Role);

		String JoinDate = browserSetups.driver.findElement(uJoinDate).getText();
		System.out.println("The Joining Date of the user is : " + JoinDate);

	}

	@When("User click on the Edit profile icon")
	public void user_click_on_the_edit_profile_icon() {
		browserSetups.driver.findElement(EditProfileIcon).click();
	}

	@When("User upload the profile picture & resume")
	public void user_upload_the_profile_picture_resume() {
		// Locate the file upload input element
		WebElement fileInput = browserSetups.driver.findElement(By.xpath("//input[@id='profile-btn']"));

		// Provide the file path of the image to upload
		String imagePath = "C:/Users/Warish.Kumar.RYSUN/eclipse-workspace/NuxtBoilerplate/src/main/resources/Images/Avatar.png";
		fileInput.sendKeys(imagePath);

		// Wait for some time to ensure the upload process completes
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@When("User click on delete icon")
	public void user_click_on_delete_icon() {
		String UserName = browserSetups.driver.findElement(FirstUserName).getText();
		System.out.println(" This is the UserName before delete user : " + UserName);
		browserSetups.driver.findElement(DeleteProfileIcon).click();
		WebElement ConfirmDelete = browserSetups.driver.findElement(YesDeleteBtn);
		// Click on Confirmation btn for delete the user
		ConfirmDelete.click();

		// Find the OK button element
		WebElement okButton = browserSetups.driver.findElement(OkPopUpBtn);

		// Click the OK button
		okButton.click();

	}

	@Then("User should not see that user in user list")
	public void user_should_not_see_that_user_in_user_list() {
		try {
			// Check if the username is present in the user list
			String userName = browserSetups.driver.findElement(By.xpath("FirstuserName")).getText();
			System.out.println("The userName found in the user list after delete: " + userName);

			// Check if the found username matches the username to delete
			if (userName.equals("UserNameToDelete")) {
				System.out.println("User is still present in the user list");
			} else {
				System.out.println("User has been successfully deleted");
			}
		} catch (NoSuchElementException e) {
			// User is not found, which is expected
			System.out.println("User is not found in the user list, as expected after deletion");
		}
	}
}