#Author: Mahathi Ananthula
#Feature: Validating Mandatory Error Message on Home Page.

Feature: Validating Mandatory Error Message on Home Page.

Scenario: Validating the Mandatory field Error Messages in Registration Section
    Given I open the UI Automation Test URL
    When I click on "Next" button
    Then I verify Mandatory field error message is displaying for all fields in "Registration" section

Scenario: Validating the Mandatory field Error Messages in Contact Section
    When I enter on Valid details in registration section
    And I click on "Next" button
    Then I verify "Contact" section is opened
    When I click on "Next" button
    Then I verify Mandatory field error message is displaying for all fields in "Contact" section
    
Scenario: Validating the Mandatory field Error Messages in Payment Section
    When I enter on Valid details in Contact section
    And I click on "Next" button
    Then I verify "Payment" section is opened
    When I click on "Next" button
    Then I verify Mandatory field error message is displaying for all fields in "Payment" section
    
Scenario: Validating the Mandatory error Messages in Terms & Conditions Section
    When I enter on Valid details in Payment section
    And I click on "Next" button
    Then I verify "Terms & Conditions" section is opened
    When I click on "Submit" button
    Then I verify error message is displaying for in Terms & Conditions section "before"
    When I scroll to end of the line in terms and conditions box
    And I click on "Submit" button
    Then I verify error message is displaying for in Terms & Conditions section "after"
    