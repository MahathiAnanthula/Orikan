#Author: Mahathi Ananthula
#Feature: Validating Successfull Registration

Feature: Validating Successfull Registration

Scenario: Entrering the details in Registration section and check on Contact Section
    Given I open the UI Automation Test URL
    When I enter on Valid details in registration section
     And I click on "Next" button
    Then I verify "Contact" section is opened

Scenario: Entrering the details in Contact section and check on Payment Section
    When I enter on Valid details in Contact section
    And I click on "Next" button
    Then I verify "Payment" section is opened
    
    
Scenario: Entrering the details in Payment section and check on Payment Section
    When I enter on Valid details in Payment section
    And I click on "Next" button
    Then I verify "Terms & Conditions" section is opened
    When I scroll to end of the line in terms and conditions box
    And I select Checkbox in Terms & Condition checkbox
    And I click on "Submit" button
    Then I verify "Complete" section is opened
    And I verify summary details in Complete section
    