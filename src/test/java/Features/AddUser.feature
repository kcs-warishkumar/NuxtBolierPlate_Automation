Feature: Verify the Add user functionality of Nuxt Boilerplate

  @TestAddUser @All_TestCases
  Scenario: Verify Add a New User with Valid Information
    Given User goes to the Nuxt login page
    When User enters "admin@gmail.com" in the username field
    And User enters "admin" in the password field
    And User clicks on the SignIn button
    And User click on Users tab
    And User Click on Add New User button
    When User fills in the following information:
      | Field         | Value            |
      | First Name    | Warish           |
      | Last Name     | Kumar            |
      | Email         | Any Random Email |
    And User select DOB from calendar
    And User select Male
    And User select Department as IT
    And User select Admin as Role
    And User select Dancing and Painting in Hobbies
    And Keep the Status toogle as Active
    And User clicks on the Submit button
    Then User should see a success message confirming the user has been added
    And User should be redirected to the Users page