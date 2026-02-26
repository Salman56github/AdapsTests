Feature: Daily status report

  Scenario: Send DSR to Leads, manager and PMO
    Given user drafts the dsr mail and send the email
    When user access the web form
    Then check whether user have multiple emailId's

#    Employee info
    And user click on Start now
    Then user select the current data
    Then user enter the EmployeeID
    Then user enter the In-Time
    Then user enter the Out-Time
    Then user enter note
    Then user select the check box of Working
    And user select on Next

#    Project info
    When user select the project
    Then user select Full time or Support
    Then user enter the number of hours assigned
    Then user enter the Task accomplished today
    Then user enter the number of hours worked
    Then user select the check box of status
    And user select on Next

#  Project 2 info
    Then user fills the details of project
    And user select on Next

#  submit page
    Then user enter the tomarrow task
    Then user select the check box of send a copy to email
#    And user submit the web form





