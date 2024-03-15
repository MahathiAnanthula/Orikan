import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.concurrent.ConcurrentHashMap.KeySetView

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import internal.GlobalVariable



class Registration {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	@Given("I open the UI Automation Test URL")
	def launchURL() {
		WebUI.getUrl("https://orikan-ui-automation-test.azurewebsites.net/")
	}

	@When("I click on (.*) button")
	def click_nextButton(String name) {
		WebUI.click(findTestObject('Object Repository/generic_button_Text',[("buttonName"):name]))
	}

	def checkErrorMessage_Registration(String pageName) {
		List<ArrayList> fieldNames
		if(pageName.contentEquals("Registration")) {
			fieldNames=[
				"Email Address",
				"Password",
				"Confirm Password"
			]
		}
		else if(pageName.contentEquals("Contact")) {
			fieldNames=[
				"First Name",
				"Last Name",
				"Address Line 1",
				"Postcode",
				"City",
				"State"
			]
		}
		else if(pageName.contentEquals("Payment")) {
			fieldNames=[
				"Cardholder Name",
				"Card Type",
				"Card Number",
				"Card CVV",
				"Card Expiry Month",
				"Card Expiry Yea"
			]
		}
		List<WebElement> elements_ValidationError = WebUI.findWebElements(findTestObject('Object Repository/generic_ValidationErrorCheck'), GlobalVariable.min_wait)

		for (int i=0;i<elements_ValidationError.size();i++) {
			WebUI.verifyMatch(elements_ValidationError.get(i).getText().trim()+" is required", fieldNames.get(i), false)
		}
	}

	@When ("I enter on Valid details in registration section")
	def providingDetails_registration() {
		WebUI.sendKeys(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"emailAddress"]), "test@orikan.com")
		WebUI.sendKeys(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"password"]), "test123")
		WebUI.sendKeys(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"confirmPassword"]), "test123")
	}
	@Then("I verify (.*) section is opened")
	def sectionNameCheck(String sectionName) {
		WebUI.verifyElementPresent(findTestObject('Object Repository/activeSectionCheck',[("sectionName")]:sectionName),GlobalVariable.min_wait)
	}
	@When("I enter on Valid details in Contact section")
	def providingDetails_contact() {
		WebUI.sendKeys(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"firstName"]), "test")
		WebUI.sendKeys(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"lastName"]), "user")
		WebUI.sendKeys(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"addressLine1"]), "US")
		WebUI.sendKeys(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"postcode"]), "6642 - 6762")
		WebUI.sendKeys(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"city"]), "New South Wales")
		WebUI.selectOptionByValue(findTestObject('Object Repository/generic_selectClass',["name"]:"state"), "New South Wales", false)
	}
	@When("I enter on Valid details in Payment section")
	def providingDetails_payment() {
		WebUI.sendKeys(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"cardHolderName"]), "test")
		WebUI.sendKeys(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"lastName"]), "user")
		WebUI.click(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"cardTypeVISA"]))
		WebUI.sendKeys(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"cardCVV"]), "123")
		WebUI.selectOptionByValue(findTestObject('Object Repository/generic_selectClass',["name"]:"cardExpiryMonth"), "May", false)
		WebUI.sendKeys(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"cardExpiryYear"]), "2028")
	}
	@When("I verify error message is displaying for in Terms & Conditions section (.*)")
	def verifyErrorMsg_TC(String name) {
		if(name.contains("before")) {
			WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/generic_ValidationErrorCheck')).trim(), "You must first read all the terms and conditions before submitting", false)
		}else {
			WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/generic_ValidationErrorCheck')).trim(), "You must agree to these terms and conditions before submitting", false)
		}
	}
	@When("I scroll to end of the line in terms and conditions box")
	def scroll_end() {
		WebUI.enhancedClick(findTestObject('Object Repository/textArea'))
		for(int i=0;i<50;i++) {
			WebUI.sendKeys(findTestObject('Object Repository/textArea'), Keys.chord(Keys.ARROW_DOWN))
		}
	}

	@When ("I select Checkbox in Terms & Condition checkbox")
	def selectCheckboxTC() {
		WebUI.click(findTestObject('Object Repository/generic_InputFields',[("fieldName"):"termsCondition"]))
	}

	@When("I verify summary details in Complete section")
	def summary_complete() {
		List<WebElement> elements_summary = WebUI.findWebElements(findTestObject('Object Repository/summary_complete'), GlobalVariable.min_wait)

		WebUI.verifyMatch(elements_summary.get(0).getText().trim(), "Welcome, .", false)
		WebUI.verifyMatch(elements_summary.get(0).getText().trim(), "Your user account test@orikan.com has been successfully registered.", false)
	}
}