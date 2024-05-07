Feature: Verify all the scanario of Department Modules Like user Hobbies, edit Hobbies and delete Hobbies

  @TestViewDepartment @All_TestCases
  Scenario: Verify the View Department link
    Given User goes to the Nuxt login page
    When User enters "admin@gmail.com" in the username field
    And User enters "admin" in the password field
    And User clicks on the SignIn button
    When User click on Department tab
    Then User should see all the available Department in table

  @TestAddDepartment @All_TestCases
  Scenario: Verify Adding other departments
    Given User goes to the Nuxt login page
    When User enters "admin@gmail.com" in the username field
    And User enters "admin" in the password field
    And User clicks on the SignIn button
    And User click on Department tab
    And User click on Add Dpartment button
    And User Enter random Department in Department field
    When User click on Submit button
    Then User should see generated Department in table
