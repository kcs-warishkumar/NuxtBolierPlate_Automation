Feature: Verify all the scanario of Hobbies Modules Like user Hobbies, edit Hobbies and delete Hobbies

  @TestViewHobbies @All_TestCases
  Scenario: Verify the View Hobbies link
    Given User goes to the Nuxt login page
    When User enters "admin@gmail.com" in the username field
    And User enters "admin" in the password field
    And User clicks on the SignIn button
    When User click on Hobbies tab
		Then User should see all the available Hobbies in table
		
	 @TestAddHobbies @All_TestCases
  Scenario: Verify Adding Hobbies
    Given User goes to the Nuxt login page
    When User enters "admin@gmail.com" in the username field
    And User enters "admin" in the password field
    And User clicks on the SignIn button
    And User click on Hobbies tab
    And User click on Add Hobbies button
    And User Enter random Hobbies in Hobbies field
    When User click on Submit button
		Then User should see generated Hobbies in table
		
		
	  @TestEditHobbies @All_TestCases
  Scenario: Verify the Edit Hobbies link
    Given User goes to the Nuxt login page
    When User enters "admin@gmail.com" in the username field
    And User enters "admin" in the password field
    And User clicks on the SignIn button
    And User click on Hobbies tab
		And User click on the Edit Hobbies icon
		And User update the Hobbies name
		When User click on Submit button
		Then User should see generated Hobbies in table
