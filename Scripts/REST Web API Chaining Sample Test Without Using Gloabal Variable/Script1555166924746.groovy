import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

userResponse = WS.sendRequest(findTestObject('REST Web Services API/ListUsers'))

def urSlurper = new groovy.json.JsonSlurper()

def ursResult = urSlurper.parseText(userResponse.getResponseBodyContent())

def ursrValue = ursResult.data[2].first_name

println('Value Extracted From Test Case :' + ursrValue)

//GlobalVariable.userName = ursrValue

//println('Global Variable Is Now Holding : ' + GlobalVariable.userName)

// NOw we are not using Global variable to do this verification [rocess. rather doing it from withing this block of code.


WS.sendRequestAndVerify(findTestObject('REST Web Services API/UpdateUsers', [('UserName') : ursrValue]))

// Make sure that you are using '(' instead of '{' specifying Variable value here.
WS.sendRequestAndVerify(findTestObject('REST Web Services API/UpdateUsers', [('UserName') : ursrValue]))

