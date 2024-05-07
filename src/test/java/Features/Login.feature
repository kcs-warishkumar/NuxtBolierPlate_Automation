Feature: Verify the Login Functionality of Nuxt Boilerplate

  @TestLogin @All_TestCases
  Scenario Outline: Login with valid and invalid credentials
    Given User goes to the Nuxt login page
    When User enters "<username>" in the username field
    And User enters "<password>" in the password field
    And User clicks on the SignIn button
    Then User should <outcome>

    Examples: 
      | username        | password | outcome                        |
      | invalid_user    | password | see an error message displayed |
      | admin@gmail.com | wrongpwd | see an error message displayed |
      | admin@gmail.com |          | see an error message displayed |
      |                 | password | see an error message displayed |
      | admin@gmail.com | admin    | redirect to the Dashboard      |

