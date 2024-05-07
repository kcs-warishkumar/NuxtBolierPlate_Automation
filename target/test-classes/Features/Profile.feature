Feature: Verify the View, Edit and delete profile

  @TestViewUser @All_TestCases
  Scenario: Verify the View profile link
    Given User goes to the Nuxt login page
    When User enters "admin@gmail.com" in the username field
    And User enters "admin" in the password field
    And User clicks on the SignIn button
    And User click on Users tab
		When User clikc on the view profile icone
		Then User should see the user name along with other details
		
  @TestEditUser @All_TestCases
  Scenario: Verify the edit profile link
    Given User goes to the Nuxt login page
    When User enters "admin@gmail.com" in the username field
    And User enters "admin" in the password field
    And User clicks on the SignIn button
    And User click on Users tab
		And User click on the Edit profile icon
		And User upload the profile picture & resume
		And User clicks on the Submit button
		When User clikc on the view profile icone
		Then User should see the user name along with other details
		
		
	@TestDeleteUser	@All_TestCases
	Scenario: Verify the delete profile link
    Given User goes to the Nuxt login page
    When User enters "admin@gmail.com" in the username field
    And User enters "admin" in the password field
    And User clicks on the SignIn button
    And User click on Users tab
    When User click on delete icon
    Then User should not see that user in user list