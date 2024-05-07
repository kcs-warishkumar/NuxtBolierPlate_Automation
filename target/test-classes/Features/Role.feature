Feature: Verify all the scanario of Role Modules Like user role, edit role and delete role

  @TestViewRole @All_TestCases
  Scenario: Verify the View Role link
    Given User goes to the Nuxt login page
    When User enters "admin@gmail.com" in the username field
    And User enters "admin" in the password field
    And User clicks on the SignIn button
    When User click on Role tab
		Then User should see all the available roles in table
		
	 @TestAddRole @All_TestCases
  Scenario: Verify Adding role
    Given User goes to the Nuxt login page
    When User enters "admin@gmail.com" in the username field
    And User enters "admin" in the password field
    And User clicks on the SignIn button
    And User click on Role tab
    And User click on Add Role button
    And User Enter random role in role field
    When User click on Submit button
		Then User should see generated roles in table
		
		
	  @TestEditRole @All_TestCases
  Scenario: Verify the Edit Role link
    Given User goes to the Nuxt login page
    When User enters "admin@gmail.com" in the username field
    And User enters "admin" in the password field
    And User clicks on the SignIn button
    And User click on Role tab
		And User click on the Edit role icon
		And User update the role name
		When User clicks on the Update button
		Then User should see generated roles in table