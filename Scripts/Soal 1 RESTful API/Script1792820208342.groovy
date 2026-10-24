import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper

// Utility JSON
JsonSlurper slurper = new JsonSlurper()

// Login
def email = "emily.johnson@x.dummyjson.com"
def firstName = "Emily"
def lastName = "Johnson"
def gender = "female"
def username = 'emilys'
def password = 'emilyspass'

result = WS.sendRequest(((findTestObject('DummyAPI/Login', [('username') : username, ('password') : password])) as RequestObject))
WS.verifyResponseStatusCode(result, 200)
def response = slurper.parseText(result.getResponseText())
def accessToken = response['accessToken']


// Cek informasi user berdasarkan token Login
result = WS.sendRequest(findTestObject('DummyAPI/Get Auth User', [accessToken: accessToken]) as RequestObject)
WS.verifyElementPropertyValue(result, 'username', username)
WS.verifyElementPropertyValue(result, 'email', email)
WS.verifyElementPropertyValue(result, 'firstName', firstName)
WS.verifyElementPropertyValue(result, 'lastName', lastName)
WS.verifyElementPropertyValue(result, 'gender', gender)



