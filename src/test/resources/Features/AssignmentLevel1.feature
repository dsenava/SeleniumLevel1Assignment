Feature:Register to guru99 application,validate login using valid and invalid credentials and book a flight ticket
  Background:
    Given User launches demo application
  Scenario Outline: Validate User is able to register for first time into guru99 demo application
    And User navigates to Registration page
    And User inputs personal details from given sheetName "<SheetName>" and rownumber <RowNumber>
    When User submits his details
    Then User gets registered successfully
    Examples:
      |SheetName|RowNumber|
      |RegistrationData|1 |
      |RegistrationData|2 |


  Scenario Outline:Validate User is able to login into guru99 demo application with valid credentials
    And User inputs username and password from given sheetName "<sheetName>" and rownumber <rowNumber>
    When User clicks on Login
    Then User lands into the Homepage successfully and validate the user login
    Examples:
      |sheetName|rowNumber|
      |LoginData|0|
      |LoginData|1|

    Scenario Outline:Validate User is not able to login into guru99 demo application with Invalid credentials
      Given User inputs invalid username "<username>" and password "<password>"
      When User clicks on Login
      Then User login is unsuccessful
  Examples:
  |username|password|
  |t%^^     |^&&     |
  |y_8933    |_       |

    Scenario Outline: Validate User is able to book a flight ticket
      And User is able to login to the application with username "<username>" and password "<password>"
      And User navigates to Flight booking page
      When User inputs all flight details
      And User clicks on Continue
      Then validate success message "Flights booked successfully" if seats are available
      Examples:
      |username|password|
      |first1  |abc@def |




